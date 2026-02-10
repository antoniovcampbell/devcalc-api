package com.devcalc.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceImplTest {

    private final CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

    @Test
    void testAdd() {
        assertEquals(15, calculatorService.add(10, 5));
        assertEquals(-5, calculatorService.add(-10, 5));
        assertEquals(0, calculatorService.add(0, 0));
        assertEquals(100, calculatorService.add(50, 50));
    }

    @Test
    void testSubtract() {
        assertEquals(5, calculatorService.subtract(10, 5));
        assertEquals(-15, calculatorService.subtract(-10, 5));
        assertEquals(0, calculatorService.subtract(0, 0));
        assertEquals(10, calculatorService.subtract(20, 10));
    }

    @Test
    void testMultiply() {
        assertEquals(50, calculatorService.multiply(10, 5));
        assertEquals(-50, calculatorService.multiply(-10, 5));
        assertEquals(0, calculatorService.multiply(0, 5));
        assertEquals(100, calculatorService.multiply(10, 10));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculatorService.divide(10, 5));
        assertEquals(-2, calculatorService.divide(-10, 5));
        assertEquals(0, calculatorService.divide(0, 5));
        assertEquals(5, calculatorService.divide(25, 5));
    }

    @Test
    void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculatorService.divide(10, 0)
        );
        assertEquals("Divisor cannot be zero", exception.getMessage());
    }

    @Test
    void testImplementsInterface() {
        assertTrue(calculatorService instanceof CalculatorService);
    }
}