package hr.fer.zemris.java.custom.collections;

/**
 * Class that represents a dictionary - a collection that stores the value of
 * elements according to their given keys. This class uses
 * ArrayIndexedCollection as an adaptee to implement the functionality. Null
 * keys are forbidden, but the null values are permited.
 * 
 * @author Dinz
 *
 */
public class Dictionary {
	/**
	 * Nested class Entry that represents one single entry to a dictionary
	 * consisting of the key and the value.
	 * 
	 * @author Dinz
	 *
	 */
	private static class Entry {
		/**
		 * Key of the entry.
		 */
		Object key;
		/**
		 * Value of the entry.
		 */
		Object value;

		/**
		 * Constructs a new instance of the Entry with its key and the value.
		 * 
		 * @param key
		 *            Key of the entry.
		 * @param value
		 *            Value of the entry.
		 */
		public Entry(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

		public Object getKey() {
			return key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

	}

	/**
	 * ArrayIndexedCollection instance that helps implementing the Dictionary.
	 */
	private ArrayIndexedCollection adaptee = new ArrayIndexedCollection();

	/**
	 * Constructs a new dictionary - actually instantiates the
	 * ArrayIndexedCollection.
	 */
	public Dictionary() {

	}

	/**
	 * Checks if the dictionary is empty.
	 * 
	 * @return True if the dictionary is empty, False otherwise.
	 */
	public boolean isEmpty() {
		return adaptee.isEmpty();
	}

	/**
	 * Returns the size of the dictionary.
	 * 
	 * @return Size of the dictionary.
	 */
	public int size() {
		return adaptee.size();
	}

	/**
	 * Puts a new entry to the dictionary. The method checks if the key was already
	 * used - if it was, the new value overwrites the old one. If not, the method
	 * creates a new Entry and adds it to the ArrayIndexedCollection/Dictionary.
	 * 
	 * @param key
	 *            Key of the entry.
	 * @param value
	 *            Value of the entry.
	 * @throws IllegalArgumentException
	 *             if the given key is null.
	 */
	public void put(Object key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null!");
		}

		if (this.containsKey(key)) {
			for (int i = 0, j = this.size(); i < j; i++) {
				if (((Entry) adaptee.get(i)).getKey().equals(key)) {
					((Entry) adaptee.get(i)).setValue(value);
					break;
				}
			}
		} else {
			adaptee.add(new Entry(key, value));
		}
	}

	/**
	 * Returns the object at the given key.
	 * 
	 * @param key
	 *            The key of the wanted entry.
	 * @return Value from the dictionary at the given key. Null if there is no given
	 *         key.
	 * @throws IllegalArgumentException
	 *             if the given key is null.
	 */
	public Object get(Object key) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null!");
		}
		for (int i = 0, j = this.size(); i < j; i++) {
			if (((Entry) adaptee.get(i)).getKey().equals(key)) {
				return ((Entry) adaptee.get(i)).getValue();
			}
		}

		return null;
	}

	/**
	 * Method that checks if the dictionary contains a certain key.
	 * 
	 * @param key
	 *            Key to be checked.
	 * @return True if the dictionary contains the key, False otherwise.
	 * @throws IllegalArgumentException
	 *             if the given key is null.
	 */
	public boolean containsKey(Object key) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null!");
		}
		for (int i = 0, j = this.size(); i < j; i++) {
			if (((Entry) adaptee.get(i)).getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

}
