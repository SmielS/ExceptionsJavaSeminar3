package org.example;

import org.example.controller.UserController;
import org.example.model.FileOperation;
import org.example.model.Repository;
import org.example.view.ViewUser;

public class Main {

    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperation();
        Repository repository = new Repository(fileOperation);
        UserController controller = new UserController(repository);
        ViewUser view = new ViewUser(controller);
        view.run();
    }
}
