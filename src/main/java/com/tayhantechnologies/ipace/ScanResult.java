package com.tayhantechnologies.ipace;

public final class ScanResult {
        private int port;
        private boolean isOpen;
        // constructor
        // getters


    public int getPort() {
        return port;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
