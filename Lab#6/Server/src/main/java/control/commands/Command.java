package control.commands;

import MyExceptions.CommandException;
import control.Response;

import java.io.Serializable;

public class Command implements Serializable {
    private static final long serialVersionUID = 3947034539840505591L;
    private Response response;


    public void execute() throws CommandException {


    }
    public Response getResponse(){
        return this.response;
    }

}
