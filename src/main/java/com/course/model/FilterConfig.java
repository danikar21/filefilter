package com.course.model;

import java.util.List;

public class FilterConfig {

  private final boolean appendMode;
  private final boolean shortStats;
  private final boolean fullStats;
  private final String outputPath;
  private final String prefix;
  private final List<String> inputFiles;

  public FilterConfig(boolean appendMode,
                      boolean shortStats,
                      boolean fullStats,
                      String outputPath,
                      String prefix,
                      List<String> inputFiles) {
    this.appendMode = appendMode;
    this.shortStats = shortStats;
    this.fullStats = fullStats;
    this.outputPath = outputPath;
    this.prefix = prefix;
    this.inputFiles = inputFiles;
  }

  public boolean isAppendMode() {
    return appendMode;
  }
  public boolean isShortStats() {
    return shortStats;
  }
  public boolean isFullStats() {
    return fullStats;
  }
  public String getOutputPath() {
    return outputPath;
  }
  public String getPrefix() {
    return prefix;
  }
  public List<String> getInputFiles() {
    return inputFiles;
  }
}

