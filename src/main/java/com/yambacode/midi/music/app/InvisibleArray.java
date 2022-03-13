package com.yambacode.midi.music.app;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * @author Christopher Yamba
 */
public class InvisibleArray {
    private BigInteger state = BigInteger.ZERO;

    public InvisibleArray(BigInteger state) {
        this.state = state;
    }

    public InvisibleArray() {
    }

    public int set(Integer... xs) {
        return Stream.of(xs).map(i -> set(i)).reduce((a, b) ->b).get();
    }

    public int set(int i) {
        BigInteger result = BigInteger.valueOf(2).pow(i);
        state = state.add(result);
        if (state.intValue() < Integer.MAX_VALUE && state.intValue() < 0) {
            throw new IllegalStateException("to large number");
        }
        return state.intValue();
    }

    public boolean isAvailable(int n) {
        int newPosition = (int) (Math.pow(2, n));
        int similarPositions = state.intValue() & newPosition;
        return similarPositions != newPosition;
    }
}
