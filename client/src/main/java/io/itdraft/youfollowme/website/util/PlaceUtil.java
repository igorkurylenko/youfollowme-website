package io.itdraft.youfollowme.website.util;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import io.itdraft.youfollowme.website.place.PlaceParams.TimeRange;

public class PlaceUtil {

    public static TimeRange getTimeRangeParamValue(final PlaceRequest placeRequest) {
        return getTimeRangeParamValue(placeRequest, TimeRange.DEFAULT_TIME_RANGE);
    }

    public static TimeRange getTimeRangeParamValue(final PlaceRequest placeRequest, final TimeRange defaultValue) {
        TimeRange timeRange = defaultValue;

        try {
            String parameterValue = placeRequest.getParameter(
                    TimeRange.PARAM_NAME, timeRange.name());

            timeRange = TimeRange.valueOf(parameterValue);

        } catch (Exception ignored) {
        }

        return timeRange;
    }

    public static PlaceRequest mutateNameToken(final PlaceRequest curPlaceRequest, final String nameToken) {
        return new PlaceRequest.Builder(curPlaceRequest)
                .nameToken(nameToken)
                .build();
    }

    public static PlaceRequest mutateTimeRangeParam(final PlaceRequest curPlaceRequest, final TimeRange timeRangeParam) {
        return new PlaceRequest.Builder(curPlaceRequest)
                .with(TimeRange.PARAM_NAME, timeRangeParam.name())
                .build();
    }

    public static TimeRange getCurTimeRange(final PlaceManager placeManager) {
        PlaceRequest curPlaceRequest = placeManager.getCurrentPlaceRequest();

        return getTimeRangeParamValue(curPlaceRequest);
    }

    private PlaceUtil() {
    }
}
