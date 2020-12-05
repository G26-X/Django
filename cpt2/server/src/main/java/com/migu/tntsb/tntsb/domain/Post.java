package com.migu.tntsb.tntsb.domain;

public class Post {
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getCmmd() {
        return cmmd;
    }

    public void setCmmd(int cmmd) {
        this.cmmd = cmmd;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private int uid;
    private String userName;
    private String userIcon;
    private int postId;
    private String description;
    private int like;
    private int fav;
    private int cmmd;

    @Override
    public String toString() {
        return "Post{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", postId=" + postId +
                ", description='" + description + '\'' +
                ", like=" + like +
                ", fav=" + fav +
                ", cmmd=" + cmmd +
                ", img='" + img + '\'' +
                '}';
    }

    private String img;
}
