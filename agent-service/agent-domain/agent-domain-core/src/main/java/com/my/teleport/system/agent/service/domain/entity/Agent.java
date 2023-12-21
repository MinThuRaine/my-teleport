package com.my.teleport.system.agent.service.domain.entity;

import com.my.teleport.system.domain.entity.AggregateRoot;
import com.my.teleport.system.domain.exception.GeneralPlatformDomainRuleException;
import com.my.teleport.system.domain.valueobject.AgentId;
import com.my.teleport.system.domain.valueobject.AgentStatus;
import com.my.teleport.system.domain.valueobject.ClientStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Agent extends AggregateRoot<AgentId> {

    private  final String name;

    private final Integer age;

    private AgentStatus status;

    private final String identityNumber;

    private final Integer phoneNo;


    public  void initializeAgent(){
        setId(new AgentId(UUID.randomUUID().toString()));
        status = AgentStatus.PENDING;
    }

    public void updateAgentStatus(AgentStatus updatedStatus){

        switch (updatedStatus) {
            case ACTIVE:
                activeAgent();
                break;
            case CLOSED:
                break;
            case REJECTED:
                break;
            case BLOCK:
                break;
        }
    }

    public  void rejectAgent(){
        if(!this.status.isPending()){
            throw  new GeneralPlatformDomainRuleException("error.msg.agent.invalid.state.to.reject","Agent is not validate state to reject.");
        }
        this.status = AgentStatus.REJECTED;
    }

    public  void closeAgentAccount(){
        this.status = AgentStatus.CLOSED;
    }

    public  void blockAgent(){
        if(!this.status.isActive()){
            throw  new GeneralPlatformDomainRuleException("error.msg.agent.invalid.state.to.block","Agent is not validate state to block.");
        }
        this.status = AgentStatus.BLOCK;
    }


    public void activeAgent(){
        if(!this.status.isPending()){
            throw  new GeneralPlatformDomainRuleException("error.msg.agent.invalid.state.to.activate","Agent is not validate state to activae.");
        }
        this.status = AgentStatus.ACTIVE;
    }

    private Agent(Builder builder) {
        name = builder.name;
        age = builder.age;
        status = builder.agentStatus;
        identityNumber = builder.identityNumber;
        phoneNo = builder.phoneNo;
        super.setId(builder.agentId);
    }


    public boolean isActive() {
        return ClientStatus.fromInt(this.status.getValue()).isActive();
    }

    public boolean isClosed() {
        return ClientStatus.fromInt(this.status.getValue()).isClosed();
    }

    public boolean isRejected() {
        return ClientStatus.fromInt(this.status.getValue()).isRejected();
    }

    public boolean isBlock() {
        return ClientStatus.fromInt(this.status.getValue()).isBlock();
    }

    public boolean isNotPending() {
        return !isPending();
    }

    public boolean isPending() {
        return ClientStatus.fromInt(this.status.getValue()).isPending();
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private Integer age;
        private AgentStatus agentStatus;
        private String identityNumber;
        private Integer phoneNo;
        private AgentId agentId;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder age(Integer val) {
            age = val;
            return this;
        }

        public Builder agentStatus(AgentStatus val) {
            agentStatus = val;
            return this;
        }

        public Builder identityNumber(String val) {
            identityNumber = val;
            return this;
        }

        public Builder phoneNo(Integer val) {
            phoneNo = val;
            return this;
        }

        public Builder agentId(AgentId val) {
            agentId = val;
            return this;
        }

        public Agent build() {
            return new Agent(this);
        }
    }
}
