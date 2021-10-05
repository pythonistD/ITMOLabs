package control.commands;

import MyExceptions.CommandException;
import control.Response;
import database.User;
import database.UserHandler;

import java.sql.SQLException;

public class Register extends Command{
    private static final long serialVersionUID = 112L;
    private User user;
    private Response response;
    @Override
    public void execute() throws CommandException {
        try {
            if(!UserHandler.userExist(user.getName())){
                UserHandler.addUser(user.getName(),user.getPass());
                response = new Response("Пользователь успешно добавлен");
                response.setFlag(true);
            }else{
                response = new Response("Пользователь уже существует");
            }

        } catch (SQLException e) {
            throw new CommandException("Пользователь не добавлен");
        }


    }
    @Override
    public Response getResponse() {
        return response;
    }

}
