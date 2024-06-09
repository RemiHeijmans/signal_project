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

    @Override
    public String getType() {
        return alert.getType();
    }
}

public class RepeatedAlertDecorator implements AlertInterface {
    private AlertInterface alert;
    private int interval;
    private int repetitions;

    public RepeatedAlertDecorator(AlertInterface alert, int interval, int repetitions) {
        this.alert = alert;
        this.interval = interval;
        this.repetitions = repetitions;
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
        for (int i = 0; i < repetitions; i++) {
            alert.trigger();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String getType() {
        return alert.getType();
    }
}
