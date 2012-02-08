package guestbook;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.google.appengine.api.users.User;

@RequestMapping("/")
@Controller
public class GuestbookController {
	private final GuestbookDao guestbookDao;

	@Autowired
	public GuestbookController(final GuestbookDao guestbookDao) {
		this.guestbookDao = guestbookDao;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		List<Greeting> greetings = guestbookDao.getAll();
		model.addAttribute("greetings", greetings);
		return "guestbook";
	}

	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public View sign(@RequestParam("content") final String content,
			final User user) {
		Date date = new Date();
		Greeting greeting = new Greeting(user, content, date);
		guestbookDao.store(greeting);

		return new RedirectView("/", true, true, false);
	}
}