package com.course.service;

import com.course.model.DataType;
import com.course.model.FilterConfig;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Управление записью данных в выходные файлы
 * - файлы создаются только при первой записи
 * - каждый файл открывается один раз
 * - поддерживается режим append
 */

public class WriterManager {
  private final FilterConfig config;
  private final Map<DataType, BufferedWriter> writers = new HashMap<>();

  public WriterManager(FilterConfig config) {
    this.config = config;
  }

  public void write(DataType type, String line) throws IOException {

    if (!writers.containsKey(type)) {
      Path dir = Paths.get(config.getOutputPath());
      Files.createDirectories(dir);

      Path file = dir.resolve(config.getPrefix() + type.getFileName());

      OpenOption option = config.isAppendMode()
          ? StandardOpenOption.APPEND
          : StandardOpenOption.TRUNCATE_EXISTING;

      BufferedWriter writer = Files.newBufferedWriter(
          file,
          StandardOpenOption.CREATE,
          option
      );

      writers.put(type, writer);
    }

    writers.get(type).write(line);
    writers.get(type).newLine();
  }

  public void closeAll() {
    writers.values().forEach(writer -> {
      try {
        writer.close();
      } catch (IOException ignored) {}
    });
  }
}
