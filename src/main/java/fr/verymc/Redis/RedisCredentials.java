package fr.verymc.Redis;

public class RedisCredentials {
    private final String address;
    private final String password;
    private final int port;
    private final String clientName;

    public RedisCredentials(String address, String password, int port, String clientName) {
        this.address = address;
        this.password = password;
        this.port = port;
        this.clientName = clientName;
    }

    public RedisCredentials(String address, String password, int port) {
        this(address, password, port, "VelocityAccess");
    }

    public RedisCredentials(String address, String password, String clientName) {
        this(address, password, 6379, clientName);
    }

    public RedisCredentials(String address, String password) {
        this(address, password, 6379, "VelocityAccess");
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getClientName() {
        return clientName;
    }

    public String getRedisURL() {
        return "redis://" + address + ":" + port;
    }
}