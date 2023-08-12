package logic;

import model.ListTasks;
import model.Task;
import model.User;

import java.io.File;


public class ManageTask {
    private User user;
    private ListTasks listTasks;

    public ManageTask(User user) {
        this.user = user;
        listTasks = new ListTasks();
    }

    public ListTasks getListTasks() {
        if (checkAutorization()) {
            this.listTasks = user.getListTasks();
            return listTasks;
        } else {
            return getListTaskifnotFind();
        }
    }

    public void addTask(String title, String description) {
        readTaskByFile();
        listTasks.addTask().add(new Task(listTasks.getCounter(), title, description));
        writeTaskByFile();
    }

    public void removeTaskinArchiv(int id) {
        readTaskByFile();
        listTasks.removeTaskinArchiv(id);
        writeTaskByFile();
    }

    public void removeTaskinActiv(int id) {
        readTaskByFile();
        listTasks.removeTaskinActiv(id);
        writeTaskByFile();
    }

    public void deleteTaskisArchiv(int id) {
        readTaskByFile();
        listTasks.deleteTaskisArchiv(id);
        writeTaskByFile();
    }

    private boolean checkAutorization() {
        String dirName = "data\\" + user.getNickname() + "$$" + user.getPassword();
        File file = new File(dirName);
        if (file.exists()) {
            readTaskByFile();
            return true;
        } else return false;
    }

    private void readTaskByFile() {
        ReaderTaskSerial readTaskSerial = new ReaderTaskSerial();
        this.user = readTaskSerial.readMyTasks(user);
        this.listTasks = user.getListTasks();
        System.out.println("ManageTask: Read Task by file");
    }

    private void writeTaskByFile() {
        user.setListTasks(listTasks);
        WriterTaskSerial writerTaskSerial = new WriterTaskSerial();
        writerTaskSerial.writeUserTask(user);
        System.out.println("ManageTask: Write Task in file");
    }

    private ListTasks getListTaskifnotFind() {
        ListTasks listNotFind = new ListTasks();
        listNotFind.addTask().add(new Task(-1, "User not Find", ""));
        return listNotFind;
    }
}
