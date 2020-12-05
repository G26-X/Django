package com.migu.tntsb.tntsb.domain;
/*Dao of user*/
public class User {
    private Integer uid;
    private String name;
    private String password;
    public Integer getUid(){
        return uid;
    }
    public void serUid(Integer uid){
        this.uid = uid;
        return ;
    }
    public String getName(){
        return name;
    }
    public void serUid(String name){
        this.name = name;
        return ;
    }
    public String getPwd(){
        return password;
    }
    public void serPwd(String password){
        this.password = password;
        return ;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + uid +
                ", username='" + name + '\'' +
                ", password=" + password +
                '}';
    }
}
