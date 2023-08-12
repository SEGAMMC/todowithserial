package logic;

import model.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriterTaskSerial {

    public void writeUserTask(User user) {
        String nameFile = "data\\" + user.getNickname() + "$$" + user.getPassword() + "\\bez.dat";
        try (FileOutputStream fileOutputStream = new FileOutputStream(nameFile);
             ObjectOutputStream listTask = new ObjectOutputStream(fileOutputStream)) {
            listTask.writeObject(user);
            System.out.println("WriterTaskSerial: Task write in file");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
