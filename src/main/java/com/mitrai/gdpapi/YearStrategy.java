package com.mitrai.gdpapi;


import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import java.util.Random;

public class YearStrategy implements AttributeStrategy<Long> {
    Random r = new Random();
    public Long getValue() throws PodamMockeryException {
        int low = 1961;
        int high = 2020;
        return r.nextInt(high-low) + (long)low;
    }

}