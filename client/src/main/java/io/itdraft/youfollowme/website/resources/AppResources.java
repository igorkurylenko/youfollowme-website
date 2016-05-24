package io.itdraft.youfollowme.website.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppResources extends ClientBundle {

    interface Normalize extends CssResource {
    }

    interface Style extends CssResource {
        @ClassName("navbar-item")
        String navbarItem();

        String wrapper();

        String push();

        String footer();

        String canvas();

        String container();

        String navbar();

        String highlighted();

        String icon();

        String status();
    }

    @Source("gss/normalize.gss")
    Normalize normalize();

    @Source("gss/style.gss")
    Style style();
}
