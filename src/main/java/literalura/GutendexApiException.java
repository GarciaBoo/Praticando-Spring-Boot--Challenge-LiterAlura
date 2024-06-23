package literalura;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GutendexApiException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(GutendexApiException.class);

    public GutendexApiException(String message) {
        super(message);
        logger.error(message);
    }

    public GutendexApiException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, cause);
    }
}