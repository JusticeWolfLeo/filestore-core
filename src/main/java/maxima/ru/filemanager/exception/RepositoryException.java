package maxima.ru.filemanager.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryException extends RuntimeException {

    private final ErrorCode errorCode;

    private final String message;
}

