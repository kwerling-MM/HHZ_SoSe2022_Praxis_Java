package com.hhz;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    Calc c;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        c = new Calc();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        c = null;
    }

    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(42, c.add(20, 22));
        assertEquals(42, c.add(22, 22));
    }

    @org.junit.jupiter.api.Test
    void add2() {
        assertEquals(42, c.add(20, 22));
        assertEquals(42, c.add(22, 22));
    }
}