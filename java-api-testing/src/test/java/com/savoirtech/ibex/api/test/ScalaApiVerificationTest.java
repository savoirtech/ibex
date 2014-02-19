package com.savoirtech.ibex.api.test;

import com.savoirtech.ibex.IbexExchange;
import com.savoirtech.ibex.api.IbexProcessor;
import com.savoirtech.ibex.router.actors.IbexRouteBuilder;
import org.junit.Test;


import static org.junit.Assert.assertNotNull;

public class ScalaApiVerificationTest {

    @Test
    public void createIbexRoute() {
        assertNotNull(routeBuilder);
        routeBuilder.stepsInRoute();
    }

    IbexRouteBuilder routeBuilder = new IbexRouteBuilder() {

        @Override
        public void configure() {
            System.out.println("QQQQ");

            fromc("A").process(new IbexProcessor() {
                @Override
                public String toString() {
                    return "HOLY MOLY!";
                }

                @Override
                public void process(IbexExchange exchange) {
                    //TODO
                }
            }).toc("BOB").process(new DummyProcessor());

            fromc("a").process(new SillyProcessor());
        }
    };

    class DummyProcessor implements IbexProcessor {

        @Override
        public String toString() {
            return "HOLY MOLY! Dummy";
        }

        @Override
        public void process(IbexExchange exchange) {
            //TODO
        }
    }
}
