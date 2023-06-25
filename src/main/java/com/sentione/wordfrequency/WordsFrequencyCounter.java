package com.sentione.wordfrequency;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.regex.Pattern.compile;

@Component
class WordsFrequencyCounter {

  private static final Pattern WORDS_MATCH = 
      compile("\\p{Alpha}+", Pattern.UNICODE_CHARACTER_CLASS);

  List<WordCount> countAndSort(InputStream input) throws IOException {
    if (input == null) {
      return List.of();
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, UTF_8))) {
      final Map<String, Long> result = new HashMap<>();
      
      for (String line; (line = reader.readLine()) != null;) {
        matchAndCountWords(line, result);
      }
      
      return mapToWordCountAndSort(result);
    }
  }

  private static void matchAndCountWords(String input, Map<String, Long> result) {
    Matcher matcher = WORDS_MATCH.matcher(input);
    while (matcher.find()) {
      String word = matcher.group().toLowerCase();
      result
          .compute(word, (key, val) ->
              (val == null) ? 1 : val + 1);
    }
  }
  
  private static List<WordCount> mapToWordCountAndSort(Map<String, Long> result) {
    return result
        .entrySet()
        .stream()
        .map(entry -> new WordCount(entry.getKey(), entry.getValue()))
        .sorted()
        .toList();
  }
}
