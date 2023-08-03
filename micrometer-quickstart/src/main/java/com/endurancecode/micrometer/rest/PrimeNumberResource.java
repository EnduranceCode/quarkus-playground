package com.endurancecode.micrometer.rest;


import io.micrometer.core.instrument.MeterRegistry;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/api")
@Produces("text/plain")
public class PrimeNumberResource {

    private static final String MESSAGE_IS_NOT_PRIME = " is not prime.";
    private static final String MESSAGE_IST_PRIME = " is prime.";

    private final MeterRegistry registry;

    PrimeNumberResource(MeterRegistry registry) {
        this.registry = registry;
    }

    @GET
    @Path("prime/{number}")
    public String checkIfPrime(@PathParam("number") long number) {
        if (number < 1) {
            return "Only natural numbers can be prime numbers.";
        }
        if (number == 1) {
            return number + MESSAGE_IS_NOT_PRIME;
        }
        if (number == 2 || number % 2 == 0) {
            return number + MESSAGE_IS_NOT_PRIME;
        }
        if (testPrimeNumber(number)) {
            return number + MESSAGE_IST_PRIME;
        } else {
            return number + MESSAGE_IS_NOT_PRIME;
        }
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
