package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class DataWebSocketClient extends WebSocketClient {
    private DataStorage dataStorage;

    public DataWebSocketClient(URI serverUri, DataStorage dataStorage) throws URISyntaxException {
        super(serverUri);
        this.dataStorage = dataStorage;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to WebSocket server");
    }

    @Override
    public void onMessage(String message) throws NumberFormatException {
        System.out.println("Received data: " + message);
        // Parse the message and store it in the data storage
        //  Assuming messege is in CSV format
        try {
            String[] parts = message.split(",");
            if (parts.length != 4) {
                throw new IllegalArgumentException("Invalid message format");
            }
            int patientId = Integer.parseInt(parts[0]);
            long timestamp = Long.parseLong(parts[1]);
            String label = parts[2];
            double measurementValue = Double.parseDouble(parts[3]);

            // Store the data using the DataStorage class method
            dataStorage.addPatientData(patientId, measurementValue, label, timestamp);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println("Error processing message: " + message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from WebSocket server: " + reason);
        // Attempt to reconnect after a short delay
        try {
            Thread.sleep(5000);
            this.reconnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // Attempt to reconnect immediately
        this.reconnect();
    }

    public static void main(String[] args) {
        try {
            DataStorage dataStorage = new DataStorage(); // Initialize your DataStorage
            DataWebSocketClient client = new DataWebSocketClient(new URI("ws://localhost:8080"), dataStorage);
            client.connect();

            // Add a shutdown hook to properly close the WebSocket connection
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
