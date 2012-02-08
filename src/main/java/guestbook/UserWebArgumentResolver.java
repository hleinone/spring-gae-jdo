package guestbook;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class UserWebArgumentResolver implements WebArgumentResolver {
	private final UserService userService;

	public UserWebArgumentResolver(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType() == User.class)
			return userService.getCurrentUser();
		return UNRESOLVED;
	}
}