package MyExceptions;

import java.net.SocketException;

public class ServerReceiveException extends SocketException {
    public ServerReceiveException(String errorMessage) {
        super(errorMessage);
    }
}
