package MyExceptions;

import java.net.SocketException;

public class ServerSendResponseException extends SocketException {
    public ServerSendResponseException(String errorMessage){
        super(errorMessage);
    }
}
