package com.jgchuanmei.admng.security;

import com.jgchuanmei.admng.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Optional;


@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /*
    * Here I set two error message. The other two(locked, bad credentials) are set by AuthenticationProvide.
    * I'll do the translation in the login page
     */

    @Autowired
    private UserInfoDetailsService userInfoDetailsService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        Optional<User> user = userInfoDetailsService.findByUsername(username);

        if (user.isPresent()) {
            if (user.get().isAccountNonLocked()) {
                if (user.get().getFailedAttempt() < userInfoDetailsService.MAX_FAILED_ATTEMPTS - 1) {
                    userInfoDetailsService.increaseFailedAttempts(user.get());
                } else {
                    userInfoDetailsService.lock(user.get());
//                    exception = new LockedException("Your account has been locked due to 3 failed attempts. It will be unlocked after 24 hours.");
                    exception = new LockedException("您已输入错误密码3次，账号已被冻结！账号将于24小时后解封，或通知超级用户解封！");
                }
            } else if (!user.get().isAccountNonLocked()) {
                if (userInfoDetailsService.unlockWhenTimeExpired(user.get())) {
//                    exception = new LockedException("Your account has been unlocked. Please try to login again.");
                    exception = new LockedException("您的账号已经解封，请重新尝试登录！");
                }
            }
        }

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
