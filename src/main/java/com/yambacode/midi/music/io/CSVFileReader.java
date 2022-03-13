package com.yambacode.midi.music.io;

import com.yambacode.midi.music.PathToConstants;
import com.yambacode.midi.music.model.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Parsing Csv files.
 *
 * Observe some potential platform dependent problems.
 *  "git add .
 * warning: CRLF will be replaced by LF in src/main/resources/music/midi-notes-mapping.csv.
 * The file will have its original line endings in your working directory."
 */
public class CSVFileReader {


    public static List<Note> parseCSV(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.peek(System.out::println);

        } catch (IOException ie) {
            ie.printStackTrace();
        }

        return new LinkedList<>();
    }

    public static void main(String[] args) {
        parseCSV(PathToConstants.PATH_TO_MIDI_NOTES_MAPPING);
    }
}
