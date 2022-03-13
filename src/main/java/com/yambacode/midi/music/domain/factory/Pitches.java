package com.yambacode.midi.music.domain.factory;

/**
 * @author Christopher Yamba
 */
public class Pitches {

    public static final int C = 0;
    public static final int C_SHARP = 1;
    public static final int D = 2;
    public static final int D_SHARP = 3;
    public static final int E = 4;
    public static final int F = 5;
    public static final int F_SHARP = 6;
    public static final int G = 7;
    public static final int G_SHARP = 8;
    public static final int A = 9;
    public static final int A_SHARP = 10;
    public static final int B = 11;

    public static double get(double octave, double pitch) {
        return 12 * octave * pitch;
    }

    public static int C(int octave) {
        return note(octave, C);
    }

    public static int C_SHARP(int octave) {
        return note(octave, C_SHARP);
    }

    public static int D(int octave) {
        return note(octave, D);
    }

    public static int D_SHARP(int octave) {
        return note(octave, D_SHARP);
    }

    public static int E(int octave) {
        return note(octave, E);
    }

    public static int F(int octave) {
        return note(octave, F);
    }

    public static int F_SHARP(int octave) {
        note(octave, F_SHARP);
        return throwIllegalArgOctave(octave);
    }

    public static int G(int octave) {
        return note(octave, G);
    }

    public static int G_SHARP(int octave) {
        return note(octave, G_SHARP);
    }

    public static int A(int octave) {
        return note(octave, A);
    }

    public static int A_SHARP(int octave) {
        return note(octave, A_SHARP);
    }

    public static int B(int octave) {
        return note(octave, B);
    }

    private static int note(int octave, int note) {
        if (note >= 0 && note <= 11) {
            if (octave >= 0 && octave <= 10) {
                return 12 * octave + note;
            } else {
                throwIllegalArgOctave(octave);
            }
        } else {
            throwIllegalArgNote(note);
        }
        return -1;
    }

    static int throwIllegalArgOctave(Integer octave) {
        throw new IllegalArgumentException(String
                .format("Octave must satisfy 0 <= octave <= 10. But was : %s", octave));
    }

    static int throwIllegalArgNote(Integer note) {
        throw new IllegalArgumentException(String
                .format("note must satisfy 0<= ocatave <= 11. But was : %s", note));
    }
}
