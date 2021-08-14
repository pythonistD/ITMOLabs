package control;

import java.io.Serializable;

public class Response implements Serializable{
    private static final long serialVersionUID = 1L;
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

    public void viewResponse(){
        System.out.println(commandName + "\n" + commandStringArgument);
    }


}
