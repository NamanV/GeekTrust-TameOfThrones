package tameofthrones.error;

public class TameOfThronesException extends Exception {
    private Error error;

    public TameOfThronesException(Error error){
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getErrorMessage();
    }
}
