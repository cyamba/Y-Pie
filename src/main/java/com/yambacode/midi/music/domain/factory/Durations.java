package com.yambacode.midi.music.domain.factory;

/**
 * @author Christopher Yamba
 */
public class Durations {


    public static final double SIXTEENS = 1d / 4d;
    public static final double EIGTH = 1d / 2d;
    public static final double DOTTED_EIGTH = 3 * SIXTEENS;
    public static final double QUATER = 1;
    public static final double DOTTED_QUARTER = 3 * EIGTH;
    public static final double HALF = 2;
    public static final double DOTTED_HALF = 3 * QUATER;
    public static final double WHOLE = 4;

    /**
     * @param tempo
     * @param duration
     * @return millis
     */
    public static double getDuration(double tempo, double duration) {
        return (1_000 * duration / QUATER) * 60 / tempo;
    }
}
