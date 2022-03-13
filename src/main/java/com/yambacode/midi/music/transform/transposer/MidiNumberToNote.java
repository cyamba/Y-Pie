package com.yambacode.midi.music.transform.transposer;

import com.yambacode.midi.music.model.Note;

import java.util.function.Function;

/**
 * public static Function<Byte, Double> toNumberToFrequency = n -> 440d * Math.pow(2d, (n - 69d) / 12d);
 */
public class MidiNumberToNote {


    public static Function<Byte, Double> toNumberToFrequency = n -> 440d * Math.pow(2d, (n - 69d) / 12d);

    /**
     * @param midiNumber 0..127
     * @return Note with default duration.
     */
    public static Note toNote(Byte midiNumber) {
        return null;
    }


    public static void main(String... args) {

    }


}
