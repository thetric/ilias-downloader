package com.github.thetric.iliasdownloader.service.webparser.impl.course

import com.github.thetric.iliasdownloader.service.IliasItemVisitor
import com.github.thetric.iliasdownloader.service.model.Course
import com.github.thetric.iliasdownloader.service.model.IliasItem

interface CourseSyncService {
    Collection<Course> getJoinedCourses()

    IliasItemVisitor.VisitResult visit(final IliasItem courseItem, final IliasItemVisitor itemVisitor)
}
