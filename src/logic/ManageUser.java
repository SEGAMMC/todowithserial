package logic;

import model.SessionByUser;
import model.User;

import java.io.File;
import java.io.IOException;

public class ManageUser {
    private User user;

    private String dirNameUserActiv;
    private String dirNameUserArchiv;
    private String fileNameUserActiv;
    private String fileNameUserArchiv;

    private File dir;
    private File dirinArchiv;

    private File file;
    private File fileinArchiv;


    public ManageUser(User user) {
        this.user = user;
        dirNameUserActiv = "data\\" + user.getNickname();
        dirNameUserArchiv = "data\\archiv\\" + user.getNickname();
        fileNameUserActiv = "data\\" + user.getNickname() + "\\task.dat";
        fileNameUserArchiv = "data\\archiv\\" + user.getNickname() + "\\task.dat";

        dir = new File(dirNameUserActiv);
        dirinArchiv = new File(dirNameUserArchiv);

        file = new File(fileNameUserActiv);
        fileinArchiv = new File(fileNameUserArchiv);

    }

    public boolean createNewUser() {
        if (checkRegistration()) {
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    WriterSerial wt = new WriterSerial();
                    wt.writeUserTask(user);
                    SessionByUser session = new SessionByUser(user);
                    wt.writeUserSession(user, session);
                    System.out.println("ManageUser: Username is created!");
                    return true;
                } else {
                    System.out.println("ManageUser: Failed to create username!");
                    return false;
                }
            } else {
                System.out.println("ManageUser: Username/password Exist!");
                return false;
            }
        }
        return false;
    }

    public void deleteUserforUSER() {
        if (file.exists()) {
            if (file.renameTo(fileinArchiv)) {
                System.out.println("ManageUser: User remove in Archiv!");
            } else {
                System.out.println("ManageUser: Failed to remove username!");
            }
        } else {
            System.out.println("ManageUser: Username/password no Exist!");
        }
    }

    public void deleteUserforADMIN() throws IOException {
        if (dir.exists()) {
            file.delete();
            dir.delete();
            System.out.println("ManageUser: User delete in DATA");
        } else {
            System.out.println("ManageUser: Username/password no Exist in DATA!");
        }
        if (dirinArchiv.exists()) {
            fileinArchiv.delete();
            dirinArchiv.delete();
            System.out.println("ManageUser: User delete in Archiv");
        } else {
            System.out.println("ManageUser: Username/password no Exist in Archiv!");
        }
    }

    private boolean checkRegistration() {
        if (dir.exists())
            return false;
        else return true;
    }

    public boolean checkAutorisation() {
        if (dir.exists())
            return true;
        else return false;
    }

    public String getHashUser() {
        return user.getHashUser();
    }
}
