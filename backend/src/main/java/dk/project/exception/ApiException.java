package dk.project.exception;

public class ApiException extends RuntimeException {

    // Attributes
    private int code;

    // __________________________________________________

    public ApiException(int code, String msg){
        super(msg);
        this.code = code;
    }

    // __________________________________________________

    public int getCode(){
        return code;
    }

}