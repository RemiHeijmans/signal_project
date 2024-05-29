package com.alerts;

public class PriorityAlertDecorator extends AlertDecorator {
    private String priorityLevel;

    public PriorityAlertDecorator(AlertInterface decoratedAlert, String priorityLevel) {
        super(decoratedAlert);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void trigger() {
        System.out.println("Priority: " + priorityLevel);
        super.trigger();
    }
}
