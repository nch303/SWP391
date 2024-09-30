package koicare.koiCareProject.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message){
        super(message);// kế thừa của RuntimeException
    }
}
