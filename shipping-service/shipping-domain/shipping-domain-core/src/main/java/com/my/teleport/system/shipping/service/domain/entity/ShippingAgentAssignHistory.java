package com.my.teleport.system.shipping.service.domain.entity;

import com.my.teleport.system.domain.valueobject.AgentId;
import com.my.teleport.system.domain.valueobject.ShippingId;

import java.time.ZonedDateTime;

public class ShippingAgentAssignHistory {

    private final ShippingId shippingId;

    private final AgentId agentId;

    private final ZonedDateTime startDate;

    private final ZonedDateTime endDate;

    private ShippingAgentAssignHistory(Builder builder) {
        shippingId = builder.shippingId;
        agentId = builder.agentId;
        startDate = builder.startDate;
        endDate = builder.endDate;
    }

    public static ShippingAgentAssignHistory.Builder builder() {
        return new ShippingAgentAssignHistory.Builder();
    }


    public static final class Builder {
        private ShippingId shippingId;
        private AgentId agentId;
        private ZonedDateTime startDate;
        private ZonedDateTime endDate;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder shippingId(ShippingId val) {
            shippingId = val;
            return this;
        }

        public Builder agentId(AgentId val) {
            agentId = val;
            return this;
        }

        public Builder startDate(ZonedDateTime val) {
            startDate = val;
            return this;
        }

        public Builder endDate(ZonedDateTime val) {
            endDate = val;
            return this;
        }

        public ShippingAgentAssignHistory build() {
            return new ShippingAgentAssignHistory(this);
        }
    }
}
