package com.yambacode.midi.music.input;

import com.yambacode.midi.music.PathToConstants;
import com.yambacode.midi.music.domain.Note;
import com.yambacode.midi.music.domain.Transposer;
import com.yambacode.midi.music.instruments.Melodizer;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Christopher Yamba
 */
public class FileReaderTest {

    @Test
    public void readPiToMidiMelody() {
        List<Note> notes = FileReader.parseMidiNotes(PathToConstants.PATH_TO_PI);
        Melodizer melodizer = new Melodizer();
        melodizer.start();
        melodizer.playEnding(notes);
        melodizer.stop();
    }

    @Test
    public void readPiAsDiatonic() {
        List<Note> notes = FileReader.parseDiatonics(PathToConstants.PATH_TO_PI);
        Melodizer melodizer = new Melodizer();
        melodizer.start();
        melodizer.playEnding(notes.stream().map(note -> Transposer.transpose(12 * 4, note))
                .collect(Collectors.toList()));
        melodizer.stop();
    }

    @Test
    public void readPiAsDiatonicBillion() {
        //TO test with real file download and store locally
        final Melodizer melodizer = new Melodizer();
        melodizer.start();

        FileReader.parseDiatonicsStream(melodizer,PathToConstants.PATH_TO_PI_BILLION)
                .map(note -> Transposer.transpose(12 * 4, note))
                .peek(System.out::println);
        melodizer.stop();
    }


}