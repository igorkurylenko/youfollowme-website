package io.itdraft.youfollowme.website.util.async.promise;

public interface ProgressCallback<P> {
    void onProgress(final P progress);
}
