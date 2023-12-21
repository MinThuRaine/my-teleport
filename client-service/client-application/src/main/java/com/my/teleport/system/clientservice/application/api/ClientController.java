package com.my.teleport.system.clientservice.application.api;


import com.my.teleport.system.client.service.domain.create.CreateClientCommand;
import com.my.teleport.system.client.service.domain.create.CreateClientResponse;
import com.my.teleport.system.client.service.domain.ports.input.ClientWriteApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/clients")
@Tag(name = "Client", description = "Client API for create, update, delete, select agent information.")
public class ClientController {

    private final ClientWriteApplicationService clientWriteApplicationService;


    public ClientController(ClientWriteApplicationService clientWriteApplicationService) {
        this.clientWriteApplicationService = clientWriteApplicationService;
    }


    @PostMapping
    @Operation(tags = {
            "Agent Create"}, summary = "Create Agent with createAgentCommand", description = "Agent Create API")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = ClientControllerSwagger.CreateClientCommand.class, description = "Request Body")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ClientControllerSwagger.CreateClientResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity createClient(@RequestBody CreateClientCommand createClientCommand) {
        log.info(" Create Client ");

        CreateClientResponse createClientResponse = clientWriteApplicationService.createClient(createClientCommand);

        log.info(" Client created ID :: {}", createClientResponse.getClientId());

        return ResponseEntity.ok(createClientResponse);
    }


    @Operation(tags = {
            "Client Controller Test Api"}, summary = "Test Api", description = " This Api is use for test server url.")
    @GetMapping("/test")
    public String testClientController() {
        return "HELLO Client Service";
    }


}
