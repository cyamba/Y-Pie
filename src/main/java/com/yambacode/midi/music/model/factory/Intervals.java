package com.yambacode.midi.music.model.factory;

/**
 * @author Christopher Yamba
 */
public class Intervals {
    public static final int OCTAVE = 12;
    public static final int FIFTH = 7;

    public enum Interval {
        UNISON(0),
        PRIM(0),
        MINOR_SECOND(1),
        MAJOR_SECOND(2),
        MINOR_THIRD(3),
        MAJOR_THIRD(4),
        PERFECT_FOURTH(5),
        TRITONE(6),
        AUGMENTED_FOURTH(6),
        DIMINISHED_FIFTH(6),
        PERFECT_FIFTH(7),
        MINOR_SIXTH(8),
        MAJOR_SIXTH(9),
        MINOR_SEVENTH(10),
        MAJOR_SEVENTH(11),
        PERFECT_OCTAVE(12),
        MINOR_NINTH(13),
        MAJOR_NINTH(14),
        MINOR_TENTH(15),
        MAJOR_TENTH(16),
        PERFECT_ELEVENTH(17),
        AUGMENTED_ELEVENTH(18),
        PERFECT_TWELVETH(19),
        MINOR_THIRTEENTH(20),
        MAJOR_THIRTEENTH(21);

        ;

        private int value;

        Interval(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
