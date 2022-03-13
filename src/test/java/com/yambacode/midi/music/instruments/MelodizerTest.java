package com.yambacode.midi.music.instruments;
import com.yambacode.midi.music.domain.MelodyParser;
import com.yambacode.midi.music.domain.Note;

import org.junit.Test;

import java.util.List;

import static com.yambacode.midi.music.domain.Transposer.*;
import static com.yambacode.midi.music.domain.factory.Durations.*;
import static com.yambacode.midi.music.domain.factory.Pitches.*;
import static com.yambacode.midi.music.domain.factory.Intervals.*;
import static java.util.stream.Collectors.*;


/**
 * @author Christopher Yamba
 */
public class MelodizerTest {

    private Melodizer melodizer = new Melodizer();

    public static final Note C = Note.of(getDuration(120, QUATER), C(5));
    public static final Note G = Note.of(getDuration(120, QUATER), G(5));
    public static final Note A = Note.of(getDuration(120, QUATER), A(5));
    public static final Note G_HALF = Note.of(getDuration(120, HALF), G(5));
    public static final Note F = Note.of(getDuration(120, QUATER), F(5));
    public static final Note E = Note.of(getDuration(120, QUATER), E(5));
    public static final Note D = Note.of(getDuration(120, QUATER), D(5));
    public static final Note C_HALF = Note.of(getDuration(120, HALF), C(5));


    @Test
    public void testPlay() {
        melodizer = new Melodizer();
        melodizer.start();
        melodizer.play(notes());
        melodizer.play(notes());
        melodizer.stop();
    }

    @Test
    public void testPlay2() {
        melodizer = new Melodizer();
        melodizer.start();
        melodizer.play(parsedNotes());
        melodizer.stop();
    }

    @Test
    public void testPlay16FirstDecimalsOfPi() {
        melodizer = new Melodizer();
        melodizer.start();
        List<Note> pi_16_decimals = parsedNotes();
        melodizer.play(pi_16_decimals);
        melodizer.play(pi_16_decimals.stream().map(note -> transpose(OCTAVE, note)));
        melodizer.play(pi_16_decimals.stream().map(note -> transpose(2 * OCTAVE, note)));
        melodizer.play(pi_16_decimals.stream().map(note -> transpose(3 * OCTAVE, note)));
        melodizer.play(pi_16_decimals.stream().map(note -> transpose(4 * OCTAVE, note)));
        melodizer.playEnding(pi_16_decimals.stream().map(note -> transpose(5 * OCTAVE, note)).collect(toList()));
        melodizer.stop();
    }

    @Test
    public void testWithParser() {
        melodizer = new Melodizer();
        melodizer.start();
        melodizer.play(parsedNotes());
        melodizer.stop();

    }

    private List<Note> parsedNotes() {
        return MelodyParser.parse("314159265358979323846264338327950");
    }

    private Note[] notes() {
        System.out.println(88 * 88);
        Note[] notes = {
                C, C, G, G, A, A, G_HALF,
                F, F, E, E, D, D, C_HALF
        };
        return notes;
    }
}