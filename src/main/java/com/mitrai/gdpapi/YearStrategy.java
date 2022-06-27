package com.mitrai.gdpapi;


import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import java.util.Random;

public class YearStrategy implements AttributeStrategy<Long> {

    public Long getValue() throws PodamMockeryException {
        Random r = new Random();
        int low = 1961;
        int high = 2020;
        return r.nextInt(high-low) + (long)low;
    }

}