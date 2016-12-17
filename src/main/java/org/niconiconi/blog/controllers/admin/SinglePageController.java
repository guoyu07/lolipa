package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by Volio on 2016/9/23.
 */
@Controller
@RequestMapping(value = "/admin/pages")
public class SinglePageController {

    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/links", method = RequestMethod.GET)
    public String editLinksPage(Model model) {
        Page page = pageService.findBySlug("links");
        model.addAttribute("page", page);
        model.addAttribute("title", "编辑友情链接");
        return "admin/pages/edit";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String editAboutPage(Model model) {
        Page page = pageService.findBySlug("about");
        model.addAttribute("page", page);
        model.addAttribute("title", "编辑关于页面");
        return "admin/pages/edit";
    }

    @RequestMapping(value = "/links", method = {RequestMethod.POST,RequestMethod.PUT})
    @ResponseBody
    public void updateLinksPage(@RequestBody @Valid Page page) {
        page.setSlug("links");
        pageService.update(page);
    }

    @RequestMapping(value = "/about", method = {RequestMethod.POST,RequestMethod.PUT})
    @ResponseBody
    public void updateAboutPage(@RequestBody @Valid Page page) {
        page.setSlug("about");
        pageService.update(page);
    }
}
