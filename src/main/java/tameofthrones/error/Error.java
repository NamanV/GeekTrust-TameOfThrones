package tameofthrones.error;

public enum Error {
    NO_ALLIES("NONE"),
    INVALID_ARGUMENTS("NONE"),
    KINGDOM_NOT_VALID("KINGDOM_NOT_VALID");
    private String errorMessage;
    private Error(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }
}
