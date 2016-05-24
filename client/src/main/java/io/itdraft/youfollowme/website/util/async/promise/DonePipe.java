package io.itdraft.youfollowme.website.util.async.promise;

public interface DonePipe<D, D_OUT, F_OUT, P_OUT> {
    Promise<D_OUT, F_OUT, P_OUT> pipeDone(final D result);
}
