package control.commands;

import MyExceptions.CommandException;
import control.Response;
import database.User;
import database.UserHandler;
import model.Dragon;

import java.sql.SQLException;

public class Login extends Command{
    private static final long serialVersionUID = 111L;
    private Response response;
    private User user;
    @Override
    public void execute() throws CommandException {
        try {
            if(UserHandler.accountExist(user.getName(),user.getPass())){
                response = new Response("login","Пользователь успешно авторизован");
                response.setFlag(true);
            }
            if(!UserHandler.accountExist(user.getName(),user.getPass())){
                response = new Response("login","Пользователя не существует в БД");
            }
        } catch (SQLException e) {
            throw new CommandException("Ошибка при добавлении");
        }

    }
    @Override
    public Response getResponse() {
        return response;
    }
}
