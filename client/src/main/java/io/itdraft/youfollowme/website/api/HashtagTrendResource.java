package io.itdraft.youfollowme.website.api;

import com.gwtplatform.dispatch.rest.shared.RestAction;
import io.itdraft.youfollowme.website.dto.HashtagTrend;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(Paths.HASHTAGS_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface HashtagTrendResource {
    @GET
    RestAction<List<HashtagTrend>> getHashtagTrends(@QueryParam(Paths.TIME_RANGE_PARAM) String timeRange);
}
