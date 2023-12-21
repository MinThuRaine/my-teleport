package com.my.teleport.system.domain.valueobject;

public enum AgentStatus {

    PENDING(100, "agentStatusType.pending"),
    ACTIVE(300, "agentStatusType.active"),
    CLOSED(600, "agentStatusType.closed"),
    REJECTED(700, "agentStatusType.rejected"),
    BLOCK(800, "agentStatusType.blocked");

    private final Integer value;
    private final String code;

    AgentStatus(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public static AgentStatus fromInt(final Integer statusValue) {

        AgentStatus enumeration = AgentStatus.PENDING;
        switch (statusValue) {
            case 100:
                enumeration = AgentStatus.PENDING;
                break;
            case 300:
                enumeration = AgentStatus.ACTIVE;
                break;
            case 600:
                enumeration = AgentStatus.CLOSED;
                break;
            case 700:
                enumeration = AgentStatus.REJECTED;
                break;
            case 800:
                enumeration = AgentStatus.BLOCK;
                break;
        }
        return enumeration;
    }

    public boolean hasStateOf(final AgentStatus state) {
        return this.value.equals(state.getValue());
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isPending() {
        return this.value.equals(AgentStatus.PENDING.getValue());
    }

    public boolean isActive() {
        return this.value.equals(AgentStatus.ACTIVE.getValue());
    }

    public boolean isClosed() {
        return this.value.equals(AgentStatus.CLOSED.getValue());
    }

    public boolean isBlock() {
        return this.value.equals(AgentStatus.BLOCK.getValue());
    }

    public boolean isRejected() {
        return this.value.equals(AgentStatus.REJECTED.getValue());
    }

}
