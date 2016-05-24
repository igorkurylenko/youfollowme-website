package io.itdraft.youfollowme.website.util.tuple;

public class Triplet<A, B, C> extends Tuple implements HasValue0<A>, HasValue1<B>, HasValue2<C> {

    public Triplet(A value0, B value1, C value2) {
        super(value0, value1, value2);
    }

    public A getValue0() {
        return (A) values[0];
    }

    public B getValue1() {
        return (B) values[1];
    }

    public C getValue2() {
        return (C) values[2];
    }

    public static <A, B, C> Triplet<A, B, C> with(final A value0, final B value1, final C value2) {
        return new Triplet<>(value0, value1, value2);
    }
}
