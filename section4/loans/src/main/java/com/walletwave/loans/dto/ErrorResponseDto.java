package com.walletwave.loans.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error information"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;
    private LocalDateTime errorTime;

    public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
    }
}
