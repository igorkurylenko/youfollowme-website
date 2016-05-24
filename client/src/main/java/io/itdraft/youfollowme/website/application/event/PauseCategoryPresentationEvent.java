package io.itdraft.youfollowme.website.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class PauseCategoryPresentationEvent extends GwtEvent<PauseCategoryPresentationEvent.PauseCategoryPresentationHandler>{

    public interface PauseCategoryPresentationHandler extends EventHandler {
        void onPauseCategoryPresentationEvent(PauseCategoryPresentationEvent event);
    }

    private static final GwtEvent.Type<PauseCategoryPresentationEvent.PauseCategoryPresentationHandler> TYPE = new GwtEvent.Type<>();

    @Override
    public GwtEvent.Type<PauseCategoryPresentationEvent.PauseCategoryPresentationHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<PauseCategoryPresentationHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PauseCategoryPresentationEvent.PauseCategoryPresentationHandler handler) {
        handler.onPauseCategoryPresentationEvent(this);
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new PauseCategoryPresentationEvent());
    }
}
