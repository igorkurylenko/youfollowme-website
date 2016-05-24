package io.itdraft.youfollowme.website.util;

import com.google.gwt.http.client.UrlBuilder;

public class TwitterUtil {

    public static final String HTTPS = "https";
    public static final String TWITTER_HOST = "twitter.com";
    public static final String TWITTER_SEARCH_PATH = "search";
    public static final String TWITTER_SEARCH_QUERY_PARAM_NAME = "q";

    public static String buildTwitterSearchUrl(String q) {
        return new UrlBuilder()
                .setProtocol(HTTPS)
                .setHost(TWITTER_HOST)
                .setPath(TWITTER_SEARCH_PATH)
                .setParameter(TWITTER_SEARCH_QUERY_PARAM_NAME, q)
                .buildString();
    }

    private TwitterUtil() {
    }
}
