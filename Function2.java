import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
public class Function2 extends BaseFunction {
  public void calculate(NonUpdatableHashMap<String, Double> params,
      NonUpdatableHashMap<String, ArrayList<Double>> inputs) {

    ArrayList<Double> A = inputs.get("A");
    ArrayList<Double> Y = inputs.get("Y");
    Double b;


    ArrayList<Double> B = IntStream.range(0, A.size()).mapToObj(i -> Y.get(i) + A.get(i))
        .collect(Collectors.toCollection(ArrayList<Double>::new));
    b = B.stream().mapToDouble(el -> el).average().getAsDouble();


    inputs.put("B", B);
    params.put("b", b);
  }
}
