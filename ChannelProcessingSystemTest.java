import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
class ChannelProcessingSystemTest {


  static Stream<org.junit.jupiter.params.provider.Arguments> casesRun() {
    return Stream.of(
        org.junit.jupiter.params.provider.Arguments.of(List.of("m, 2.0", "c, 1.0"),
            List.of("X, 1.0, 2.0, 3.0"), List.of("Y"), List.of("Y, 3.0, 5.0, 7.0")),
        org.junit.jupiter.params.provider.Arguments.of(List.of("m, 1.0", "c, 3.0"),
            List.of("X, 4.0, 8.0, 10.0, 20.0"), List.of("b"), List.of("b, 13.63125")),
        org.junit.jupiter.params.provider.Arguments.of(List.of("m, 5.5", "c, 0.72"),
            List.of("X, 0.983, 0.29, 0.8347, 0.0234"), List.of("b", "A", "X"),
            List.of("b, 15.749924454328161",
                "A, 1.017293997965412, 3.4482758620689657, 1.1980352222355337, 42.73504273504273",
                "X, 0.983, 0.29, 0.8347, 0.0234")));

  }

  @ParameterizedTest
  @MethodSource("casesRun")
  void testRun(List<String> paramLines, List<String> inputLines, List<String> reqRes,
      List<String> expResLines) throws IOException {

    String resFileName = "testOut.txt";
    Path paramFile = writeTempFile(paramLines);
    Path inputFile = writeTempFile(inputLines);
    Queue<String> reqResQueue = new LinkedList<>(reqRes);


    ChannelProcessingSystem system = new ChannelProcessingSystem(paramFile.toString(),
        inputFile.toString(), resFileName, reqResQueue);
    system.run();

    Path expResultFile = writeTempFile(expResLines);

    byte[] expBytes = Files.readAllBytes(expResultFile);
    byte[] actBytes = Files.readAllBytes(Paths.get(resFileName));
    assertArrayEquals(expBytes, actBytes);

    Files.deleteIfExists(paramFile);
    Files.deleteIfExists(inputFile);
    Files.deleteIfExists(expResultFile);
    Files.deleteIfExists(Paths.get(resFileName));
  }

  private Path writeTempFile(List<String> lines) throws IOException {
    Path temp = Files.createTempFile("test", ".txt");
    Files.write(temp, lines);
    return temp;
  }

}
