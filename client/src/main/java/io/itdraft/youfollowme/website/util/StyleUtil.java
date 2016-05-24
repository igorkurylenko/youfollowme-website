package io.itdraft.youfollowme.website.util;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootPanel;

public class StyleUtil {

    private StyleUtil() {
    }

    public static int getTop(Element element) {
        Style style = element.getStyle();

        return parseInt(style.getTop());
    }

    public static int getRight(Element element) {
        Style style = element.getStyle();

        return parseInt(style.getRight());
    }

    public static int getBottom(Element element) {
        Style style = element.getStyle();

        return parseInt(style.getBottom());
    }

    public static int getLeft(Element element) {
        Style style = element.getStyle();

        return parseInt(style.getLeft());
    }

    private static native int parseInt(String value) /*-{
        return parseInt(value);
    }-*/;

}
