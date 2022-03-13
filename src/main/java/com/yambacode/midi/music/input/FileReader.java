package com.yambacode.midi.music.input;


import com.yambacode.midi.music.domain.MelodyParser;
import com.yambacode.midi.music.domain.Note;
import com.yambacode.midi.music.domain.Transposer;
import com.yambacode.midi.music.domain.factory.Intervals;
import com.yambacode.midi.music.domain.transposer.NoteMapper;
import com.yambacode.midi.music.instruments.Melodizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Christopher Yamba
 */
public class FileReader {

    public static final String SRC_MAIN_RESOURCES = "src/main/resources/";
    public static final double HALF_A_SECOND = 500d;
    public static final int _0_IN_ASCII_48_OFFSET = '0';

    public static List<Note> parseNotes(String filePath) {

        Function<String, Double> toDouble = (String s) -> Double.valueOf(s);

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            List<Note> collect = stream.map(line ->
                    Note.of(toDouble.apply(line.split("_")[0]), toDouble.apply(line.split("_")[1])))
                    .collect(toList());
            return collect;
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return new LinkedList<>();
    }


    public static List<Note> parseJustPitches(String filePath) {

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            List<Note> collect = stream.flatMap(line -> getNoteStream(line))
                    .map(_double -> Note.of(500d, _double))
                    .collect(toList());
            return collect;
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return new LinkedList<>();
    }

    private static Stream<Double> getNoteStream(String line) {
        return line.chars().mapToObj(i -> (double) (i - 48));
    }

    public static List<Note> parseJustNotesForPi(String filePath) {
        return parseJustNotesForPi(filePath, 3);
    }

    public static List<Note> parseJustNotesForPi(String filePath, int octaveFactor) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream
                    .flatMap(line -> MelodyParser.parse(line, octaveFactor).stream())
                    .map(note -> Transposer.transpose(octaveFactor * Intervals.OCTAVE, note))
                    .collect(toList());

        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return new LinkedList<>();
    }

    public static List<Note> parseMidiNotes(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream
                    .flatMap(line -> getIntStream(line))
                    .map(i -> Note.of(HALF_A_SECOND, i))
                    .map(note->Transposer.transpose(Intervals.OCTAVE*3,note))
                    .collect(toList());

        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return new LinkedList<>();
    }

    public static List<Note> parseDiatonics(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream
                    .flatMap(line -> getIntStream(line))
                    .map(i -> Note.of(HALF_A_SECOND, i))
                    .map(NoteMapper::toMidiNote)
                    .collect(toList());

        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return new LinkedList<>();
    }

    public static Stream<Note> parseDiatonicsStream(Melodizer melodizer, String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream
                    .flatMap(line -> getIntStream(line))
                    .map(i -> Note.of(HALF_A_SECOND, i))
                    .map(NoteMapper::toMidiNote)
                    .peek(System.out::println)
                    .peek(melodizer::play);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }

    private static Stream<Integer> getIntStream(String line) {
        return line.chars()
                .mapToObj(i -> (i - _0_IN_ASCII_48_OFFSET))
                .peek(System.out::println);
    }


}
