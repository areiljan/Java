package ee.taltech.iti0202.files.input;

public class FileReaderException extends RuntimeException {
    /**
     * Special exception for readers.
     * @param message - why did the file not read in string format.
     * @param reason - the reason.
     */
    public FileReaderException(String message, Throwable reason) {
        super(message, reason);
    }
}
