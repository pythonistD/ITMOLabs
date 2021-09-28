package control;

import java.io.Serializable;
import java.util.Deque;
import java.util.Iterator;

public class Response implements Serializable{
    private static final long serialVersionUID = 1L;
    private boolean flag;
    private String commandStringArgument;
    private Serializable commandObjectArgument;
    private Deque<Response> responseDeque;


    public void extractResponses(){
        Iterator<Response> responseIterator = getResponseDeque().iterator();
        while (responseIterator.hasNext()){
            responseIterator.next().viewResponse();
        }
    }
    public boolean isHardResponse(){
        return responseDeque.size() != 0;
    }

    public String getCommandStringArgument() {
        return commandStringArgument;
    }

    public void setCommandStringArgument(String commandStringArgument) {
        this.commandStringArgument = commandStringArgument;
    }


    public Response(){}

    public Deque<Response> getResponseDeque() {
        return responseDeque;
    }

    public void viewResponse(){
        System.out.println(this.commandStringArgument + "\n" + this.commandObjectArgument);
    }

    public boolean isFlag() {
        return flag;
    }
}
