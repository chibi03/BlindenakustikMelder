package core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;

import org.glassfish.jersey.server.ResourceConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.MapperOptions;

import com.mongodb.MongoClient;

import model.Crossings;

public class MongoDBManager extends ResourceConfig{
	
	public MongoDBManager() {
		packages("core", "model", "service", "util");
	}

	public static void main(String args[]) {
		ArrayList<Crossings> lights = null;
		try {
			Properties prop = JSONDownloader.getProperies();
			JSONDownloader.getJSON(prop.getProperty("url"), prop.getProperty("savePath"));
			JsonModelCoverter converter = new JsonModelCoverter();
			lights = converter.jsonToObject(prop.getProperty("savePath"));
			
			if (lights != null) {
				Morphia morphia = new Morphia();
				morphia.mapPackage("core");
				morphia.mapPackage("model");
				
				//allow empty Entries
				MapperOptions options = new MapperOptions();
			    options.setStoreEmpties(true);
			    morphia.getMapper().setOptions(options);
				

				MongoClient mongoClient = new MongoClient("localhost", 27017);

				// create the Datastore
				Datastore datastore = morphia.createDatastore(mongoClient, prop.getProperty("dbName"));
				datastore.ensureIndexes();

				// Now connect to your databases
				mongoClient.getDatabase(prop.getProperty("dbName"));

				//Save to database
				datastore.save(lights);
			} else {
				System.out.println("Failed to convert JSON ("+ prop.getProperty("savePath") +") to POJO.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
	}

}
