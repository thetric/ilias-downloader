package com.github.thetric.iliasdownloader.cli

import com.github.thetric.iliasdownloader.cli.console.ConsoleService
import com.github.thetric.iliasdownloader.cli.sync.SyncHandler
import com.github.thetric.iliasdownloader.service.IliasService
import com.github.thetric.iliasdownloader.service.model.Course
import com.github.thetric.iliasdownloader.service.model.IliasItem
import com.github.thetric.iliasdownloader.ui.common.prefs.UserPreferenceService
import com.github.thetric.iliasdownloader.ui.common.prefs.UserPreferences
import groovy.util.logging.Log4j2

import java.util.function.Function

import static com.github.thetric.iliasdownloader.service.IliasService.VisitResult.CONTINUE
import static org.apache.logging.log4j.Level.DEBUG

@Log4j2
final class IliasCliController {
    private final CliOptions cliOptions

    private final IliasService iliasService

    private final Function<UserPreferences, ? extends SyncHandler> syncHandlerProvider

    private final ResourceBundle resourceBundle
    private final UserPreferenceService preferenceService
    private final ConsoleService consoleService

    IliasCliController(
        final CliOptions cliOptions,
        final IliasService iliasService,
        final Function<UserPreferences, ? extends SyncHandler> syncHandlerProvider,
        final ResourceBundle resourceBundle,
        final UserPreferenceService preferenceService, final ConsoleService consoleService) {
        this.cliOptions = cliOptions
        this.iliasService = iliasService
        this.syncHandlerProvider = syncHandlerProvider
        this.resourceBundle = resourceBundle
        this.preferenceService = preferenceService
        this.consoleService = consoleService
    }

    void start() {
        UserPreferences prefs = preferenceService.loadUserPreferences()

        updateFileSizeLimitFromCliOpts(prefs)

        Collection<Course> coursesFromIlias = iliasService.joinedCourses
        Collection<Course> coursesToSync

        if (cliOptions.showCourseSelection || !prefs.activeCourses) {
            try {
                coursesToSync = showAndSaveCourseSelection(coursesFromIlias)
            } catch (CourseSelectionOutOfRange e) {
                log.catching(DEBUG, e)
                String errMsg = resourceBundle.getString('sync.courses.prompt.errors.out-of-range')
                System.err.println errMsg.replace('{0}', coursesFromIlias.size() as String)
                return
            }
        } else {
            println ''
            coursesToSync = coursesFromIlias.findAll { prefs.activeCourses.contains(it.id) }
        }
        // update ids - some might not exist anymore
        prefs.activeCourses = coursesToSync*.id.unique()
        preferenceService.saveUserPreferences(prefs)

        printSelectedCourses(coursesToSync)
        executeSync(iliasService, coursesToSync, prefs)
    }

    private void printSelectedCourses(Collection<Course> coursesToSync) {
        println ''
        println ">>> Syncing ${coursesToSync.size()} courses:"
        print coursesToSync.collect { "  > ${it.name}" }.join('\n')
    }

    private void updateFileSizeLimitFromCliOpts(UserPreferences prefs) {
        if (cliOptions.fileSizeLimitInMiB != null) {
            // limit = 0 -> unlimited
            if (cliOptions.fileSizeLimitInMiB >= 0) {
                prefs.maxFileSizeInMiB = cliOptions.fileSizeLimitInMiB
                preferenceService.saveUserPreferences(prefs)
            } else {
                def errMsg = "${resourceBundle.getString('args.sync.max-size.negative')} $cliOptions.fileSizeLimitInMiB"
                throw new IllegalArgumentException(errMsg)
            }
        }
    }

    private void executeSync(IliasService iliasService, Collection<Course> coursesToSync, UserPreferences prefs) {
        println ''
        println ">>> ${resourceBundle.getString('sync.started')}"
        SyncHandler syncHandler = syncHandlerProvider.apply(prefs)
        for (Course course : coursesToSync) {
            iliasService.visit(course, { IliasItem iliasItem ->
                syncHandler.handle(iliasItem)
                return CONTINUE
            })
        }
        println ''
        println ">>> ${resourceBundle.getString('sync.finished')}"
    }

    /**
     * Prompts the user to select the positions of the {@link Course}s to sync. If the input is empty (default) all
     * courses are selected. Otherwise the space separated positions (1 based) are taken from the {@code allCourses} argument.
     * @param allCourses {@link Course}s to select from
     * @return the {@link Course}s to sync
     */
    private Collection<Course> showAndSaveCourseSelection(Collection<Course> allCourses) {
        println ''
        println ">>> ${resourceBundle.getString('sync.courses.available')}"
        allCourses.eachWithIndex { Course course, int i ->
            println "\t${i + 1} ${course.name} (ID: ${course.id})"
        }
        def courseSelection = consoleService.readLine('sync.courses', resourceBundle.getString('sync.courses.prompt'))
        final trimmedSelection = courseSelection.trim()
        if (!trimmedSelection) {
            return allCourses
        } else {
            List<Integer> courseIndices = courseSelection.split(/\s+/)
                                                         .collect { Integer.parseInt it }
                                                         .collect { it - 1 }
            if (courseIndices.any { it < 0 || it > allCourses.size() }) {
                throw new CourseSelectionOutOfRange()
            }
            return courseIndices.collect { allCourses[it] }
        }
    }

    private static final class CourseSelectionOutOfRange extends RuntimeException {

    }
}
