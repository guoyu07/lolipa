package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * Created by Volio on 2016/9/22.
 */
@Controller
@RequestMapping("/admin/settings")
public class SettingController {

    @Autowired
    private PageRepository pageRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getSettings(Model model, Principal principal) {
        List<Page> pages = pageRepository.findAll();
        String username = principal.getName();
        model.addAttribute("pages", pages);
        model.addAttribute("username", username);
        return "admin/setting";
    }
}
