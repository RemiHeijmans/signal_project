package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.AlertInterface;
import com.alerts.PriorityAlertDecorator;
import com.alerts.RepeatedAlertDecorator;

class MockAlert implements AlertInterface {
    private int triggerCount = 0;

    @Override
    public void trigger() {
        triggerCount++;
    }

    public int getTriggerCount() {
        return triggerCount;
    }

    @Override
    public int getPatientId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPatientId'");
    }

    @Override
    public String getCondition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCondition'");
    }

    @Override
    public long getTimestamp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimestamp'");
    }
}

public class AlertDecoratorTest {
    private MockAlert mockAlert;

    @BeforeEach
    public void setUp() {
        mockAlert = new MockAlert();
    }

    @Test
    public void testPriorityAlertDecorator() {
        AlertInterface priorityAlert = new PriorityAlertDecorator(mockAlert, "HIGH");
        priorityAlert.trigger();
        assertEquals(1, mockAlert.getTriggerCount());
    }

    @Test
    public void testRepeatedAlertDecorator() {
        AlertInterface repeatedAlert = new RepeatedAlertDecorator(mockAlert, 500, 2);
        repeatedAlert.trigger();
        assertEquals(2, mockAlert.getTriggerCount());
    }
}
