package io.itdraft.youfollowme.website.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {}

    @UiField
    SimplePanel mainContentPanel;

    @UiField
    SimplePanel categoryNavbarPanel;

    @UiField
    SimplePanel timeRangeNavbarPanel;
    @UiField
    SimplePanel statusPanel;

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(ApplicationPresenter.MAIN_CONTENT_SLOT, mainContentPanel);
        bindSlot(ApplicationPresenter.MESSAGE_SLOT, statusPanel);
        bindSlot(ApplicationPresenter.CATEGORY_NAVBAR_SLOT, categoryNavbarPanel);
        bindSlot(ApplicationPresenter.TIME_RANGE_NAVBAR_SLOT, timeRangeNavbarPanel);
    }
}
