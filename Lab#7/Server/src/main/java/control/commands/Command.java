package control.commands;

import MyExceptions.CommandException;
import control.Response;
import model.Dragon;

import java.io.Serializable;

public class Command implements Serializable {
    private static final long serialVersionUID = 3947034539840505591L;
    private Response response;


    public void execute() throws CommandException {


    }

    public Dragon getById(long id) throws NullPointerException{
        return Dragon.getDragonsCollection().stream().filter(dragon -> dragon.getId() == id).findFirst().orElse(null);
    }

    public Response getResponse() {
        return this.response;
    }

}
