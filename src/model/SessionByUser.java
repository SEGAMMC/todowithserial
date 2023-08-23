package model;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.HashMap;

public class SessionByUser implements Serializable {
    private String hashbyUser;
    private HashMap<String, Boolean> session;

    public SessionByUser(User user) {
        this.hashbyUser = DigestUtils.md5Hex(user.getNickname() + ":" + user.getPassword());;
        session = new HashMap<>();
    }

    public String getHashbyUser() {
        return hashbyUser;
    }

    public void setHashbyUser(String hashbyUser) {
        this.hashbyUser = hashbyUser;
    }

    public HashMap<String, Boolean> getSession() {
        return session;
    }

    public void setSession(HashMap<String, Boolean> session) {
        this.session = session;
    }
}


