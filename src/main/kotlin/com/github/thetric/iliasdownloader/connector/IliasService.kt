package com.github.thetric.iliasdownloader.connector

import com.github.thetric.iliasdownloader.connector.model.Course
import com.github.thetric.iliasdownloader.connector.model.CourseFile
import com.github.thetric.iliasdownloader.connector.model.LoginCredentials
import java.io.IOException
import java.io.InputStream

/**
 * Provides access to the Ilias.
 * This interface provides some methods for basic session management (login,
 * logout) and it can list the courses (with/without their contents).
 */
interface IliasService : AutoCloseable {
    // Session management

    /**
     * Logs the user in.
     *
     * @param loginCredentials user credentials
     * @see [logout]
     */
    fun login(loginCredentials: LoginCredentials)

    /**
     * Logs the current user out.
     *
     * @see [login]
     */
    fun logout()

    // course sync

    /**
     * Finds all courses without any course content.
     *
     * @return all [Course]s of the current user
     * @see [visit]
     */
    fun getJoinedCourses(): Collection<Course>

    fun <C> visit(
        courseItem: Course,
        itemListener: IliasItemListener<C>,
        initialContext: C
    )

    /**
     * Downloads the content of the {@link CourseFile} from the Ilias.
     *
     * @param file [CourseFile] to download
     * @return [InputStream] content of the file
     * @see com.github.thetric.iliasdownloader.connector.exception.IliasException
     */
    @Throws(IOException::class)
    fun getContentAsStream(file: CourseFile): InputStream
}
