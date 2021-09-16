package MyExceptions;

import java.net.SocketException;

public class ClientSendRequestException extends SocketException {
    public ClientSendRequestException(String errorMessage){
        super(errorMessage);
    }
}
