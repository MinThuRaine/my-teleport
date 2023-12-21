package com.my.teleport.system.orderservice.application.order.api;

import com.my.teleport.system.order.service.domain.order.dto.create.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class OrderControllerSwagger {


    private OrderControllerSwagger() {
        // don't allow to instantiate; use only for live API documentation
    }

    @Schema(description = "CreateOrderResponse")
    public static final class CreateOrderResponse {

        private CreateOrderResponse() {
            // dont allow to initiatiate
        }

        @Schema(example = "1428")
        public  String orderTrackingId;

        @Schema(example = "Success")
        public  String orderStatus;

        @Schema(example = "Order Created Complete")
        public  String message;

    }

    @Schema(description = "ConfirmOrderResponse")
    public static final class ConfirmOrderResponse {

        private ConfirmOrderResponse() {
            // dont allow to initiatiate
        }

        @Schema(example = "1428")
        public  String orderTrackingId;

        @Schema(example = "Success")
        public  String orderStatus;

        @Schema(example = "Order Created Complete")
        public  String message;

    }

    @Schema(description = "CreateOrderCommand")
    public static final class CreateOrderCommand {
        private CreateOrderCommand() {
            // dont allow to initiatiate
        }
        @Schema(example = "1")
        public String clientId;

        public PickupOrderAddressDto pickupAddress;

        public DeliveryOrderAddressDto deliveryAddress;

        @Schema(example = "9582716382")
        public Integer phoneNo;

        @Schema(example = "1")
        public String remark;

        @Schema(example = "1")
        public List<OrderItem> items;

    }

    @Schema(description = "Order Confirm")
    public class ConfirmOrderCommand {

        private ConfirmOrderCommand() {
            // dont allow to initiatiate
        }

        @Schema(example = "2920123-23930432-34932-342332")
        public String orderId;

    }

    @Schema(description = "PickupOrderAddressDto")
    public static final class  PickupOrderAddressDto {

        private PickupOrderAddressDto() {
            // dont allow to initiatiate
        }

        @Schema(example = "No1 Street")
        public String street;

        @Schema(example = "3192")
        public String postalCode;

        @Schema(example = "1")
        public Integer region;

        @Schema(example = "2")
        public Integer township;

    }


    @Schema(description = "DeliveryOrderAddressDto")
    public static final class  DeliveryOrderAddressDto {

        private DeliveryOrderAddressDto() {
            // dont allow to initiatiate
        }

        @Schema(example = "No1 Street")
        public String street;

        @Schema(example = "3192")
        public String postalCode;

        @Schema(example = "1")
        public Integer region;

        @Schema(example = "2")
        public Integer township;

    }


}
