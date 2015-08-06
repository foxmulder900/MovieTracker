package hello.search;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.users.UserRepo;
import hello.users.UserType;
import hello.utils.JSONRequest;
import hello.utils.exceptions.ProgramError;

@Controller
public class SearchController {

	@Autowired
	private UserRepo users;
	
    @RequestMapping("/search")
    public String search(@RequestParam(value="filter", required=false) String filter, Model model) {
        if(filter == null){
        	return "search";
        }
        
        List<String> results;
		try{
			results = this.doSearch(filter);
		}
		catch(Exception e){
			String errorMsg = e.getMessage();
			model.addAttribute("message", errorMsg==null ? "Program Error!" : errorMsg);
			return "message";
		}
			
        model.addAttribute("filter",filter);
        model.addAttribute("results",results);
        return "results";
    }
    
    public List<String> doSearch(String filter) throws ProgramError, JSONException, IOException{
    	List<String> returnList = new ArrayList<String>();
    	
		String url = "http://www.omdbapi.com/?s="+this.sanitizeFilter(filter);
		JSONObject jsonObj = JSONRequest.getJSON(url);
		
		if(jsonObj.has("Error")){
			throw new ProgramError(jsonObj.getString("Error"));
		}
    	JSONArray searchResults = jsonObj.getJSONArray("Search");
    	for(int i=0; i<searchResults.length(); i++){
    	    returnList.add(searchResults.getJSONObject(i).getString("Title"));
    	}
	
    	return returnList;
    }
    
    public String sanitizeFilter(String filter) throws UnsupportedEncodingException{
    	filter = filter.replaceAll("\"","");
    	return URLEncoder.encode(filter,"UTF-8");
    }
    
    @RequestMapping(value="/setwatched", method=RequestMethod.GET)
    public @ResponseBody void setWatched(
    		@RequestParam(value="userId", required=true) String userId,
    		@RequestParam(value="title", required=true) String title)
    {
    	UserType user = users.findByUserId(userId);
    	user.addWatchedMovie(title);
    	users.save(user);
    }
    
    @RequestMapping(value="/settowatch", method=RequestMethod.GET)
    public @ResponseBody void setToWatched(
    		@RequestParam(value="userId", required=true) String userId,
    		@RequestParam(value="title", required=true) String title)
    {
    	UserType user = users.findByUserId(userId);
    	user.addMovieToWatch(title);
    	users.save(user);
    }
}