package com.endurancecode.micrometer.rest;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import java.util.LinkedList;
import java.util.NoSuchElementException;

@Path("/api")
@Produces("text/plain")
public class PrimeNumberResource {

    private static final String EMPTY_STRING = "";

    private static final String MESSAGE_IS_NOT_PRIME = " is not prime.";
    private static final String MESSAGE_IS_PRIME = " is prime.";

    private static final String PRIME_NUMBER_COUNTER_NAME = "api.prime.number";
    private static final String PRIME_NUMBER_TEST_TIMER_NAME = "api.prime.number.test";
    private static final String LIST_GAUGE_NAME = "api.list.size";
    private static final String MICROMETER_LABEL_TYPE = "type";

    private final MeterRegistry registry;
    private final LinkedList<Long> list = new LinkedList<>();

    PrimeNumberResource(MeterRegistry registry) {
        this.registry = registry;
        registry.gaugeCollectionSize(LIST_GAUGE_NAME, Tags.empty(), list);
    }

    @GET
    @Path("prime/{number}")
    public String checkIfPrime(@PathParam("number") long number) {
        if (number < 1) {
            registry.counter(PRIME_NUMBER_COUNTER_NAME, MICROMETER_LABEL_TYPE, "not-natural").increment();
            return "Only natural numbers can be prime numbers.";
        }
        if (number == 1) {
            registry.counter(PRIME_NUMBER_COUNTER_NAME, MICROMETER_LABEL_TYPE, "one").increment();
            return number + MESSAGE_IS_NOT_PRIME;
        }
        if (number == 2 || number % 2 == 0) {
            registry.counter(PRIME_NUMBER_COUNTER_NAME, MICROMETER_LABEL_TYPE, "even").increment();
            return number + MESSAGE_IS_NOT_PRIME;
        }
        if (timedTestPrimeNumber(number)) {
            registry.counter(PRIME_NUMBER_COUNTER_NAME, MICROMETER_LABEL_TYPE, "prime").increment();
            return number + MESSAGE_IS_PRIME;
        } else {
            registry.counter(PRIME_NUMBER_COUNTER_NAME, MICROMETER_LABEL_TYPE, "not-prime").increment();
            return number + MESSAGE_IS_NOT_PRIME;
        }
    }

    @GET
    @Path("gauge/{number}")
    public Long checkListSize(@PathParam("number") long number) {
        if (number == 2 || number % 2 == 0) {
            // Add even numbers to the list
            list.add(number);
        } else {
            // Remove items from the list for odd numbers
            try {
                number = list.removeFirst();
            } catch (NoSuchElementException nse) {
                number = 0;
            }
        }
        return number;
    }

    protected boolean timedTestPrimeNumber(long number) {
        Timer.Sample sample = Timer.start(registry);
        boolean result = testPrimeNumber(number);
        sample.stop(registry.timer(PRIME_NUMBER_TEST_TIMER_NAME,MICROMETER_LABEL_TYPE, result + EMPTY_STRING));
        return result;
    }

    protected boolean testPrimeNumber(long number) {
        for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
