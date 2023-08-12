import logic.ManageTask;
import model.User;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
//        //если такого пользователя нет
        ManageTask mt2 = new ManageTask( new User("segam1", "123"));
        System.out.println(mt2.getListTasks());

        //если нет такого пользователя и надо его зарегистрировать
//        ManageUser mu = new ManageUser(new User ("segam1","789"));
//        mu.createNewUser();

        //пользователь есть такой, получаем список его задач
//        ManageTask mt = new ManageTask( new User("segam111", "789"));
//        System.out.println(mt.getListTasks());

        //пользователь есть такой, добавляем задачу в список
//        ManageTask mt = new ManageTask( new User("segam1", "789"));
//        mt.addTask("First task", "First description for task");
//        mt.addTask("Two task", "First description for task");
//        mt.addTask("Three task", "First description for task");
//        mt.addTask("Four task", "First description for task");
//        System.out.println(mt.getListTasks());

//        //пользователь есть, перемещаем задачи между актив и архив у него задачу по id
//        ManageTask mt = new ManageTask( new User("segam1", "789"));
//        mt.removeTaskinArchiv(1);
//        System.out.println(mt.getListTasks().getActivTasks());
//        System.out.println(mt.getListTasks().getArchivTasks());

        //пользователь есть, удаляем задачу из архива
//        ManageTask mt = new ManageTask( new User("segam1", "789"));
//        mt.deleteTaskisArchiv(1);
//        System.out.println(mt.getListTasks().getActivTasks());
//        System.out.println(mt.getListTasks().getArchivTasks());


        //пользователь удаляет сам себя
//        ManageUser mu = new ManageUser( new User("segam1", "789"));
//        mu.createNewUser();
//        mu.deleteUserforUSER();

        //пользователя удаляет admin
//        ManageUser mu = new ManageUser( new User("segam1", "789"));
//        mu.createNewUser();
//        mu.deleteUserforADMIN();




    }
}
