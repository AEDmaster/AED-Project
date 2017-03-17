package com.company;

import java.util.Arrays;

/**
 * Created by lamec on 3/17/2017.
 */
public class ElectricityInformation extends InformationType {
    double percentage;
    int year;

    public ElectricityInformation(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public Number getValue() {
        return percentage;
    }
}
