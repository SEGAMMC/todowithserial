package logic;

import model.User;

import java.io.*;


public class ReaderTaskSerial {
    private User user;

    public ReaderTaskSerial() {
        user = new User("", "");
    }

    public User readMyTasks(User userLogPass) {
        String nameFile = "data\\" + userLogPass.getNickname() + "$$" + userLogPass.getPassword() + "\\bez.dat";
        try (FileInputStream fileRead = new FileInputStream(nameFile);
             ObjectInputStream allTask = new ObjectInputStream(fileRead);) {
            user = (User) allTask.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("ReaderTaskSerial: File not find");
            System.out.println(user.getNickname() + "  " + user.getPassword());
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return (User) user;
    }
}
