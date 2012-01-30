package guestbook;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

@RequestMapping("/")
@Controller
public class GuestbookController {
	private final UserService userService;
	private final GuestbookDao guestbookDao;

	@Autowired
	public GuestbookController(final UserService userService,
			final GuestbookDao guestbookDao) {
		this.userService = userService;
		this.guestbookDao = guestbookDao;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String read(Model model) {
		List<Greeting> greetings = guestbookDao.getAll();
		model.addAttribute("greetings", greetings);
		return "guestbook";
	}

	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String sign(@RequestParam("content") final String content) {
		User user = userService.getCurrentUser();

		Date date = new Date();
		Greeting greeting = new Greeting(user, content, date);
		guestbookDao.store(greeting);

		return "redirect:/";
	}

	@ModelAttribute("user")
	public User getUser() {
		return userService.getCurrentUser();
	}

	@ModelAttribute("loginUrl")
	public String getLoginUrl(final HttpServletRequest request) {
		return userService.createLoginURL(request.getRequestURI());
	}

	@ModelAttribute("logoutUrl")
	public String getLogoutUrl(final HttpServletRequest request) {
		return userService.createLogoutURL(request.getRequestURI());
	}
}