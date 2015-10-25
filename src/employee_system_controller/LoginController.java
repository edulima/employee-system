package employee_system_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login_page.mvc")
	public String getLoginPage() {
		return "loginPage";
	}
}
