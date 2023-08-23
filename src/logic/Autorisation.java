package logic;

import model.SessionByUser;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

public class Autorisation {
    private String dirNameUserActiv;
    private File dir;
    private String hashUser;
    private SessionByUser session;


    public String getNewSession(String nickname, String pass) {
        if (!checkLogPass(nickname, pass)) {
            return null;
        }

        ReaderSerial readerSession = new ReaderSerial();
        session = readerSession.readMySession(nickname);

        Date dataNow = new Date();
        String cookieSession = DigestUtils.md5Hex(nickname + ":" + pass + ":" + dataNow.getTime());
        session.getSession().put(cookieSession, true);

        WriterSerial writerSerial = new WriterSerial();
        writerSerial.writeUserSession(new User(nickname, pass), session);
        return nickname+"&&"+cookieSession;
    }


    public boolean checkLogPass(String nickname, String pass) {
        dirNameUserActiv = "data\\" + nickname;
        dir = new File(dirNameUserActiv);

        String hashIn = DigestUtils.md5Hex(nickname + ":" + pass);

        if (dir.exists()) {
            ReaderSerial readerSession = new ReaderSerial();
            session = readerSession.readMySession(nickname);
            if (session.getHashbyUser().equals(hashIn)) {
                System.out.println("Autorisation: NickName and Password Good!");
                return true;
            } else {
                System.out.println("Autorisation: NickName or Password NO OK");
                return false;
            }
        } else {
            System.out.println("Autorisation: This Nickname not found");
            return false;
        }
    }

    public boolean checkCookie(String nickname,String cookie) {
        ReaderSerial readerSession = new ReaderSerial();
        HashMap<String, Boolean> listSession = null;
            session = readerSession.readMySession(nickname);
             listSession = session.getSession();

        return listSession.containsKey(cookie);
    }
}
