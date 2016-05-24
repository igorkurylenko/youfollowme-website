package io.itdraft.youfollowme.website.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class PlayTimeRangePresentationEvent extends GwtEvent<PlayTimeRangePresentationEvent.PlayTimeRangePresentationHandler> {

    public interface PlayTimeRangePresentationHandler extends EventHandler {
        void onPlayTimeRangePresentationEvent(PlayTimeRangePresentationEvent event);
    }

    private static final GwtEvent.Type<PlayTimeRangePresentationEvent.PlayTimeRangePresentationHandler> TYPE = new GwtEvent.Type<>();

    @Override
    public GwtEvent.Type<PlayTimeRangePresentationEvent.PlayTimeRangePresentationHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<PlayTimeRangePresentationHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayTimeRangePresentationEvent.PlayTimeRangePresentationHandler handler) {
        handler.onPlayTimeRangePresentationEvent(this);
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new PlayTimeRangePresentationEvent());
    }
}
