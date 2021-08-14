package control.commands;

import control.Response;

import java.io.Serializable;

public class Command implements Serializable {
    private Response response;


    public void execute() throws Exception {


    }
    public Response getResponse(){
        return this.response;
    }

}
