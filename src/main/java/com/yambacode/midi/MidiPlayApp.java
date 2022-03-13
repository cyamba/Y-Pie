package com.yambacode.midi;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * @author Christopher Yamba
 */
public class MidiPlayApp {

    public static void main(String[] args) {
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            MidiChannel[] channels = synthesizer.getChannels();

            channels[0].noteOn(60, 60);
            channels[0].noteOn(80, 80);
            Thread.sleep(2000);
            channels[0].noteOff(60);

            synthesizer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
