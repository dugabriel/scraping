package com.scraping.domain;

public enum Frequency {
    DAY(1440),
    MIDDAY(720),
    HOUR(60),
    HALF(30),
    TEST(1);
    private int value;
    private Frequency(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
