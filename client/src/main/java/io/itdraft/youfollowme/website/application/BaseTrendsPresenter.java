package io.itdraft.youfollowme.website.application;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.thirdparty.guava.common.base.Throwables;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import io.itdraft.youfollowme.website.application.event.DisplayStatusEvent;
import io.itdraft.youfollowme.website.application.event.HideStatusEvent;
import io.itdraft.youfollowme.website.dto.Trend;
import io.itdraft.youfollowme.website.resources.AppMessages;
import io.itdraft.youfollowme.website.util.async.promise.DoneCallback;
import io.itdraft.youfollowme.website.util.async.promise.FailCallback;
import io.itdraft.youfollowme.website.util.async.promise.Promise;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class BaseTrendsPresenter<V extends BaseTrendsPresenter.TrendsView, Proxy_ extends Proxy<?>> extends Presenter<V, Proxy_> implements ResizeHandler {

    public interface TrendsView extends View {
        void displayTrends(List<? extends Trend> trends);

        void adjustDisplaySize();

        void clearDisplay();

        void setDisplayMode(TrendsDisplayMode displayMode);

        boolean isCanvasSupported();
    }

    private List<? extends Trend> trends = Collections.emptyList();
    protected final AppMessages appMessages;
    protected final Timer resetTimer = new Timer() {
        public void run() {
            onReset();
        }
    };

    public BaseTrendsPresenter(EventBus eventBus, V view, Proxy_ proxy,
                               GwtEvent.Type<RevealContentHandler<?>> slot,
                               AppMessages appMessages) {
        super(eventBus, view, proxy, slot);

        this.appMessages = appMessages;

        initTrendsDisplayMode();
    }

    private void initTrendsDisplayMode() {
        TrendsDisplayMode trendsDisplayMode =
                getView().isCanvasSupported() ? TrendsDisplayMode.Heap : TrendsDisplayMode.List;

        getView().setDisplayMode(trendsDisplayMode);
    }

    protected void onBind() {
        super.onBind();

        registerHandler(Window.addResizeHandler(this));
    }

    protected void onReset() {
        super.onReset();

        getView().clearDisplay();

        getView().adjustDisplaySize();

        reloadTrends();
    }

    public void onResize(ResizeEvent event) {
        resetTimer.cancel();
        resetTimer.schedule(100);
    }

    private void reloadTrends() {
        DisplayStatusEvent.fire(this, appMessages.loading());

        fetchTrends().done(new DoneCallback<List<? extends Trend>>() {
            public void onDone(List<? extends Trend> result) {
                HideStatusEvent.fire(BaseTrendsPresenter.this);

                onTrendsFetched(result);
            }
        }).fail(new FailCallback<Throwable>() {
            public void onFail(Throwable result) {
                HideStatusEvent.fire(BaseTrendsPresenter.this);

                // todo: correctly handle failure!
                throw new UnsupportedOperationException("Not implemented yet.");
            }
        });
    }

    protected abstract Promise<List<? extends Trend>, Throwable, Void> fetchTrends();

    private void onTrendsFetched(List<? extends Trend> trends) {
        if (trends.isEmpty()) {
            DisplayStatusEvent.fire(this, appMessages.noData());
            return;
        }

        ensureSorted(this.trends = trends);

        hackSwarmAppTrend(trends);//todo: remove this dirty hack

        getView().displayTrends(trends);
    }

    private void hackSwarmAppTrend(List<? extends Trend> trends) {
        if (trends.get(0).toString().contains("swarmapp")) {
            trends.remove(0);
        }
    }

    private void ensureSorted(List<? extends Trend> trends) {
        Collections.sort(trends, new Comparator<Trend>() {
            public int compare(Trend left, Trend right) {
                return (int) (right.getWeight() - left.getWeight());
            }
        });
    }
}
