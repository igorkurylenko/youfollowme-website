package io.itdraft.youfollowme.website.application.domain;

import com.gwtplatform.mvp.client.UiHandlers;
import io.itdraft.youfollowme.website.dto.DomainTrend;

interface DomainsUIHandlers extends UiHandlers {
    void onTrendSelected(DomainTrend trend);
}
