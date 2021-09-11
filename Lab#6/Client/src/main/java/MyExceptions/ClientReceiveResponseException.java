package MyExceptions;

import java.net.SocketException;

public class ClientReceiveResponseException extends SocketException{
    public ClientReceiveResponseException(String errorMessage){
            super(errorMessage);
    }
}
