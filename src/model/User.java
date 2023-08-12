package model;

import java.io.Serializable;

public class User implements Serializable {
    private String nickname;
    private String password;
    private ListTasks listTasks;


    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
        listTasks = new ListTasks();
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

}
