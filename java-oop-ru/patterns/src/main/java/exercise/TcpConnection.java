package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class TcpConnection {

    private String ipAddress;
    private int port;
    private List<String> buffer = new ArrayList<>();
    private Connection connection;

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void connect() {
        connection.connect();
    }

    public void write(String data) {
        connection.write(data);
    }

    public void disconnect() {
        connection.disconnect();
    }

    public String getCurrentState() {
        return this.getConnection().getCurrentState();
    }

    public void addToBuffer(String data) {
        buffer.add(data);
    }
}
// END
