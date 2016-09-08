package org.niconiconi.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by Volio on 2016/9/5.
 */
@Service
@PropertySource("classpath:env.properties")
public class EnvService {

    @Autowired
    Environment env;

    public int getPageSize() {
        return env.getRequiredProperty("site.pagesize",Integer.class);
    }
}
