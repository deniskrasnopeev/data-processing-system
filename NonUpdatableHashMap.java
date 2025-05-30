import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 
 * @author Denis Krasnopeev
 *
 */
public class NonUpdatableHashMap<K, V> extends HashMap<K, V> {

  private static final long serialVersionUID = 3790664592946984454L;

  public NonUpdatableHashMap() {
    super();
  }

  public NonUpdatableHashMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
  }

  public NonUpdatableHashMap(int initialCapacity) {
    super(initialCapacity);
  }

  public NonUpdatableHashMap(Map<? extends K, ? extends V> m) {
    super(m);
  }

  @Override
  public V get(Object key) {
    if (containsKey(key)) {
      return super.get(key);
    } else {
      throw new NoSuchElementException();
    }
  }

  @Override
  public V put(K key, V value) {
    if (containsKey(key)) {
      throw new IllegalArgumentException();
    } else {
      return super.put(key, value);
    }
  }
}
