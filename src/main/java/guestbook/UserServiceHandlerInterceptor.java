package guestbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.appengine.api.users.UserService;

@Component
public class UserServiceHandlerInterceptor extends
		HandlerInterceptorAdapter {
	private final UserService userService;

	@Autowired
	public UserServiceHandlerInterceptor(
			UserService userService) {
		this.userService = userService;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView == null)
			return;
		modelAndView.addObject("loginUrl", userService.createLoginURL(request.getRequestURI()));
		modelAndView.addObject("logoutUrl", userService.createLogoutURL(request.getRequestURI()));
		modelAndView.addObject("user", userService.getCurrentUser());
	}
}
