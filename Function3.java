import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
public class Function3 extends BaseFunction {
  public void calculate(NonUpdatableHashMap<String, Double> params,
      NonUpdatableHashMap<String, ArrayList<Double>> inputs) {
    ArrayList<Double> X = inputs.get("X");
    ArrayList<Double> A =
        X.stream().map(el -> 1 / el).collect(Collectors.toCollection(ArrayList<Double>::new));

    inputs.put("A", A);
  }
}
