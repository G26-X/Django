package com.migu.tntsb.tntsb.domain;

public class Comment {
    private Integer pid;
    private String userName;
    private String content;

    public Integer getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "pid=" + pid +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", likes=" + likes +
                '}';
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    private String userIcon;
    private Integer likes;
}
