package com.my.teleport.system.orderservice.application.order.api;

import com.my.teleport.system.order.service.domain.order.dto.create.*;
import com.my.teleport.system.order.service.domain.order.ports.input.OrderApplicationService;
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
@RequestMapping(value = "/orders")
@Tag(name = "Order", description = "Order create api for create, update, delete, select order information.")
public class OrderController {


    private final OrderApplicationService orderApplicationService;


    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    @Operation(tags = {
            "Order Create"}, summary = "Create Order with createOrderCommand", description = "This is a first step to  create process when the customer want to delivery from pick up location to target location.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = OrderControllerSwagger.CreateOrderCommand.class, description = "Request Body")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = OrderControllerSwagger.CreateOrderResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Creating order for client: {} ", createOrderCommand.getClientId());
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with tracking id: {}", createOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }

    @PostMapping("/process")
    @Operation(tags = {
            "Order Process"}, summary = "Process Order with processOrderCommand", description = "This is step to confirm process when admin get the customer confirm for delivery process.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = OrderControllerSwagger.ConfirmOrderCommand.class, description = "Request Body")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = OrderControllerSwagger.ConfirmOrderResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<OrderProcessingRespond> processOrder(@RequestBody OrderProcessingCommand processOrderCommand) {
        log.info("Confirm order : {} ", processOrderCommand.getOrderId());
        OrderProcessingRespond processOrderRespond = orderApplicationService.processOrder(processOrderCommand);
        log.info("Order confirmed with tracking id: {}", processOrderRespond.getOrderTrackingId());
        return ResponseEntity.ok(processOrderRespond);
    }

    @PostMapping("/cancel")
    @Operation(tags = {
            "Order Cancel"}, summary = "Cancel Order with orderCancelCommand", description = "This is step to cancel process when the order is cancelled with any reason.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = OrderControllerSwagger.ConfirmOrderCommand.class, description = "Request Body")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = OrderControllerSwagger.ConfirmOrderResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<OrderCancelRespond> cancelOrder(@RequestBody OrderCancelCommand orderCancelCommand) {
        log.info("Cancel order : {} ", orderCancelCommand.getOrderId());
        OrderCancelRespond orderCancelRespond = orderApplicationService.cancelOrder(orderCancelCommand);
        log.info("Order cancelled with tracking id: {}", orderCancelRespond.getOrderTrackingId());
        return ResponseEntity.ok(orderCancelRespond);
    }


    @GetMapping("/test")
    @Operation(tags = {
            "Order Controller Test Api"}, summary = "Test Api", description = " This Api is use for test server url.")
    public String testOrderController() {
        return "HELLO Order Service";
    }


}
