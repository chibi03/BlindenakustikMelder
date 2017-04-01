package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Coordinate;
import model.Crossings;

public class JsonModelCoverter {

	public ArrayList<Crossings> jsonToObject(String filename){
		ArrayList<Crossings> trafficLights = new ArrayList<Crossings>();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootArray = mapper.readTree(new File(filename));
			JsonNode features = rootArray.get("features");
			
			if(features.isArray()){
				for (int i=0; i< features.size(); i++) {
					JsonNode lightNode = features.get(i);
					
					String originalId = lightNode.path("id").asText();
					
					JsonNode coordinates = lightNode.get("geometry").get("coordinates");
					double x = coordinates.path(0).asDouble();
					double y = coordinates.path(1).asDouble();
					
					JsonNode properties = lightNode.get("properties");
					int district = properties.path("BEZIRK").asInt();
					String crossing = properties.path("KREUZUNG").asText();
					
					trafficLights.add(new Crossings(new Coordinate(x, y), crossing, i, originalId, district));
				}
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trafficLights;
	}
	
}
