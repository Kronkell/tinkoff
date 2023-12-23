package edu.project3;

public record Info(
    Resources resources,
    StatusCodes statusCodes,
    GeneralInfo generalInfo,
    RemoteAddresses remoteAddresses,
    UserAgents userAgents
) {
}
