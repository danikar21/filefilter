package com.course;

import com.course.cli.ArgumentsParser;
import com.course.model.FilterConfig;
import com.course.service.FileProcessor;

/**
 * Точка входа в приложение
 */
public class Main {
  public static void main(String[] args) {
    try {
      ArgumentsParser parser = new ArgumentsParser();
      FilterConfig config = parser.parse(args);

      FileProcessor processor = new FileProcessor(config);
      processor.process();
    } catch (IllegalArgumentException e) {
      System.err.println("Ошибка аргументов: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Критическая ошибка: " + e.getMessage());
    }
  }
}