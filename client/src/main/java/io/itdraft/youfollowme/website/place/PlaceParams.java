package io.itdraft.youfollowme.website.place;

public class PlaceParams {
    public enum TimeRange {
        hour, day, week, month;

        public static final String PARAM_NAME = "timerange";
        public static final TimeRange DEFAULT_TIME_RANGE = day;
    }

    private PlaceParams() {
    }
}
