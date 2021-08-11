package MyExceptions;

public class IncorrectIdException extends Exception{
    private long bagId;
    public IncorrectIdException(String message, long bagId){
        super(message);
        this.bagId = bagId;
    }

    public long getBagId() {
        return this.bagId;
    }
}
