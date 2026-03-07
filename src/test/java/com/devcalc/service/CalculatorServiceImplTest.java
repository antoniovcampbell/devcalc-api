package com.devcalc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceImplTest {

    private final CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

    @Test
    void testAdd() {
        assertEquals(15, calculatorService.add(10, 5));
    }

    @Test
    void testSubtract() {
        assertEquals(5, calculatorService.subtract(10, 5));
    }

    @Test
    void testMultiply() {
        assertEquals(50, calculatorService.multiply(10, 5));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculatorService.divide(10, 5));
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
    void testSqrt() {
        assertEquals(4.0, calculatorService.sqrt(16));
    }

    @Test
    void testSqrtNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculatorService.sqrt(-1)
        );
        assertEquals("Negative numbers are not allowed", exception.getMessage());
    }

    @Test
    void testImplementsInterface() {
        assertTrue(calculatorService instanceof CalculatorService);
    }
}