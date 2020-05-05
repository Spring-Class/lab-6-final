package com.luxoft.springcore.aspects;

import com.luxoft.springcore.events.TravelEvent;
import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.Country;
import com.luxoft.springcore.objects.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    private final static Logger LOG = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* *.arrivalToDestination*(..))")
    public void arrivalToDestinationMethod() {
    }

    @Around("arrivalToDestinationMethod() ")
    public Object arrivalToDestinationLogger(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object result = thisJoinPoint.proceed();

        Object[] methodArgs = thisJoinPoint.getArgs();
        TravelEvent travelEvent = (TravelEvent) methodArgs[0];
        Person person = travelEvent.getPerson();
        City city = travelEvent.getTravellingDestination();
        Country country = city != null ? city.getCountry() : null;
        String personName = person != null ? person.getName() : "";
        String countryDestination = country != null ? city.toString() : "";

        LOG.info(String.format("%s has arrived to %s", personName, countryDestination));
        return result;
    }

}
