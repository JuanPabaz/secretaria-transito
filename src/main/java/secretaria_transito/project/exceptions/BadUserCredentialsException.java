package secretaria_transito.project.exceptions;

public class BadUserCredentialsException extends RuntimeException{

    public BadUserCredentialsException(String message){
        super(message);
    }

}
