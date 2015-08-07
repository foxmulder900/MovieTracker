package hello.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.users.UserRepo;
import hello.users.UserType;
import hello.utils.exceptions.AlreadyExists;

@Controller
public class ProfileController {
	
	@Autowired
	private UserRepo users;
	
    @RequestMapping("/api/profile/{userId}")
    public String profile(@PathVariable String userId, Model model) {
    	UserType user = users.findByUserId(userId);
        model.addAttribute("fullname", user.getFullName());
        model.addAttribute("watchedMovies", user.getWatchedList());
        model.addAttribute("moviesToWatch", user.getToWatchList());
        return "profile";
    }
    
    @RequestMapping("/api/new-profile")
    public String newProfile(
    		@RequestParam(value="userId", required=true) String userId,
    		@RequestParam(value="firstName", required=true) String firstName,
    		@RequestParam(value="lastName", required=true) String lastName,
    		Model model) {
    	
    	try{
    		users.addUser(userId, firstName, lastName);
    	}
    	catch(AlreadyExists e){
    		model.addAttribute("message", "UserId: '"+userId+"' is already in use.");
    		return "message";
    	}
    	return this.profile(userId, model);
    }
    
}
