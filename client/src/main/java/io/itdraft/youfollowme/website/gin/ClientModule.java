package io.itdraft.youfollowme.website.gin;


import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;
import io.itdraft.youfollowme.website.api.DomainTrendResourceDelegateProxy;
import io.itdraft.youfollowme.website.api.HashtagTrendResourceDelegateProxy;
import io.itdraft.youfollowme.website.application.ApplicationModule;
import io.itdraft.youfollowme.website.api.Paths;
import io.itdraft.youfollowme.website.place.PlaceNameTokens;
import io.itdraft.youfollowme.website.resources.ResourceLoader;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder()
                .tokenFormatter(RouteTokenFormatter.class)
                .defaultPlace(PlaceNameTokens.getHashtags())
                .errorPlace(PlaceNameTokens.getHashtags())
                .unauthorizedPlace(PlaceNameTokens.getHashtags())
                .build());

        bindConstant().annotatedWith(RestApplicationPath.class).to(Paths.ROOT_PATH);
        bind(ResourceLoader.class).asEagerSingleton();

        RestDispatchAsyncModule.Builder dispatchBuilder = new RestDispatchAsyncModule.Builder();
        install(dispatchBuilder.build());

        install(new ApplicationModule());

        bind(HashtagTrendResourceDelegateProxy.class).asEagerSingleton();
        bind(DomainTrendResourceDelegateProxy.class).asEagerSingleton();
    }
}
