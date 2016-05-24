package io.itdraft.youfollowme.website.util.ui;

import com.google.gwt.canvas.dom.client.TextMetrics;
import io.itdraft.youfollowme.website.geometry.HasSize;
import io.itdraft.youfollowme.website.geometry.Size;

public class MeasuredText implements HasSize {
    private final String text;
    private final Font font;
    private final Size size;

    public MeasuredText(String text, Font font, TextMetrics textMetrics) {
        this(text, font, new Size(textMetrics.getWidth(), font.getFontSize()));
    }

    public MeasuredText(String text, Font font, Size size) {
        this.text = text;
        this.font = font;
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public Size getSize() {
        return size;
    }

    public double getWidth() {
        return getSize().getWidth();
    }

    public double getHeight() {
        return getSize().getHeight();
    }

    public Font getFont() {
        return font;
    }
}
