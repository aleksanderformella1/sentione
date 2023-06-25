package com.sentione.wordfrequency;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/count", produces = APPLICATION_JSON_VALUE)
class WordFrequencyController {
  private final WordsFrequencyCounter wordsFrequencyCounter;

  WordFrequencyController(WordsFrequencyCounter wordsFrequencyCounter) {
    this.wordsFrequencyCounter = wordsFrequencyCounter;
  }

  @PostMapping
  List<WordCount> countWords(@RequestParam("file") MultipartFile file) throws IOException {
    return wordsFrequencyCounter.countAndSort(file.getInputStream());
  }
}
