package com.cenyol.study;

/**
 * Created by cenyol on 22/03/2017.
 */
public class ServerHost {
    private String protocol = "tcp";
    private String ip = "121.40.130.184";
    private int port = 1883;

    public String getBrokerAddress() {
        return protocol + "://" + ip + ":" + port;
    }

    @Override
    public String toString() {
        return "ServerHost{" +
                "protocol='" + protocol + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
