package com.savoirtech.ibex.api.test;

import com.savoirtech.ibex.IbexExchange;
import com.savoirtech.ibex.api.IbexProcessor;

public class SillyProcessor implements IbexProcessor {

    @Override
    public String toString() {
        return "HOLY MOLY! Sillyy";
    }

    @Override
    public void process(IbexExchange exchange) {
        //TODO
    }
}

