package control.commands;

import control.Response;

import java.io.Serializable;

public class Command implements Serializable {
    private static final long serialVersionUID = 3947034539840505591L;
    private Response response;


    public void execute() throws Exception {


    }
    public Response getResponse(){
        return this.response;
    }

}
