package com.yambacode.midi.music.app;


import com.yambacode.midi.music.instruments.Melodizer;

/**
 * @author Christopher Yamba
 */
public class MidiFactory {

    public static Melodizer getMidiChannel(){
        return new Melodizer();
    }
}
