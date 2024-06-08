package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

public class WebSocketDataReader extends WebSocketClient implements DataReader {
    private DataStorage dataStorage;

    public WebSocketDataReader(URI serverUri, DataStorage dataStorage) throws URISyntaxException {
        super(serverUri != null ? serverUri : new URI("ws://localhost:8080")); // Default URI for testing
        this.dataStorage = dataStorage;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to WebSocket server");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received data: " + message);
        // Parse the message and store it in the data storage
        try {
            String[] parts = message.split(",");
            int patientId = Integer.parseInt(parts[0]);
            long timestamp = Long.parseLong(parts[1]);
            String label = parts[2];
            double measurementValue = Double.parseDouble(parts[3]);

            dataStorage.addPatientData(patientId, measurementValue, label, timestamp);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.err.println("Invalid message format: " + message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from WebSocket server");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        throw new UnsupportedOperationException("readData not implemented for real-time WebSocket data");
    }

    @Override
    public void startRealTimeData(DataStorage dataStorage, String uri) throws Exception {
        this.dataStorage = dataStorage;
        this.connect();
    }

    @Override
    public void stopRealTimeData() {
        this.close();
    }

    public static void main(String[] args) {
        try {
            DataStorage dataStorage = DataStorage.getInstance();
            WebSocketDataReader reader = new WebSocketDataReader(new URI("ws://localhost:8080"), dataStorage);
            reader.startRealTimeData(dataStorage, "ws://localhost:8080");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    reader.stopRealTimeData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
