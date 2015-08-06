package hello.details;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.utils.JSONRequest;

@Controller
public class DetailsController {

    @RequestMapping("/details")
    public String getDetails(@RequestParam(value="title", required=true) String title, Model model) throws JSONException, IOException{
		String url = "http://www.omdbapi.com/?t="+URLEncoder.encode(title,"UTF-8");
		JSONObject jsonObj = JSONRequest.getJSON(url);
		
		Iterator<?> fields = jsonObj.keys();
		while(fields.hasNext()){
			String fieldName = (String) fields.next();
			model.addAttribute(fieldName, jsonObj.get(fieldName));
		}
		
    	model.addAttribute("title", title);
    	return "details";
    }
}