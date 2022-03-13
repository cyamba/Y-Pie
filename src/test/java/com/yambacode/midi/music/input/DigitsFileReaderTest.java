package com.yambacode.midi.music.input;

import com.yambacode.midi.music.PathToConstants;
import com.yambacode.midi.music.io.input.DigitsFileReader;
import com.yambacode.midi.music.model.Note;
import com.yambacode.midi.music.transform.transposer.Transposer;
import com.yambacode.midi.music.instruments.Melodizer;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Christopher Yamba
 */
public class DigitsFileReaderTest {

    @Test
    public void readPiToMidiMelody() {
        List<Note> notes = DigitsFileReader.parseMidiNotes(PathToConstants.PATH_TO_PI);
        Melodizer melodizer = new Melodizer();
        melodizer.start();
        melodizer.playEnding(notes);
        melodizer.stop();
    }

    @Test
    public void readPiAsDiatonic() {
        List<Note> notes = DigitsFileReader.parseDiatonic(PathToConstants.PATH_TO_PI);
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

        DigitsFileReader.parseDiatonicStream(melodizer,PathToConstants.PATH_TO_PI_BILLION)
                .map(note -> Transposer.transpose(12 * 4, note))
                .peek(System.out::println);
        melodizer.stop();
    }


}