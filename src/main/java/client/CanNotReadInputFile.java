package client;

public class CanNotReadInputFile extends RuntimeException {
    public CanNotReadInputFile(String message) {
        super(message);
    }
}
