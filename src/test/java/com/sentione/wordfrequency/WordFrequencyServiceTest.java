package com.sentione.wordfrequency;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class WordFrequencyServiceTest {

  @ParameterizedTest
  @MethodSource
  void shouldCalculateWordFrequency(String input, List<WordCount> expected) throws IOException {
    List<WordCount> result =
        new WordsFrequencyCounter().countAndSort(new ByteArrayInputStream(input.getBytes()));

    assertEquals(expected, result);
  }

  static Stream<Arguments> shouldCalculateWordFrequency() {
    return Stream.of(
        arguments("Test 555. My my test m test.",
            List.of(new WordCount("test", 3),
            new WordCount("my", 2),
            new WordCount("m", 1))
        ),
        arguments("a2 a a", List.of(new WordCount("a", 3))),
        arguments("taka taka taką", List.of(new WordCount("taka", 2), new WordCount("taką", 1))),
        arguments("", List.of()),
        arguments(",,,,12212.?", List.of())
    );
  }
}
