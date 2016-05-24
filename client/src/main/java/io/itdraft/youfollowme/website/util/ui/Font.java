package io.itdraft.youfollowme.website.util.ui;

import io.itdraft.youfollowme.website.util.CSSUnit;

public class Font {
    private int fontSize;
    private String fontFamily;
    private String color;

    public Font(int fontSize, String fontFamily, String color) {
        this.fontSize = fontSize;
        this.fontFamily = fontFamily;
        this.color = color;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public String getColor() {
        return color;
    }

    public String toStringForCanvasFontProperty() {
        return new StringBuilder()
                .append(fontSize)
                .append(CSSUnit.px.name())
                .append(" ")
                .append(fontFamily).toString();
    }

    @Override
    public String toString() {
        return toStringForCanvasFontProperty();
    }
}
