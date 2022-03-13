package com.yambacode.midi.music.domain;


import com.yambacode.midi.music.domain.transposer.NoteMapper;

/**
 * @author Christopher Yamba
 */
public class Notes {
    public final static Note _0 = NoteMapper.toMidiNote(Note.of(1000, 0));

}
