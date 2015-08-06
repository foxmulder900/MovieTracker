package hello.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONRequest {
	public static JSONObject getJSON(String url) throws JSONException, IOException{
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
 
		int responseCode = con.getResponseCode();
		if(responseCode == 200){
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return new JSONObject(response.toString());
		}
		return new JSONObject("{\"Error\":\"HTTP request responded with code:"+responseCode+"\"}");
	}
}
