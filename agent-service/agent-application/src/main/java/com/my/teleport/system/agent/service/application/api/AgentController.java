package com.my.teleport.system.agent.service.application.api;

import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentCommand;
import com.my.teleport.system.agent.service.domain.dto.create.CreateAgentResponse;
import com.my.teleport.system.agent.service.domain.dto.query.AgentDataQuery;
import com.my.teleport.system.agent.service.domain.dto.query.AgentDataResponse;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusCommand;
import com.my.teleport.system.agent.service.domain.dto.update.UpdateAgentStatusResponse;
import com.my.teleport.system.agent.service.domain.ports.input.AgentReadApplicationService;
import com.my.teleport.system.agent.service.domain.ports.input.AgentWriteApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping(value = "/agent")
@Tag(name = "Agent", description = "Agent API for create, update, delete, select agent information.")
public class AgentController {


    private final AgentWriteApplicationService agentWriteApplicationService;
    private final AgentReadApplicationService agentReadApplicationService;


    public AgentController(AgentReadApplicationService agentReadApplicationService, AgentWriteApplicationService agentWriteApplicationService) {
        this.agentWriteApplicationService = agentWriteApplicationService;
        this.agentReadApplicationService = agentReadApplicationService;
    }

    @PostMapping
    @Operation(tags = {
            "Agent Create"}, summary = "Create Agent with createAgentCommand", description = "Agent Create API")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = AgentControllerSwagger.CreateAgentCommand.class, description = "Request Body")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = AgentControllerSwagger.CreateAgentResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity createAgent(@RequestBody CreateAgentCommand createAgentCommand) {
        log.info(" Create Agent ");

        CreateAgentResponse createAgentResponse = agentWriteApplicationService.createAgent(createAgentCommand);

        log.info(" Agent created ID :: {}", createAgentResponse.getAgentId());

        return ResponseEntity.ok(createAgentResponse);
    }

    @GetMapping("{agentId}")
    @Operation(summary = "Retrieve an Agent", description = "Example Requests:\n" + "\n" + "Agent/1\n" + "\n" + "\n"
            + "/agent/asfdas-21321-213ddd")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AgentControllerSwagger.AgentData.class))) })
    public ResponseEntity getAgentById(@PathParam("agentId") @Parameter(description = "agentId") final AgentDataQuery agentDataQuery
                                       ){

        log.info("Get Agent Data By ID :: {} ::", agentDataQuery.getAgentId());

        AgentDataResponse agentDataResponse = agentReadApplicationService.getAgentByID(agentDataQuery);

        return  ResponseEntity.ok(agentDataResponse);
    }


    @PutMapping("/updateStatus")
    @Operation(summary = "Update status Agent", description = "Example Requests:\n" + "\n" + "Agent/1\n" + "\n" + "\n"
            + "/updateStatus")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = AgentControllerSwagger.UpdateAgentStatusCommand.class, description = "Request Body")))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AgentControllerSwagger.UpdateAgentStatus.class))) })
    public ResponseEntity updateStatusAgent(@RequestBody UpdateAgentStatusCommand updateAgentStatusCommand
    ){

        log.info("Agent_ID to Update Status :: {} ::", updateAgentStatusCommand.getAgentId());

        UpdateAgentStatusResponse updateAgentStatusResponse = agentWriteApplicationService.updateAgentStatus(updateAgentStatusCommand);

        return  ResponseEntity.ok(updateAgentStatusResponse);
    }

    @Operation(tags = {
            "Agent Controller Test Api"}, summary = "Test Api", description = " This Api is use for test server url.")
    @GetMapping("/test")
    public String testAgentController() {
        return "HELLO Agent Service";
    }

}
