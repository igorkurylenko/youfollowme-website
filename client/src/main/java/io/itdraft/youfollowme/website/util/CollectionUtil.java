package io.itdraft.youfollowme.website.util;

import java.util.Collection;

public class CollectionUtil {

    private CollectionUtil() {
    }

    public static boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

}
