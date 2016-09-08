package org.niconiconi.blog.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Volio on 2016/9/6.
 */
@Controller
public class AuthController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLogin() {
        return "auth/login";
    }
}
