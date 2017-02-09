package com.github.thetric.iliasdownloader.service.webparser.impl

import java.time.LocalDateTime

/**
 * @author broj
 */
interface RelativeDateTimeParser {
    LocalDateTime parse(String dateTimeString)

    boolean isRelativeDateTime(String dateTimeString)
}