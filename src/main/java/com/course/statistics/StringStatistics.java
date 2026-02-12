package com.course.statistics;

public class StringStatistics implements Statistics {

  private long count = 0;
  private Integer minLength = null;
  private Integer maxLength = null;

  public void add(String value) {
    count++;
    int len = value.length();
    if (minLength == null || len < minLength) minLength = len;
    if (maxLength == null || len > maxLength) maxLength = len;
  }

  public long getCount() { return count; }

  public void print(boolean shortMode, boolean fullMode) {
    System.out.println("Количество: " + count);
    if (fullMode && count > 0) {
      System.out.println("Минимальная длина: " + minLength);
      System.out.println("Максимальная длина: " + maxLength);
    }
    System.out.println();
  }
}
