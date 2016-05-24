package io.itdraft.youfollowme.website.util.tuple;

public class Unit<A> extends Tuple implements HasValue0<A>{

    public Unit(A value0) {
        super(value0);
    }

    public A getValue0() {
        return (A) values[0];
    }

    public static <A> Unit<A> with(final A value0) {
        return new Unit<>(value0);
    }
}
