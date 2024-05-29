package com.alerts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AlertDecoratorTest {
    private AlertInterface alert;

    @BeforeEach
    public void setUp() {
        alert = Mockito.mock(AlertInterface.class);
    }

    @Test
    public void testPriorityAlertDecorator() {
        AlertInterface priorityAlert = new PriorityAlertDecorator(alert, "HIGH");
        priorityAlert.trigger();
        Mockito.verify(alert, Mockito.times(1)).trigger();
    }

    @Test
    public void testRepeatedAlertDecorator() {
        AlertInterface repeatedAlert = new RepeatedAlertDecorator(alert, 500, 2);
        repeatedAlert.trigger();
        Mockito.verify(alert, Mockito.times(2)).trigger();
    }
}
