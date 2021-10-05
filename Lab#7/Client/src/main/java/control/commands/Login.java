package control.commands;

import MyExceptions.CommandException;
import control.Response;
import database.User;

public class Login extends Command{
    private static final long serialVersionUID = 111L;
    private User user;
    @Override
    public void execute() throws CommandException {

    }

    public Login(User user) {
        this.user = user;
    }
}
