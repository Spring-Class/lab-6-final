package com.luxoft.springcore.services;

import com.luxoft.springcore.events.TravelEvent;
import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PersonManager {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishPersonEvent(Person person, City destination) {
        TravelEvent travelEvent = new TravelEvent(person, destination);
        applicationEventPublisher.publishEvent(travelEvent);
    }
}
