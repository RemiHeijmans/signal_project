package com.alerts;

public class RepeatedAlertDecorator extends AlertDecorator {
    private int repeatInterval;
    private int repeatCount;

    public RepeatedAlertDecorator(AlertInterface decoratedAlert, int repeatInterval, int repeatCount) {
        super(decoratedAlert);
        this.repeatInterval = repeatInterval;
        this.repeatCount = repeatCount;
    }

    @Override
    public void trigger() {
        for (int i = 0; i < repeatCount; i++) {
            super.trigger();
            try {
                Thread.sleep(repeatInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
