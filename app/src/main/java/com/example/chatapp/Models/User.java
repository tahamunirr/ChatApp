package com.example.chatapp.Models;

public class User {

    String profilepic, userName, mail,password, userId, lastmessage;



    public User(String profilepic, String userName, String mail, String password, String userId, String lastmessage) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.password = password;
        this.userId = userId;
        this.lastmessage = lastmessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User(){}

    //Sign Up
    public User(String userName, String mail,String password) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
    }




    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public String getLastmessage() {
        return lastmessage;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
