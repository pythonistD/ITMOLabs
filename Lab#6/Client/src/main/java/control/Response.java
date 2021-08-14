package control;

import java.io.Serializable;

public class Response implements Serializable{
    private String commandName;
    private String commandStringArgument;
    private Serializable commandObjectArgument;

    public Response(String commandName, String commandStringArgument, Serializable commandObjectArgument) {
        this.commandName = commandName;
        this.commandStringArgument = commandStringArgument;
        this.commandObjectArgument = commandObjectArgument;
    }
    public Response(String commandName, String commandStringArgument) {
        this.commandName = commandName;
        this.commandStringArgument = commandStringArgument;
    }
    public Response(){}


}
