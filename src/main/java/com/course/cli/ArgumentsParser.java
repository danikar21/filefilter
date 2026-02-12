package com.course.cli;

import com.course.model.FilterConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Разбор аргументов командной строки
 */

public class ArgumentsParser {

  public FilterConfig parse(String[] args) {

    boolean append = false;
    boolean shortStats = false;
    boolean fullStats = false;
    String outputPath = ".";
    String prefix = "";
    List<String> inputFiles = new ArrayList<>();

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-a" -> append = true;
        case "-s" -> shortStats = true;
        case "-f" -> fullStats = true;
        case "-o" -> {
          if (i + 1 >= args.length)
            throw new IllegalArgumentException("Не указан путь после -o");
          outputPath = args[++i];
        }
        case "-p" -> {
          if (i + 1 >= args.length)
            throw new IllegalArgumentException("Не указан префикс после -p");
          prefix = args[++i];
        }
        default -> inputFiles.add(args[i]);
      }
    }

    if (shortStats && fullStats)
      throw new IllegalArgumentException("Нельзя использовать -s и -f одновременно");

    if (inputFiles.isEmpty())
      throw new IllegalArgumentException("Не указаны входные файлы");

    return new FilterConfig(append, shortStats, fullStats, outputPath, prefix, inputFiles);
  }
}
