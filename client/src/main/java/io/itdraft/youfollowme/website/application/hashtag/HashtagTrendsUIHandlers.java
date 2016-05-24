package io.itdraft.youfollowme.website.application.hashtag;

import com.gwtplatform.mvp.client.UiHandlers;
import io.itdraft.youfollowme.website.dto.HashtagTrend;

interface HashtagTrendsUIHandlers extends UiHandlers {
    void onTrendSelected(HashtagTrend trend);
}
