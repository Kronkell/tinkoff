package edu.project3;

public record LogRecord(
    String remoteAddr,
    String remoteUser,
    String timeLocal,
    String request,
    String status,
    String bodyBytesSent,
    String httpReferer,
    String httpUserAgent
) {}
