package com.alerts;

public class PriorityAlertDecorator implements AlertInterface {
    private AlertInterface alert;
    private String priority;

    public PriorityAlertDecorator(AlertInterface alert, String priority) {
        this.alert = alert;
        this.priority = priority;
    }

    @Override
    public int getPatientId() {
        return alert.getPatientId();
    }

    @Override
    public String getCondition() {
        return alert.getCondition();
    }

    @Override
    public long getTimestamp() {
        return alert.getTimestamp();
    }

    @Override
    public void trigger() {
        alert.trigger();
    }


}
