package client;

public class CanNotWriteOutputFile extends RuntimeException {
    public CanNotWriteOutputFile(String message) {
        super(message);
    }
}
