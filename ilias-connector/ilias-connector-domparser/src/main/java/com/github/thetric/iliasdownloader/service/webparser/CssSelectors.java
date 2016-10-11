package com.github.thetric.iliasdownloader.service.webparser;

import lombok.Getter;

/**
 * @author broj
 * @since 18.08.2016
 */
enum CssSelectors {
    COURSE_SELECTOR("a[href*='_crs_'].il_ContainerItemTitle"),
    ITEM_CONTAINER_SELECTOR(".il_ContainerListItem"),
    ITEM_TITLE_SELECTOR("a.il_ContainerItemTitle"),
    ITEM_PROPERTIES_SELECTOR(".il_ItemProperty");

    @Getter
    private final String cssSelector;

    CssSelectors(String cssSelector) {
        this.cssSelector = cssSelector;
    }
}