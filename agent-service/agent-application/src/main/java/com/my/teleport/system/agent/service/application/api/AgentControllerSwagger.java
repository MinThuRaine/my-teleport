package com.my.teleport.system.agent.service.application.api;

import io.swagger.v3.oas.annotations.media.Schema;

public class AgentControllerSwagger {

    private AgentControllerSwagger() {
        // don't allow to instantiate; use only for live API documentation
    }

    @Schema(description = "CreateAgentResponse")
    public static final class CreateAgentResponse {

        @Schema(example = "166")
        public String agentId;
        @Schema(example = "pending")
        public String agentStatus;
        @Schema(example = "Agent Created!!")
        public String message;

        private CreateAgentResponse() {
            // dont allow to initiatiate
        }
    }

    @Schema(description = "CreateAgentResponse")
    public static final class CreateAgentCommand {
        @Schema(example = "Grace")
        public String name;
        @Schema(example = "28")
        public Integer age;
        @Schema(example = "998271")
        public String identityNumber;
        @Schema(example = "927363612")
        public Integer phoneNo;

        private CreateAgentCommand() {
            // dont allow to initiatiate
        }
    }



    @Schema(description = "UpdateAgentStatusCommand")
    public static final class UpdateAgentStatusCommand {

        private UpdateAgentStatusCommand() {
            // dont allow to initiatiate
        }

        @Schema(example = "d14d09be-821c-4a25-b66e-6b7b6fb67a7c")
        public String agentId;

        @Schema(example = "300")
        public Integer status;


    }


    @Schema(description = "UpdateAgentStatus")
    public static final class UpdateAgentStatus {

        private UpdateAgentStatus() {
            // dont allow to initiatiate
        }

        @Schema(example = "d14d09be-821c-4a25-b66e-6b7b6fb67a7c")
        public String agentId;

        @Schema(example = "Agent Status Update successfully")
        public String message;


    }


    @Schema(description = "AgentData")
    public static final class AgentData {

        private AgentData() {
            // dont allow to initiatiate
        }

        @Schema(example = "Grace")
        public String name;

        @Schema(example = "28")
        public Integer age;

        @Schema(example = "998271")
        public String identityNumber;

        @Schema(example = "927363612")
        public Integer phoneNo;

    }



}
