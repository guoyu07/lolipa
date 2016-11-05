package org.niconiconi.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by Volio on 2016/11/5.
 */
@Service
@PropertySource("classpath:env.properties")
public class EnvService {

    private final Environment env;

    @Autowired
    public EnvService(Environment env) {
        this.env = env;
    }

    public String getMailFrom() {
        return env.getProperty("mail.from");
    }

    public String getMailTo() {
        return env.getProperty("mail.to");
    }

    public boolean getNotification() {
        return env.getProperty("mail.notification", boolean.class);
    }
}
