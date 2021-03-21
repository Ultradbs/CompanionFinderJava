package com.company;

import com.company.controllers.Controller;
import com.company.database.PostgresDB;
import com.company.database.IDB;
import com.company.repositories.User;

public class Main {

    public static void main(String[] args) {
        IDB db = new PostgresDB();
        User repo = new User(db);
        Controller controller = new Controller(repo);
        Application app = new Application(controller);
        app.start();
    }
}
