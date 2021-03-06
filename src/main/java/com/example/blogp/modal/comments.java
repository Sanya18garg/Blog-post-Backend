package com.example.blogp.modal;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comment")
public class comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commentid")
    private int id;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "blogid", referencedColumnName = "blogid", nullable = false)
    @NotNull
    private blog blog;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @NotNull
    private user user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public com.example.blogp.modal.blog getBlog() {
        return blog;
    }

    public void setBlog(com.example.blogp.modal.blog blog) {
        this.blog = blog;
    }

    public com.example.blogp.modal.user getUser() {
        return user;
    }

    public void setUser(com.example.blogp.modal.user user) {
        this.user = user;
    }
}
