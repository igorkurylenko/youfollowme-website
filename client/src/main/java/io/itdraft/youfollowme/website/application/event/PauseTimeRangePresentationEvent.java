package io.itdraft.youfollowme.website.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class PauseTimeRangePresentationEvent extends GwtEvent<PauseTimeRangePresentationEvent.PauseTimeRangePresentationHandler> {

    public interface PauseTimeRangePresentationHandler extends EventHandler {
        void onPauseTimeRangePresentationEvent(PauseTimeRangePresentationEvent event);
    }

    private static final GwtEvent.Type<PauseTimeRangePresentationEvent.PauseTimeRangePresentationHandler> TYPE = new GwtEvent.Type<>();

    @Override
    public GwtEvent.Type<PauseTimeRangePresentationEvent.PauseTimeRangePresentationHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<PauseTimeRangePresentationHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PauseTimeRangePresentationEvent.PauseTimeRangePresentationHandler handler) {
        handler.onPauseTimeRangePresentationEvent(this);
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new PauseTimeRangePresentationEvent());
    }
}
