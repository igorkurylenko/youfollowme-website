package io.itdraft.youfollowme.website.util.tuple;

public class Pair<A, B> extends Tuple implements HasValue0<A>, HasValue1<B> {
    public Pair(A value0, B value1) {
        super(value0, value1);
    }

    public A getValue0() {
        return (A) values[0];
    }

    public B getValue1() {
        return (B) values[1];
    }

    public static <A,B> Pair<A,B> with(final A value0, final B value1) {
        return new Pair<>(value0, value1);
    }
}
