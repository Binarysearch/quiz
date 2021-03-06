package net.desenlace.quiz;

public class ApiException extends RuntimeException {
    
    private static final long serialVersionUID = -6619414531801379041L;
    private int errorCode;

    public ApiException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode(){
        return errorCode;
    }

}