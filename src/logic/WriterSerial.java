package logic;

import model.SessionByUser;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriterSerial {

    public void writeUserTask(User user) {
        String nameFile = "data\\" + user.getNickname() + "\\task.dat";
        try (FileOutputStream fileOutputStream = new FileOutputStream(nameFile);
             ObjectOutputStream listTask = new ObjectOutputStream(fileOutputStream)) {
            listTask.writeObject(user);
            System.out.println("WriterSerial: writeUserTask - Task write in file");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }


    public void writeUserSession(User user, SessionByUser session) {
        String nameFile = "data\\" + user.getNickname() + "\\session.dat";
        try (FileOutputStream fileOutputStream = new FileOutputStream(nameFile);
             ObjectOutputStream listSession = new ObjectOutputStream(fileOutputStream)) {
            listSession.writeObject(session);
            System.out.println("WriterSerial: writeUserSession - Session write in file");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

}
