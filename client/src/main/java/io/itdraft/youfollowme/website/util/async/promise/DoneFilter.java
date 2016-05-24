package io.itdraft.youfollowme.website.util.async.promise;

public interface DoneFilter<D, D_OUT> {
    D_OUT filterDone(final D result);
}
