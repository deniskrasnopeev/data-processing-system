import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
class FileOperationTest {


  static Stream<org.junit.jupiter.params.provider.Arguments> casesFileOperations() {
    return Stream.of(
        org.junit.jupiter.params.provider.Arguments.of(List.of("m, 2.0", "c, 1.0"),
            List.of("X, 1.0, 2.0, 3.0"), Map.of("m", 2.0, "c", 1.0),
            Map.of("X", List.of(1.0, 2.0, 3.0))),
        org.junit.jupiter.params.provider.Arguments.of(List.of("a, 0.5"), List.of("Z, 10.0, 20.0"),
            Map.of("a", 0.5), Map.of("Z", List.of(10.0, 20.0))),
        org.junit.jupiter.params.provider.Arguments.of(Collections.emptyList(),
            Collections.emptyList(), Map.of(), Map.of()));
  }

  @ParameterizedTest
  @MethodSource("casesFileOperations")
  void testFileOperation(List<String> paramLines, List<String> inputLines,
      Map<String, Double> expParams, Map<String, List<Double>> expInputs) throws IOException {

    Path paramFile = writeTempFile(paramLines);
    Path inputFile = writeTempFile(inputLines);

    FileOperation ops = new FileOperation(paramFile.toString(), inputFile.toString());

    assertEquals(expParams, ops.getParameters());
    assertEquals(expInputs, ops.getInputs());

    Files.deleteIfExists(paramFile);
    Files.deleteIfExists(inputFile);
  }

  private Path writeTempFile(List<String> lines) throws IOException {
    Path temp = Files.createTempFile("test", ".txt");
    Files.write(temp, lines);
    return temp;
  }


}
