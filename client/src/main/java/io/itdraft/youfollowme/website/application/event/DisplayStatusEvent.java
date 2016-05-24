package io.itdraft.youfollowme.website.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;


public class DisplayStatusEvent extends GwtEvent<DisplayStatusEvent.DisplayStatusHandler> {
    public interface DisplayStatusHandler extends EventHandler {
        void onDisplayStatus(DisplayStatusEvent event);
    }

    private static final Type<DisplayStatusHandler> TYPE = new Type<>();

    private String status;

    DisplayStatusEvent(
            String status) {
        this.status = status;
    }

    public static Type<DisplayStatusHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, String status) {
        source.fireEvent(new DisplayStatusEvent(status));
    }

    public Type<DisplayStatusHandler> getAssociatedType() {
        return TYPE;
    }

    public String getStatus() {
        return status;
    }

    protected void dispatch(DisplayStatusHandler handler) {
        handler.onDisplayStatus(this);
    }
}
