package io.itdraft.youfollowme.website.api;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import io.itdraft.youfollowme.website.dto.DomainTrend;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path(Paths.DOMAINS_PATH)
public interface DomainTrendResource {
    @GET
    RestAction<List<DomainTrend>> getDomainTrends(@QueryParam(Paths.TIME_RANGE_PARAM) String interval);
}
