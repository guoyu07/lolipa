package org.niconiconi.blog.services;

import org.niconiconi.blog.errors.NotFoundException;
import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Volio on 2016/9/23.
 */
@Service
public class PageService {

    private final PageRepository pageRepository;

    @Autowired
    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public Page findBySlug(String slug) {
        Page page = pageRepository.findBySlug(slug);
        if (page == null) {
            throw new NotFoundException();
        }
        return page;
    }

    public List<Page> findAll() {
        return pageRepository.findAll();
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
