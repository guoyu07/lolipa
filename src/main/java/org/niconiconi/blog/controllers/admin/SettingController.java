package org.niconiconi.blog.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Volio on 2016/9/22.
 */
@Controller
@RequestMapping("/admin/settings")
public class SettingController {

    @RequestMapping(method = RequestMethod.GET)
    public String getSettings() {
        return "admin/setting";
    }
}
