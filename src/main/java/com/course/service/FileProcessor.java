package com.course.service;

import com.course.model.*;
import com.course.statistics.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

public class FileProcessor {

  private final FilterConfig config;
  private final Map<DataType, Statistics> statistics = new EnumMap<>(DataType.class);

  public FileProcessor(FilterConfig config) {
    this.config = config;
    statistics.put(DataType.INTEGER, new NumberStatistics());
    statistics.put(DataType.FLOAT, new NumberStatistics());
    statistics.put(DataType.STRING, new StringStatistics());
  }

  public void process() {

    WriterManager writerManager = new WriterManager(config);

    for (String fileName : config.getInputFiles()) {

      Path path = Path.of(fileName);

      if (!Files.exists(path)) {
        System.err.println("Файл не найден: " + fileName);
        continue;
      }

      try (BufferedReader reader = Files.newBufferedReader(path)) {
        String line;

        while ((line = reader.readLine()) != null) {
          if (line.isBlank()) continue;

          DataType type = TypeDetector.detect(line);

          writerManager.write(type, line);

          if (type == DataType.STRING) {
            ((StringStatistics) statistics.get(type)).add(line);
          } else {
            double value = Double.parseDouble(line);
            ((NumberStatistics) statistics.get(type)).add(value);
          }
        }

      } catch (IOException e) {
        System.err.println("Ошибка чтения файла " + fileName + ": " + e.getMessage());
      }
    }

    writerManager.closeAll();
    printStatistics();
  }

  private void printStatistics() {

    if (!config.isShortStats() && !config.isFullStats())
      return;

    for (Map.Entry<DataType, Statistics> entry : statistics.entrySet()) {
      if (entry.getValue().getCount() > 0) {
        System.out.println(entry.getKey());
        entry.getValue().print(config.isShortStats(), config.isFullStats());
      }
    }
  }
}
