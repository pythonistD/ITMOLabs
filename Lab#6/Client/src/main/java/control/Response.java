package control;

import java.io.Serializable;
import java.util.Deque;
import java.util.Iterator;

public class Response implements Serializable{
    private static final long serialVersionUID = 1L;
    private String commandName;
    private String commandStringArgument;
    private Serializable commandObjectArgument;
    private Deque<Response> responseDeque;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
    public void extractResponses(){
        Iterator<Response> responseIterator = getResponseDeque().iterator();
        while (responseIterator.hasNext()){
            responseIterator.next().viewResponse();
        }
    }
    public boolean isHardResponse(){
        if(responseDeque.size() == 0){
            return false;
        }
        return true;
    }

    public String getCommandStringArgument() {
        return commandStringArgument;
    }

    public void setCommandStringArgument(String commandStringArgument) {
        this.commandStringArgument = commandStringArgument;
    }

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

    public Deque<Response> getResponseDeque() {
        return responseDeque;
    }

    public void viewResponse(){
        System.out.println(this.commandName + "\n" + this.commandStringArgument);
    }


}
