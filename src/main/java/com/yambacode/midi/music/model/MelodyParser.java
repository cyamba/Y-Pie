package com.yambacode.midi.music.model;

import com.yambacode.midi.music.model.factory.Durations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.yambacode.midi.music.model.factory.Durations.QUATER;
/**
 * @author Christopher Yamba
 */
public class MelodyParser {

    public static final int DEFAULT_OCTAVE = 5;

    public static List<Note> parse(String str, Integer octave) {
        int oct = octave != null ? octave : DEFAULT_OCTAVE;
        return IntStream.range(0, 16).mapToObj(i -> {
            double pitch = Double.parseDouble(str.substring(i, i + 1));
            return Note.of(Durations.getDuration(120, QUATER), oct * pitch);
        }).collect(Collectors.toList());
    }

    public static List<Note> parse(List<Double> doubles) {
        return doubles.stream().map(pitch ->
                Note.of(Durations.getDuration(120, QUATER), DEFAULT_OCTAVE * pitch)
        ).collect(Collectors.toList());
    }

    public static List<Note> parse(String str) {
        return IntStream.range(0, str.length()).mapToObj(i -> {
            double pitch = Double.parseDouble(str.substring(i, i + 1));
            return Note.of(Durations.getDuration(120, QUATER), DEFAULT_OCTAVE * pitch);
        }).collect(Collectors.toList());
    }

}
