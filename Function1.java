import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
public class Function1 extends BaseFunction {
  public void calculate(NonUpdatableHashMap<String, Double> params,
      NonUpdatableHashMap<String, ArrayList<Double>> inputs) {
    double m = params.get("m");
    double c = params.get("c");
    ArrayList<Double> X = inputs.get("X");

    ArrayList<Double> Y =
        X.stream().map(el -> el * m + c).collect(Collectors.toCollection(ArrayList<Double>::new));

    inputs.put("Y", Y);
  }
}
