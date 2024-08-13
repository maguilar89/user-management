package nisum.com.user_management.domain.exception;

public class RecordAlreadyExistException extends RuntimeException{

    public RecordAlreadyExistException(String message){
        super(message);
    }

    public RecordAlreadyExistException(String message, Throwable tw){
        super(message, tw);
    }

}
