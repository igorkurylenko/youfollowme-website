package io.itdraft.youfollowme.website.util;

public class ColorUtil {

    private ColorUtil() {
    }

    public static String rgbaWhite(double alpha) {
        return new StringBuilder().append("rgba(255,255,255, ")
                .append(alpha).append(")").toString();
    }

}
