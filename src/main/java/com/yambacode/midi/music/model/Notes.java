package com.yambacode.midi.music.model;


import com.yambacode.midi.music.transform.transposer.NoteMapper;

/**
 * @author Christopher Yamba
 */
public class Notes {
    public final static Note _0 = NoteMapper.toMidiNote(Note.of(1000, 0));

}
