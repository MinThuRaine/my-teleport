package com.my.teleport.system.clientservice.application.api;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClientControllerSwagger {


    private ClientControllerSwagger() {
        // don't allow to instantiate; use only for live API documentation
    }

    @Schema(description = "CreateClientResponse")
    public static final class CreateClientResponse {

        private CreateClientResponse() {
            // dont allow to initiatiate
        }

        @Schema(example = "234")
        public String clientId;
        @Schema(example = "pending")
        public String clientStatus;
        @Schema(example = "Client Created!!")
        public String message;


    }

    @Schema(description = "CreateClientResponse")
    public static final class CreateClientCommand {

        private CreateClientCommand() {
            // dont allow to initiatiate
        }

        @Schema(example = "Jack")
        public String name;
        @Schema(example = "33")
        public Integer age;
        @Schema(example = "227788")
        public String identityNumber;
        @Schema(example = "983764721")
        public Integer phoneNo;


    }


}
