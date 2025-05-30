import java.util.ArrayList;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
public class InputsAdder extends Adder {
  NonUpdatableHashMap<String, ArrayList<Double>> map;

  public InputsAdder(NonUpdatableHashMap<String, ArrayList<Double>> map) {
    this.map = map;
  }

  @Override
  void add(String[] data) {
    ArrayList<Double> list = new ArrayList<>();
    for (int i = 1; i < data.length; i++) {
      list.add(Double.parseDouble(data[i]));
    }
    map.put(data[0], list);

  }
}
