package ua.nure.piontkovskyi.SummaryTask4.exception;

import ua.nure.piontkovskyi.SummaryTask4.util.serializer.StreamSerializer;

/**
 * {@code SerializerException} is thrown by {@link StreamSerializer}
 * when it cannot serialize or deserialize object or list of objects.
 */
public class SerializerException extends RuntimeException {

    /**
     * Creates a new {@code SerializerException} object with a specified message and cause.
     *
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }

}
