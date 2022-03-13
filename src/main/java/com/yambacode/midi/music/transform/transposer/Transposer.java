package com.yambacode.midi.music.transform.transposer;

import com.yambacode.midi.music.model.Note;

/**
 * @author Christopher Yamba
 */
public class Transposer {

    public static int transpose(int transposition, int currentPitch) {
        return currentPitch + transposition;
    }

    public static Note transpose(int transposition, Note note) {
        return Note.of(note.getDuration(), note.getPitch() + transposition);
    }
}
