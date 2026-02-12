package com.course.statistics;

public class NumberStatistics implements Statistics {

  private long count = 0;
  private double sum = 0;
  private Double min = null;
  private Double max = null;

  public void add(double value) {
    count++;
    sum += value;
    if (min == null || value < min) min = value;
    if (max == null || value > max) max = value;
  }

  public long getCount() {
    return count;
  }

  public void print(boolean shortMode, boolean fullMode) {
    System.out.println("Количество: " + count);
    if (fullMode && count > 0) {
      System.out.println("Минимум: " + min);
      System.out.println("Максимум: " + max);
      System.out.println("Сумма: " + sum);
      System.out.println("Среднее: " + sum / count);
    }
    System.out.println();
  }
}

