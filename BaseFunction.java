import java.util.ArrayList;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
public abstract class BaseFunction {
  abstract void calculate(NonUpdatableHashMap<String, Double> params,
      NonUpdatableHashMap<String, ArrayList<Double>> inputs);
}
