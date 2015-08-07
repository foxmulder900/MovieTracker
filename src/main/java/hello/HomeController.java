package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String base(Model model){
		return home(model);
	}
	@RequestMapping("/home")
	public String home(Model model){
		return "home";
	}
	
}