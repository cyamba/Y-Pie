package com.yambacode.midi.music.instruments;


import com.yambacode.midi.music.domain.Note;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Christopher Yamba
 */
public class Melodizer {

    public static final int VELOCITY = 60;
    MidiChannel[] channels;
    private Synthesizer synthesizer;

    public Melodizer() {
        try {
            this.synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

        this.channels = this.synthesizer.getChannels();
    }

    public void play(Note... notes) {
        Stream.of(notes).forEachOrdered(note -> play(note));
    }

    public void play(List<Note> notes) {
        notes.stream().forEachOrdered(this::play);
    }

    public void play(Stream<Note> notes) {
        notes.forEachOrdered(this::play);
    }

    public void playEnding(List<Note> notes) {
        for (int i = 0; i < notes.size() - 1; i++) {
            play(notes.get(i));
        }
        Note last = notes.get(notes.size() - 1);
        play(last, 5);
    }

    public void playEndingStream(Melodizer melodizer, Stream<Note> notes) {
        notes.forEachOrdered(note -> play(note));
    }

    public void play(Note note) {
        System.out.println(note );
        play(note, 1);
    }

    public void play(Note note, int durationFactor) {
        channels[0].noteOn((int) note.getPitch(), VELOCITY);
        try {
            Thread.sleep((int) note.getDuration() * durationFactor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channels[0].noteOff(VELOCITY);
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
