<a name="1.1.0"></a>
# [1.1.0](https://github.com/thetric/ilias-downloader/compare/1.0.2...1.1.0) (2017-04-09)


### Bug Fixes

* **cli:** handle login err and do not crash ([6b2a4f2](https://github.com/thetric/ilias-downloader/commit/6b2a4f2))
* **cli:** print "login successful" msg only if login succeeded ([c4e50cc](https://github.com/thetric/ilias-downloader/commit/c4e50cc))
* **cli:** show course selection if no courses are active ([aa6135e](https://github.com/thetric/ilias-downloader/commit/aa6135e))
* **cli:** update activeCourse ids only one + make them unique ([7a23df2](https://github.com/thetric/ilias-downloader/commit/7a23df2))
* **common-ui:** create parent dirs for settings file if parent don't exist ([9f51f0f](https://github.com/thetric/ilias-downloader/commit/9f51f0f))
* **connector-domparser:** check if login has succeeded ([831b19b](https://github.com/thetric/ilias-downloader/commit/831b19b))
* **connector-domparser:** correctly prepend 'https://' ([29e1a9b](https://github.com/thetric/ilias-downloader/commit/29e1a9b))
* **connector-domparser:** extract base url from trimmed url string ([b68372e](https://github.com/thetric/ilias-downloader/commit/b68372e))
* **connector-domparser:** try to preserve content's original encoding otherwise use iso 8859-1 ([8b3cfb5](https://github.com/thetric/ilias-downloader/commit/8b3cfb5))


### Features

* **cli:** default to all courses if the input is blank when prompting the user for the courses to sync ([fe3b061](https://github.com/thetric/ilias-downloader/commit/fe3b061)), closes [#7](https://github.com/thetric/ilias-downloader/issues/7)
* **cli:** include username in password prompt ([28bb0a2](https://github.com/thetric/ilias-downloader/commit/28bb0a2))
* **connector-domparser:** add okHttp web client impl ([3e27d9c](https://github.com/thetric/ilias-downloader/commit/3e27d9c))
* **ui-common:** add UserPrefSrvc.getSettingsFile ([4e2ce9e](https://github.com/thetric/ilias-downloader/commit/4e2ce9e))



<a name="1.0.2"></a>
## [1.0.2](https://github.com/thetric/ilias-downloader/compare/1.0.1...1.0.2) (2017-04-03)


### Bug Fixes

* do not reset maxFileSize to 0 ([8d60bac](https://github.com/thetric/ilias-downloader/commit/8d60bac)), closes [#6](https://github.com/thetric/ilias-downloader/issues/6)



<a name="1.0.1"></a>
## [1.0.1](https://github.com/thetric/ilias-downloader/compare/1.0.0...1.0.1) (2017-04-03)


### Bug Fixes

* save selected courses ([a47f9e3](https://github.com/thetric/ilias-downloader/commit/a47f9e3))
* use 0 instead of null for default file size ([e0bed4c](https://github.com/thetric/ilias-downloader/commit/e0bed4c)), closes [#5](https://github.com/thetric/ilias-downloader/issues/5)



<a name="1.0.0"></a>
# [1.0.0](https://github.com/thetric/ilias-downloader/compare/313963c...1.0.0) (2017-03-16)


### Bug Fixes

* relative DateTime is now parsed with seconds+nanos set at 0 ([61d1c69](https://github.com/thetric/ilias-downloader/commit/61d1c69))
* remove [@CompileStatic](https://github.com/CompileStatic) to make visitor pattern work ([313963c](https://github.com/thetric/ilias-downloader/commit/313963c))
* replace existing files in sync ([b309f75](https://github.com/thetric/ilias-downloader/commit/b309f75))
* set correct mod time of files ([78e68b0](https://github.com/thetric/ilias-downloader/commit/78e68b0))
* use sync dir from cli args instead of hardcoded one ([7377768](https://github.com/thetric/ilias-downloader/commit/7377768))
* **cli:** save prefs on 1st start ([cbdf153](https://github.com/thetric/ilias-downloader/commit/cbdf153))
* **common:** create parent dirs of settings path only if not exist ([49557e2](https://github.com/thetric/ilias-downloader/commit/49557e2))


### Features

* **cli:** accept download size limit ([0890231](https://github.com/thetric/ilias-downloader/commit/0890231))
* **cli:** cache paths of parent ilias items while syncing ([15b25b1](https://github.com/thetric/ilias-downloader/commit/15b25b1))
* **cli:** check file size limit in sync ([92abe32](https://github.com/thetric/ilias-downloader/commit/92abe32))
* **cli:** enable course selection ([7f5558a](https://github.com/thetric/ilias-downloader/commit/7f5558a)), closes [#3](https://github.com/thetric/ilias-downloader/issues/3)
* **cli:** use json user pref srvc instead of yml ([231681d](https://github.com/thetric/ilias-downloader/commit/231681d))
* **common:** add json user pref service ([0004194](https://github.com/thetric/ilias-downloader/commit/0004194))
* **common:** create parent dirs + settings file if not exist ([1d25081](https://github.com/thetric/ilias-downloader/commit/1d25081))
* **connector:** replace IliasService.getCourseItems(Course) with traversal method ([dab41ab](https://github.com/thetric/ilias-downloader/commit/dab41ab))
* **connector-domparser:** extract file size ([5ac7592](https://github.com/thetric/ilias-downloader/commit/5ac7592))
* **i18n:** add translation for 'download limit' cli option ([66edf6b](https://github.com/thetric/ilias-downloader/commit/66edf6b))


