package employee_system.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
	
	@RequestMapping(value = "/login_page.mvc")
	public String getLoginPage() {
		return "loginPage";
	}
	
	@RequestMapping(value = "/sign_up.mvc")
	public String getSignUpPage() {
		return "signUpPage";
	}
}
