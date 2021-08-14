package control.commands;

import control.Response;

import java.io.Serializable;

public class Command implements Serializable {


    public Response execute() throws Exception {
        return new Response();

    }

}
