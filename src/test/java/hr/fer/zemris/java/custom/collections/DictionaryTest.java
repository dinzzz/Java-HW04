package hr.fer.zemris.java.custom.collections;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class DictionaryTest {

	@Test
	public void isEmptyTest() {
		Dictionary dict = new Dictionary();

		boolean expected = true;
		boolean actual = dict.isEmpty();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void sizeTest() {
		Dictionary dict = new Dictionary();

		dict.put(24, 13);
		dict.put(11, 17);

		int expected = 2;
		int actual = dict.size();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void putSingleTest() {
		Dictionary dict = new Dictionary();

		dict.put(24, 13);
		
		int expected = 1;
		int actual = dict.size();
		
		Assert.assertEquals(expected, actual);
		
		int expected1 = 13;
		int actual1 = (int)dict.get(24);
		
		Assert.assertEquals(expected1, actual1);
		
		
	}

	@Test
	public void putMultipleTest() {
		Dictionary dict = new Dictionary();

		dict.put(24, 13);
		dict.put(11, 17);
		dict.put(25, 3);
		dict.put(21, null);

		int expected = 4;
		int actual = dict.size();

		Assert.assertEquals(expected, actual);
		
		int expected1 = 3;
		int actual1 = (int)dict.get(25);
		
		Object expected2 = null;
		Object actual2 = dict.get(21);
		
		Assert.assertEquals(expected2, actual2);
		Assert.assertEquals(expected1, actual1);
				
				
	}

	@Test
	public void putOverlayTest() {
		Dictionary dict = new Dictionary();

		dict.put(24, 13);
		dict.put(11, 17);
		dict.put(24, 19);
		dict.put(11, 1);
		
		int expected1 = 19;
		int expected2 = 1;
		int actual1 = (int)dict.get(24);
		int actual2 = (int)dict.get(11);
		
		Assert.assertEquals(expected2, actual2);
		Assert.assertEquals(expected1, actual1);
	}

	@Test
	public void containsKeyTest() {
		Dictionary dict = new Dictionary();

		dict.put(24, 13);
		dict.put(11, 17);
		
		boolean expected = true;
		boolean actual = dict.containsKey(24);
		
		boolean expected1 = false;
		boolean actual1 = dict.containsKey(19);
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected1, actual1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void putNullKeyTest() {
		Dictionary dict = new Dictionary();
		
		dict.put(null, 35);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getNullKeyTest() {
		Dictionary dict = new Dictionary();
		
		dict.put(24, 13);
		@SuppressWarnings("unused")
		Object get = dict.get(null);
	}
}
