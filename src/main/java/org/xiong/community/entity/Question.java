package org.xiong.community.entity;

public class Question {
    private int id;
    private String title;
    private String description;
    private long gmt_creat;
    private long gmt_modifid;
    private int creator;
    private int comment_count;
    private int view_count;
    private int like_count;
    private String tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getGmt_creat() {
        return gmt_creat;
    }

    public void setGmt_creat(long gmt_creat) {
        this.gmt_creat = gmt_creat;
    }

    public long getGmt_modifid() {
        return gmt_modifid;
    }

    public void setGmt_modifid(long gmt_modifid) {
        this.gmt_modifid = gmt_modifid;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", gmt_creat=" + gmt_creat +
                ", gmt_modifid=" + gmt_modifid +
                ", creator=" + creator +
                ", comment_count=" + comment_count +
                ", view_count=" + view_count +
                ", like_count=" + like_count +
                ", tags='" + tags + '\'' +
                '}';
    }
}
