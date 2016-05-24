package io.itdraft.youfollowme.website.application.status;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import io.itdraft.youfollowme.website.application.event.DisplayStatusEvent;
import io.itdraft.youfollowme.website.application.event.HideStatusEvent;

public class StatusPresenter extends PresenterWidget<StatusPresenter.MyView>
        implements DisplayStatusEvent.DisplayStatusHandler,
        HideStatusEvent.HideStatusHandler {

    interface MyView extends View {
        void setStatus(String status);

        void setStatusVisible(boolean visible);
    }

    @Inject
    StatusPresenter(
            EventBus eventBus,
            MyView view) {
        super(eventBus, view);
    }

    public void onDisplayStatus(DisplayStatusEvent event) {
        getView().setStatus(event.getStatus());
        getView().setStatusVisible(true);
    }

    public void onHideStatus(HideStatusEvent event) {
        getView().setStatusVisible(false);
    }

    protected void onBind() {
        addRegisteredHandler(DisplayStatusEvent.getType(), this);
        addRegisteredHandler(HideStatusEvent.getType(), this);
    }
}
