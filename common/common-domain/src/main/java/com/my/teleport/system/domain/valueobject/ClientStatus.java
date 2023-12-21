package com.my.teleport.system.domain.valueobject;

public enum ClientStatus {
    PENDING(100, "clientStatusType.pending"),
    ACTIVE(300, "clientStatusType.active"),
    CLOSED(600, "clientStatusType.closed"),
    REJECTED(700, "clientStatusType.rejected"),
    BLOCK(800, "clientStatusType.blocked");

    private final Integer value;
    private final String code;

    ClientStatus(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public static ClientStatus fromInt(final Integer statusValue) {

        ClientStatus enumeration = ClientStatus.PENDING;
        switch (statusValue) {
            case 100:
                enumeration = ClientStatus.PENDING;
                break;
            case 300:
                enumeration = ClientStatus.ACTIVE;
                break;
            case 600:
                enumeration = ClientStatus.CLOSED;
                break;
            case 700:
                enumeration = ClientStatus.REJECTED;
                break;
            case 800:
                enumeration = ClientStatus.BLOCK;
                break;
        }
        return enumeration;
    }

    public boolean hasStateOf(final ClientStatus state) {
        return this.value.equals(state.getValue());
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isPending() {
        return this.value.equals(ClientStatus.PENDING.getValue());
    }

    public boolean isActive() {
        return this.value.equals(ClientStatus.ACTIVE.getValue());
    }

    public boolean isClosed() {
        return this.value.equals(ClientStatus.CLOSED.getValue());
    }

    public boolean isBlock() {
        return this.value.equals(ClientStatus.BLOCK.getValue());
    }

    public boolean isRejected() {
        return this.value.equals(ClientStatus.REJECTED.getValue());
    }

}
