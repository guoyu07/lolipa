package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.models.User;
import org.niconiconi.blog.repositories.PageRepository;
import org.niconiconi.blog.services.PageService;
import org.niconiconi.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by Volio on 2016/9/22.
 */
@Controller
@RequestMapping("/admin/settings")
public class SettingController {

    @Autowired
    private PageService pageService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String getSettings(Model model, Principal principal) {
        List<Page> pages = pageService.findAll();
        String username = principal.getName();
        model.addAttribute("pages", pages);
        model.addAttribute("username", username);
        return "admin/setting";
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public void updateProfile(@RequestBody @Valid User user, Principal principal) {
        String username = principal.getName();
        userService.update(username, user);
    }
}
