import com.course.service.*;

import com.course.model.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeDetectorTest {

  @Test
  void shouldDetectInteger() {
    assertEquals(DataType.INTEGER, TypeDetector.detect("123"));
    assertEquals(DataType.INTEGER, TypeDetector.detect("-100"));
  }

  @Test
  void shouldDetectFloat() {
    assertEquals(DataType.FLOAT, TypeDetector.detect("3.14"));
    assertEquals(DataType.FLOAT, TypeDetector.detect("1.5E-10"));
  }

  @Test
  void shouldDetectString() {
    assertEquals(DataType.STRING, TypeDetector.detect("Hello"));
    assertEquals(DataType.STRING, TypeDetector.detect("123abc"));
  }
}
