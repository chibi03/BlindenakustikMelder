package model;

import java.util.Map;

import org.mongodb.morphia.annotations.Embedded;

import util.Maps;

@Embedded
public class Defects {

	@Embedded(concreteClass = java.util.HashMap.class)
	private Map<Integer, Boolean> defectMap;
	
	public Defects() {
		this.defectMap = Maps.<Integer, Boolean>builder().
	            put(0, false).
	            put(1, false).
	            put(2, false).
	            put(3, false).
	            put(4, false).
	            put(5, false).
	            put(6, false).
	            put(7, false).
	            build();
	}
	

	public Map<Integer, Boolean> getDefectMap() {
		return defectMap;
	}
	
	public void setDefectMap(Map<Integer, Boolean> defectMap) {
		this.defectMap = defectMap;
	}

}
