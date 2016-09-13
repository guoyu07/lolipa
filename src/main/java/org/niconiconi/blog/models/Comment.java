package org.niconiconi.blog.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Volio on 2016/9/6.
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coid;

    @Column(name = "cid")
    private Long cid;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "author", nullable = false, length = 200)
    @NotNull
    @Size(max = 200)
    private String author;

    @Column(name = "mail", nullable = false, length = 200)
    @NotNull
    @Email
    private String mail;

    @Column(name = "url", nullable = false, length = 200)
    @URL
    private String url;

    @Column(name = "ip", nullable = false, length = 64)
    private String ip;

    @Column(name = "agent", nullable = false, length = 200)
    private String agent;

    @Column(name = "text", nullable = false)
    @NotNull
    private String text;

    @Column(name = "status", nullable = false, length = 16)
    private String status;

    public Long getCoid() {
        return coid;
    }

    public void setCoid(Long coid) {
        this.coid = coid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
