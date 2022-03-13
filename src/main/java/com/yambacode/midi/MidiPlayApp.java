package com.yambacode.midi;

import com.yambacode.midi.music.transform.transposer.MidiNumberToNote;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * A simple demo.
 * @author Christopher Yamba
 */
public class MidiPlayApp {

    public static final byte A4_CONCERT_PITCH = 69;

    public static final int VELOCITY_OF_NOTE = 80;

    public static void main(String[] args) {
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            MidiChannel[] channels = synthesizer.getChannels();

            channels[0].noteOn(80, 80);
            channels[0].noteOn(60, 60);
            Thread.sleep(2000);

            channels[0].noteOff(60);
            channels[0].noteOff(80);

            Thread.sleep(2000);
            channels[0].noteOn(90, 80);

            Thread.sleep(2000);
            channels[0].noteOff(90);

            someMoreStuff(channels);

            synthesizer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * MIDI note number |	Key number (Organ)|	Key number (Piano)|	Note names (English) |	Note names (German) |	Frequency (Equal tuning at 440 Hz)
     * 69	34	49	A4 concert pitch	a’ Kammerton	440.00
     *
     * @param channels
     * @throws InterruptedException
     */
    public static void someMoreStuff(MidiChannel[] channels) throws InterruptedException {

        MidiNumberToNote.toNote(A4_CONCERT_PITCH);
        Thread.sleep(2000);
        channels[0].noteOn(A4_CONCERT_PITCH, 80);

        Thread.sleep(2000);
        channels[0].noteOff(A4_CONCERT_PITCH);

        //there are 16 channels
        for (MidiChannel chan : channels) {
            IntStream.rangeClosed(0, Byte.MAX_VALUE).forEachOrdered(
                    i -> {
                        if (RAND.nextBoolean()) {
                            int sleepMillis = RAND.nextInt(25, 100);
                            for (int j = 0; j < sleepMillis; j++) {
                                int note = RAND.nextInt(50, 100);
                                _play(chan, (byte) note, sleepMillis);
                            }
                        } else {
                            _play(chan, (byte) i);
                        }
                    }

            );
        }
    }

    private static final Random RAND = new Random();

    private static void _play(MidiChannel chan, byte i) {
        try {
            play(i, 250, chan);
            play((byte) (new Random().nextInt(128)), 250, chan);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void _play(MidiChannel chan, byte miNumber, int durationMillis) {
        try {
            play(miNumber, durationMillis, chan);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void play(byte midiNote, int sleepMillis, MidiChannel channel) throws InterruptedException {
        Thread.sleep(sleepMillis);
        channel.noteOn(midiNote, VELOCITY_OF_NOTE);

        Thread.sleep(sleepMillis);
        channel.noteOff(midiNote);
    }

}
