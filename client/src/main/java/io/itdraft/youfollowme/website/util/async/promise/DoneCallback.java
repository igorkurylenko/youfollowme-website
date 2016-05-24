package io.itdraft.youfollowme.website.util.async.promise;

public interface DoneCallback<D> {
    void onDone(final D result);
}
