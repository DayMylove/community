package org.xiong.community.entity;

public class User {

    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private String bio;
    private String avatar;
    private long gmt_creat;
    private long gmt_modifid;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account_id='" + account_id + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", bio='" + bio + '\'' +
                ", gmt_creat=" + gmt_creat +
                ", gmt_modifid=" + gmt_modifid +
                '}';
    }
}
