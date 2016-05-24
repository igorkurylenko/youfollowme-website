package io.itdraft.youfollowme.website.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;


public class HideStatusEvent extends GwtEvent<HideStatusEvent.HideStatusHandler> {
    public interface HideStatusHandler extends EventHandler {
        void onHideStatus(HideStatusEvent event);
    }

    private static final Type<HideStatusHandler> TYPE = new Type<>();

    public static Type<HideStatusHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new HideStatusEvent());
    }

    public Type<HideStatusHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(HideStatusHandler handler) {
        handler.onHideStatus(this);
    }
}
