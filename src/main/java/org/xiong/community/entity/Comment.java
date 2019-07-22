package org.xiong.community.entity;

public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private String likeCount;
    private String content;
    private int commentor;
    private long gmtCreat;
    private long gmtModifid;

    public int getCommentor() {
        return commentor;
    }

    public void setCommentor(int commentor) {
        this.commentor = commentor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getGmtCreat() {
        return gmtCreat;
    }

    public void setGmtCreat(long gmtCreat) {
        this.gmtCreat = gmtCreat;
    }

    public long getGmtModifid() {
        return gmtModifid;
    }

    public void setGmtModifid(long gmtModifid) {
        this.gmtModifid = gmtModifid;
    }
}
