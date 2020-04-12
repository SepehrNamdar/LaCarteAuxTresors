package client.writer;

public class CanNotWriteOutputFile extends RuntimeException {
    public CanNotWriteOutputFile(String message) {
        super(message);
    }
}
