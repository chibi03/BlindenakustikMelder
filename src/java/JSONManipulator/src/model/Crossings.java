package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(noClassnameStored = true)
public class Crossings {

	@Id
	private int id;

	@Property("_version")
	private int version;
	private int district;

	private Coordinate coordinates;

	private String crossingLabel;
	private String originalId;

	private Defects defects = new Defects();

	@Property("trafficLightNumbers")
	private List<Integer> trafficLightNumbers = new ArrayList<>();
	
	private LocalDateTime lastChanged;

	public Crossings(Coordinate coord, String crossing, int id, String originalId, int district) {
		this.coordinates = coord;
		this.crossingLabel = crossing;
		this.id = id;
		this.originalId = originalId;
		this.district = district;
		this.version = 1;
		this.lastChanged = LocalDateTime.now();
	}

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate coord) {
		this.coordinates = coord;
	}

	public String getCrossingLabel() {
		return crossingLabel;
	}

	public void setCrossingLabel(String crossingLabel) {
		this.crossingLabel = crossingLabel;
	}

	public int getDistrict() {
		return district;
	}

	public void setDistrict(int district) {
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public void setDefects(Defects defects) {
		this.defects = defects;
	}

	public void setTrafficLightNumbers(List<Integer> trafficLightNumbers) {
		this.trafficLightNumbers = trafficLightNumbers;
	}

	public void addTrafficLightNumber(Integer number) {
		if (!trafficLightNumbers.stream().anyMatch(val -> val.equals(number))) {
			trafficLightNumbers.add(number);
		}
	}

	public void setVersion(int version) {
		this.version = version;

	}

	public int getVersion() {
		return version;
	}

	public Defects getDefects() {
		return defects;
	}

	public List<Integer> getTrafficLightNumbers() {
		return trafficLightNumbers;
	}
	
	public LocalDateTime getLastChanged() {
		return lastChanged;
	}
	
	public void setLastChanged(LocalDateTime lastChanged) {
		this.lastChanged = lastChanged;
	}
}
