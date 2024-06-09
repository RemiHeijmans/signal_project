package com.alerts;

public interface AlertInterface {
    int getPatientId();
    String getCondition();
    long getTimestamp();
    void trigger();
    String getType();
}
