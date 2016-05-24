/*
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.itdraft.youfollowme.website.place;

/**
 * Main presenters name tokens.
 */
public class PlaceNameTokens {
    public static final String hashtags = "!/hashtags";
    public static final String domains = "!/domains";
    public static final String keywords = "!/keywords";

    public static String getDomains() {
        return domains;
    }

    public static String getHashtags() {
        return hashtags;
    }

    public static String getKeywords() {
        return keywords;
    }

    private PlaceNameTokens() {
    }
}
