import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
public class Function4 extends BaseFunction {
  public void calculate(NonUpdatableHashMap<String, Double> params,
      NonUpdatableHashMap<String, ArrayList<Double>> inputs) {
    ArrayList<Double> X = inputs.get("X");
    double b = params.get("b");
    ArrayList<Double> C =
        X.stream().map(el -> el + b).collect(Collectors.toCollection(ArrayList<Double>::new));

    inputs.put("C", C);
  }
}
