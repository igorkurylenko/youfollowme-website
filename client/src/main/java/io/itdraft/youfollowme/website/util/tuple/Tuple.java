package io.itdraft.youfollowme.website.util.tuple;

import java.util.Objects;

public abstract class Tuple {
    protected Object[] values;

    public Tuple(Object... values) {
        this.values = values;
    }
}
