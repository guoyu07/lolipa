package org.niconiconi.blog.repositories;

import org.niconiconi.blog.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Volio on 2016/9/5.
 */
public interface PageRepository extends JpaRepository<Page, Integer> {

    Page findBySlug(String slug);
}
