import com.course.cli.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentsParserTest {

  @Test
  void shouldThrowIfNoFiles() {
    ArgumentsParser parser = new ArgumentsParser();

    assertThrows(IllegalArgumentException.class,
        () -> parser.parse(new String[]{"-s"}));
  }

  @Test
  void shouldParseValidArguments() {
    ArgumentsParser parser = new ArgumentsParser();

    var config = parser.parse(new String[]{
        "-a", "-s", "-o", "out", "-p", "test_", "file1.txt"
    });

    assertTrue(config.isAppendMode());
    assertTrue(config.isShortStats());
    assertEquals("out", config.getOutputPath());
    assertEquals("test_", config.getPrefix());
    assertEquals(1, config.getInputFiles().size());
  }
}

