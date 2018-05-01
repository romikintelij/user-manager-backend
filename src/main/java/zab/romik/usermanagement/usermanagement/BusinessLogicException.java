package zab.romik.usermanagement.usermanagement;


/** business logic error*/
public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(String message) {
        super(message);
    }
}
