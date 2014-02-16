package com.savoirtech.ibex.java.test;

import com.savoirtech.ibex.api.IbexProcessor;
import com.savoirtech.ibex.router.actors.IbexRouteBuilder;
import org.apache.camel.Exchange;
import org.junit.Test;


import static org.junit.Assert.assertNotNull;

public class ScalaApiVerification {

    @Test
    public void createIbexRoute() {
        assertNotNull(routeBuilder);
    }

    IbexRouteBuilder routeBuilder = new IbexRouteBuilder() {
        @Override
        public void configure() {
            from("A").process(new IbexProcessor() {
                @Override
                public void process(Exchange exchange) {
                    //TODO
                }
            }).to("BOB").process(new IbexProcessor() {
                @Override
                public void process(Exchange exchange) {
                    //TODO
                }
            });
        }
    };
}
