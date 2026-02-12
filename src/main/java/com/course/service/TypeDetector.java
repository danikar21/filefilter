package com.course.service;

import com.course.model.DataType;

/**
 * Определяется тип строки (целое число, вещественное число, строка)
 */

public class TypeDetector {
  public static DataType detect(String line) {

    try {
      Long.parseLong(line);
      return DataType.INTEGER;
    } catch (NumberFormatException ignored) {}

    try {
      Double.parseDouble(line);
      return DataType.FLOAT;
    } catch (NumberFormatException ignored) {}

    return DataType.STRING;
  }
}
