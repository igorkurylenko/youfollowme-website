package io.itdraft.youfollowme.website.util.async;

import com.google.gwt.core.client.Scheduler;

import java.util.Iterator;

public class AsyncUtil {

    public static final Scheduler SCHEDULER = Scheduler.get();

    public static <T> void foreachIncremental(final Iterable<T> iterable,
                                              final IteratorCallback<? super T> callback) {
        Iterator<T> iterator = iterable.iterator();

        foreachIncremental(iterator, callback);
    }

    private static <T> void foreachIncremental(final Iterator<T> iterator,
                                               final IteratorCallback<? super T> callback) {
        if (iterator.hasNext()) {
            SCHEDULER.scheduleIncremental(new Scheduler.RepeatingCommand() {
                public boolean execute() {
                    return iterator.hasNext() &&
                            callback.f(iterator.next()) &&
                            iterator.hasNext();
                }
            });
        }
    }

    public static <T> void foreachDeferred(final Iterable<T> iterable,
                                           final IteratorCallback<? super T> callback) {
        SCHEDULER.scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                for (T value : iterable) if (!callback.f(value)) break;
            }
        });
    }
}
