package dataProviders;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler
{
	JSONObject jsonObj;

	public JsonHandler()
	{
		try {
			JSONParser parser = new JSONParser();
			jsonObj =(JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"/resources/Data.json"));
		} catch (IOException | ParseException e) {           
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap<String,String> getData(String object){
		HashMap<String,String> objectData = new HashMap<String,String>();
		objectData = ((HashMap<String,String>)jsonObj.get(object));
		return objectData;
	}
	
	public String getStringData(String Object){
		return jsonObj.get(Object).toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String[][] getArray(String object){
		HashMap<String,String> map =getData(object);
		String[][] arr = new String[map.size()][2];
		Set entries = map.entrySet();
		Iterator entriesIterator = entries.iterator();
		int i = 0;
		while(entriesIterator.hasNext()){
		    Map.Entry mapping = (Map.Entry) entriesIterator.next();
		    arr[i][0] = (String) mapping.getKey();
		    arr[i][1] = (String) mapping.getValue();
		    i++;
		}
		return arr;
	}
	
	
}