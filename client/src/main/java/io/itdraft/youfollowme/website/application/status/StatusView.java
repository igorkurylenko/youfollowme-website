package io.itdraft.youfollowme.website.application.status;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class StatusView extends ViewImpl implements StatusPresenter.MyView {
    interface Binder extends UiBinder<Widget, StatusView> {
    }

    @UiField
    InlineLabel statusLabel;

    @Inject
    StatusView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public void setStatusVisible(boolean visible) {
        statusLabel.setVisible(visible);
    }
}
