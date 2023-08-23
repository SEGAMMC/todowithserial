package logic;

import model.SessionByUser;
import model.User;

import java.io.*;


public class ReaderSerial {
    private User user;
    private SessionByUser session;

    public ReaderSerial() {
        user = new User("", "");
    }

    public User readMyTasks(User userLogPass) {
        String nameFile = "data\\" + userLogPass.getNickname() + "\\task.dat";
        try (FileInputStream fileRead = new FileInputStream(nameFile);
             ObjectInputStream allTask = new ObjectInputStream(fileRead);) {
            user = (User) allTask.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("ReaderTaskSerial: readMyTasks - File not find");
            System.out.println(user.getNickname() + "  " + user.getPassword());
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return (User) user;
    }

    public SessionByUser readMySession(String nickname) {
        String nameFile = "data\\" + nickname + "\\session.dat";
        try (FileInputStream fileRead = new FileInputStream(nameFile);
             ObjectInputStream allSession = new ObjectInputStream(fileRead);) {
            session = (SessionByUser) allSession.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("ReaderSerial: readMySession - File not find");
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return (SessionByUser) session;
    }
}
