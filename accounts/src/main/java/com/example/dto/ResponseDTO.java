package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"

)
public class ResponseDTO {

    @Schema(
            description = "Status code in the response", example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response", example = "Request processed successfully"
    )
    private String statusMsg;

    public ResponseDTO() {
    }

    public ResponseDTO(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "statusCode='" + statusCode + '\'' +
                ", statusMsg='" + statusMsg + '\'' +
                '}';
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}
