package model;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;

public class User implements Serializable {
    private String nickname;
    private String password;
    private ListTasks listTasks;
    private String hashUser;


    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
        this.listTasks = new ListTasks();
        String nickpass = nickname+":"+password;
        setHashUser(nickpass);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ListTasks getListTasks() {
        return listTasks;
    }

    public void setListTasks(ListTasks listTasks) {
        this.listTasks = listTasks;
    }

    public String getHashUser() {
        return hashUser;
    }

    private void setHashUser(String nickpass) {
        this.hashUser = DigestUtils.md5Hex(nickpass);
    }
}
