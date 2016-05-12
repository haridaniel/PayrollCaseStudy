package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.common;

import java.util.HashMap;
import java.util.Map;

public class ThrowMap<K, V> {
	private Map<K, V> map = new HashMap<>();

	protected void put(K key, V value) {
		map.put(key, value);
	}

	protected V getOrThrow(K key) {
		if(!map.containsKey(key))
			throw new RuntimeException("not found: " + key);
		return map.get(key);
	}
	
}
