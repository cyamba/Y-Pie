package com.yambacode.midi.music.instruments;


import com.yambacode.midi.music.model.Note;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.List;
import java.util.stream.Stream;

/**
 * A musical instrument abstraction in front of jdk:s midi sound api
 * that takes notes and duration as input and plays them back.
 *
 * @author Christopher Yamba
 */
public class Melodizer {

    /**
     * BPM - Beats per minute.
     */
    public static final int BPM = 60;

    private MidiChannel[] channels;

    private Synthesizer synthesizer;

    public Melodizer() {
        try {
            this.synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

        this.channels = this.synthesizer.getChannels();
    }

    /**
     * @param notes
     */
    public void play(Note... notes) {
        Stream.of(notes).forEachOrdered(note -> play(note));
    }

    /**
     * @param notes
     */
    public void play(List<Note> notes) {
        notes.stream().forEachOrdered(this::play);
    }

    /**
     * @param notes
     */
    public void play(Stream<Note> notes) {
        notes.forEachOrdered(this::play);
    }

    /**
     * @param notes
     */
    public void playEnding(List<Note> notes) {
        for (int i = 0; i < notes.size() - 1; i++) {
            play(notes.get(i));
        }
        Note last = notes.get(notes.size() - 1);
        play(last, 5);
    }

    /**
     * @param melodizer
     * @param notes
     */
    public void playEndingStream(Melodizer melodizer, Stream<Note> notes) {
        notes.forEachOrdered(note -> play(note));
    }

    /**
     * @param note
     */
    public void play(Note note) {
        System.out.println(note);
        play(note, 1);
    }

    /**
     * @param note
     * @param durationFactor
     */
    public void play(Note note, int durationFactor) {
        if (durationFactor < 1) {
            throw new IllegalArgumentException("The durationFactor must be non-zero. durationfactor: " + durationFactor);
        }
        channels[0].noteOn((int) note.getPitch(), BPM);
        try {
            Thread.sleep((int) note.getDuration() * durationFactor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channels[0].noteOff(BPM);
    }

    public void start() {
        try {
            synthesizer.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        synthesizer.close();
    }
}
