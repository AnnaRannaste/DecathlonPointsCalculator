package com.decathlonapplication.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecathlonServiceTest {

    @Test
    public void testCalculatePointsForTrackEvent() {
        DecathlonService decathlonService = new DecathlonService();
        double result = decathlonService.calculatePoints("100M", 10.0);
        System.out.println("Calculated points: " + result);
        assertEquals(1097, result, "Incorrect points calculation for 100m track event");
    }


    @Test
    public void testCalculatePointsForFieldEvent() {
        DecathlonService decathlonService = new DecathlonService();
        double result = decathlonService.calculatePoints("LONG JUMP", 700);
        System.out.println("Calculated points: " + result);
        assertEquals(814, result, "Incorrect points calculation for Long jump field event");
    }

    @Test
    public void testUnknownEvent() {
        DecathlonService decathlonService = new DecathlonService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> decathlonService.calculatePoints("UnknownEvent", 10.0));
        assertEquals("Unknown event: UnknownEvent", exception.getMessage(), "Unknown event exception not thrown");
    }
}
