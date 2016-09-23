package org.niconiconi.blog.services;

import org.niconiconi.blog.errors.NotFoundException;
import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Volio on 2016/9/23.
 */
@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    public Page findBySlug(String slug) {
        Page page = pageRepository.findBySlug(slug);
        if (page == null) {
            throw new NotFoundException();
        }
        return page;
    }

    public Page update(Page page) {
        Page sPage = pageRepository.findBySlug(page.getSlug());
        if (sPage == null) {
            throw new NotFoundException();
        }
        sPage.setContent(page.getContent());
        return pageRepository.save(sPage);
    }
}
