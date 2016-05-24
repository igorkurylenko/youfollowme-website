package io.itdraft.youfollowme.website.application.keyword;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import io.itdraft.youfollowme.website.application.BaseTrendsViewWithUiHandlers;
import io.itdraft.youfollowme.website.resources.AppConstants;

import javax.inject.Inject;

public class KeywordTrendsView extends BaseTrendsViewWithUiHandlers<KeywordTrendsUiHandlers> implements KeywordTrendsPresenter.MyView {
    interface Binder extends UiBinder<Widget, KeywordTrendsView> {
    }

    @UiField
    HTMLPanel content;

    @Inject
    KeywordTrendsView(Binder binder, AppConstants appConstants) {
        super(appConstants);
        initWidget(binder.createAndBindUi(this));
    }

    protected Canvas getCanvasIfSupported() {
        return null;
    }
}
