package com.yambacode.midi.music.domain.transposer;


import com.yambacode.midi.music.domain.Note;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Christopher Yamba
 */
public class NoteMapper {
    

    private static final Function<Note, Note> toMidiNote = note -> {
        int midiPitch = (int) note.getPitch() % 10; //0..9
        double duration = note.getDuration();
        return switch (midiPitch) {
            case 0 -> Note.of(duration, 16);
            case 1 -> Note.of(duration, 0);
            case 2 -> Note.of(duration, 2);
            case 3 -> Note.of(duration, 4);
            case 4 -> Note.of(duration, 5);
            case 5 -> Note.of(duration, 7);
            case 6 -> Note.of(duration, 9); //diatonic a
            case 7 -> Note.of(duration, 11); //diatonic b
            case 8 -> Note.of(duration, 12);
            case 9 -> Note.of(duration, 14);
            default -> throw new IllegalArgumentException("illegal value for note : " + note);
        };
    };

    public static Stream<Note> toMidiNote(Stream<Note> noteStream) {
        return noteStream.map(toMidiNote);
    }

    public static Note toMidiNote(Note note){
        return toMidiNote.apply(note);
    }
}