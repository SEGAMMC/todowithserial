package logic;

import model.User;

import java.io.File;
import java.io.IOException;

public class ManageUser {
    private User user;

    private final String dirNameUserActiv = "data\\" + user.getNickname() + "$$" + user.getPassword();
    private final String dirNameUserArchiv = "data\\archiv\\" + user.getNickname() + "$$" + user.getPassword();
    private final String fileNameUserActiv = "data\\" + user.getNickname() + "$$" + user.getPassword() + "\\bez.dat";
    private final String fileNameUserArchiv = "data\\archiv\\" + user.getNickname() + "$$" + user.getPassword() + "\\bez.dat";

    private File dir = new File(dirNameUserActiv);
    private File dirinArchiv = new File(dirNameUserArchiv);

    private File file = new File(fileNameUserActiv);
    private File fileinArchiv = new File(fileNameUserArchiv);


    public ManageUser(User user) {
        this.user = user;
    }

    public void createNewUser() {
        if (checkRegistration()) {
            if (!dirinArchiv.exists()) {
                if (dirinArchiv.mkdir()) {
                    WriterTaskSerial wt = new WriterTaskSerial();
                    wt.writeUserTask(user);
                    System.out.println("ManageUser: Username is created!");
                } else {
                    System.out.println("ManageUser: Failed to create username!");
                }
            } else {
                System.out.println("ManageUser: Username/password Exist!");
            }
        }
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
}
