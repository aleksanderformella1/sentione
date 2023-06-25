package com.sentione.wordfrequency;

record WordCount(String word, long count) implements Comparable<WordCount> {

  @Override
  public int compareTo(WordCount comparable) {
    return Long.compare(comparable.count(), this.count());
  }
}
