package MyExceptions;

public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }

    public static CommandException createExceptionChain(Exception e, String message) throws CommandException {
        CommandException commandException = new CommandException(message);
        commandException.initCause(e);
        return commandException;
    }
}
