/**
 * 
 * @author Denis Krasnopeev
 *
 */
public class ParamsAdder extends Adder {

  NonUpdatableHashMap<String, Double> map;

  public ParamsAdder(NonUpdatableHashMap<String, Double> map) {
    this.map = map;
  }

  @Override
  void add(String[] data) {
    map.put(data[0], Double.parseDouble(data[1]));

  }



}
