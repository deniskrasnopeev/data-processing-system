import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
class Function1Test {

  static Stream<org.junit.jupiter.params.provider.Arguments> testCases() {
    return Stream.of(
        org.junit.jupiter.params.provider.Arguments.of(3.6, 2.9, Arrays.asList(1.0, 7.3, 3.1),
            Arrays.asList(6.5, 29.18, 14.06)),
        org.junit.jupiter.params.provider.Arguments.of(0.0, 10.0, Arrays.asList(632.0, 95.0, 22.0),
            Arrays.asList(10.0, 10.0, 10.0)),
        org.junit.jupiter.params.provider.Arguments.of(-5.0, 26.3,
            Arrays.asList(-2.5, 0.1, 23.77, 13.4), Arrays.asList(38.8, 25.8, -92.55, -40.7)),
        org.junit.jupiter.params.provider.Arguments.of(5.0, 1.0, Collections.emptyList(),
            Collections.emptyList()));
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testCalculate(double m, double c, List<Double> X, List<Double> exY) {
    Function1 func = new Function1();
    NonUpdatableHashMap<String, Double> params = new NonUpdatableHashMap<>();
    params.put("m", m);
    params.put("c", c);

    NonUpdatableHashMap<String, ArrayList<Double>> inputs = new NonUpdatableHashMap<>();
    inputs.put("X", new ArrayList<>(X));

    func.calculate(params, inputs);

    assertEquals(exY, inputs.get("Y"));
  }

}
