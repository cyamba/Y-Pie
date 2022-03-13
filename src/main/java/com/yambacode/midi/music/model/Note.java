package com.yambacode.midi.music.model;

/**
 * Class representing a musical note.
 *
 * @author Christopher Yamba
 */
public class Note implements Element {

    //TODO BPM adjusted noteduration values.
    private double duration;

    //As enum with named pitches.
    private double pitch;

    private Note(double duration, double pitch) {
        this.duration = duration;
        this.pitch = pitch;
    }

    public static Note of(double duration, double pitch) {
        return new Note(duration, pitch);
    }

    public double getDuration() {
        return duration;
    }

    public double getPitch() {
        return pitch;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", duration,pitch);
    }
}
