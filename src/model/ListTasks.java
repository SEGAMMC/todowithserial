package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ListTasks implements Serializable {
    private List<Task> listActivTask = new ArrayList<Task>();
    private List<Task> listArchivTask = new ArrayList<Task>();
    private int counter;


    public int getCounter() {
        return ++counter;
    }

    public List<Task> getActivTasks() {
        return listActivTask;
    }

    public List<Task> getArchivTasks() {
        return listArchivTask;
    }

    public List<Task> addTask() {
        return listActivTask;
    }

    public ListTasks removeTaskinArchiv(int idTask) {
        int findId = -1;
        for (int i = 0; i < listActivTask.size(); i++)
            if (listActivTask.get(i).getId() == idTask) findId = i;
        if (findId < 0) {
            System.out.println("ListTasks: Remove TaskinArchiv not found ID");
            return this;
        }
        listArchivTask.add(listActivTask.get(findId));
        listActivTask.remove(findId);
        return this;
    }

    public ListTasks removeTaskinActiv(int idTask) {
        int findId = -1;
        for (int i = 0; i < listArchivTask.size(); i++)
            if (listArchivTask.get(i).getId() == idTask) findId = i;
        if (findId < 0) {
            System.out.println("ListTasks: Remove TaskinActiv not found ID");
            return this;
        }
        listActivTask.add(listArchivTask.get(findId));
        listArchivTask.remove(findId);
        return this;
    }

    public ListTasks deleteTaskisArchiv(int idTask) {
        int findId = -1;
        for (int i = 0; i < listArchivTask.size(); i++)
            if (listArchivTask.get(i).getId() == idTask) findId = i;
        if (findId < 0) {
            System.out.println("ListTasks: delete TaskisAchiv not found ID");
            return this;
        }
        listArchivTask.remove(findId);
        return this;
    }

    @Override
    public String toString() {
        return "ListTasks{\n" +
                "listActivTask=" + listActivTask +
                ",\nlistArchivTask=" + listArchivTask +
                '}';
    }
}
