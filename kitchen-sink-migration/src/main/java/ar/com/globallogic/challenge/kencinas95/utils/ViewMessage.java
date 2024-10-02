package ar.com.globallogic.challenge.kencinas95.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record ViewMessage(
        Severity severity,
        String summary,

        String detail) {
    @AllArgsConstructor
    public enum Severity {
        INFO("info"), ERROR("error-message");

        @Getter
        private final String value;
    }

    public static ViewMessage info(final String summary, final String detail) {
        return new ViewMessage(Severity.INFO, summary, detail);
    }

    public static ViewMessage error(final String summary, final String detail) {
        return new ViewMessage(Severity.ERROR, summary, detail);
    }

    public static String getMessageFromException(final Exception ex) {
        String message = "Something went wrong!";
        if (ex == null) return message;
        Throwable t = ex;
        while (t != null) {
            message = t.getMessage();
            t = t.getCause();
        }
        return message;
    }
}
