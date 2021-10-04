package assignments.w1.w1d4.problem1;


/**
 * Represents the kay value pair emmited by the map method in the Mapper class
 * @author Mohammed Abdelkawy
 *
 * @param <K> key
 * @param <V> value
 */
class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
	
	private K key;
	private V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("< %s, %s >", key, value);
	}
	
	@Override()
	public int compareTo(Pair<K, V> p) {
		return getKey().compareTo(p.getKey());
	}
}
