package io.itdraft.youfollowme.website.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class PlayCategoryPresentationEvent extends GwtEvent<PlayCategoryPresentationEvent.PlayCategoryPresentationHandler>{

    public interface PlayCategoryPresentationHandler extends EventHandler {
        void onPlayCategoryPresentationEvent(PlayCategoryPresentationEvent event);
    }

    private static final Type<PlayCategoryPresentationHandler> TYPE = new Type<>();

    @Override
    public Type<PlayCategoryPresentationHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<PlayCategoryPresentationHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayCategoryPresentationHandler handler) {
        handler.onPlayCategoryPresentationEvent(this);
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new PlayCategoryPresentationEvent());
    }
}
