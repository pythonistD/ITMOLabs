package control.commands;

import MyExceptions.CommandException;
import database.User;

public class Register extends Command{
    private static final long serialVersionUID = 112L;
    private User user;
    @Override
    public void execute() throws CommandException {


    }

    public Register(User user) {
        this.user = user;
    }
}
