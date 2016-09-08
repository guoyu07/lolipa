package org.niconiconi.blog.utils;

import org.pegdown.PegDownProcessor;

/**
 * Created by Volio on 2016/9/5.
 */
public class Markdown {

    public static String markdownToHtml(String markdownSource) {
        PegDownProcessor processor = new PegDownProcessor();
        return processor.markdownToHtml(markdownSource);
    }

    //截取文章
    public static String subMarkdownToHtml(String markdownSource) {
        String html = Markdown.markdownToHtml(markdownSource);
        int moreIndex = html.indexOf("<!--more-->");
        if (moreIndex > 0) {
            html = html.substring(0, moreIndex);
        }
        return html;
    }
}
