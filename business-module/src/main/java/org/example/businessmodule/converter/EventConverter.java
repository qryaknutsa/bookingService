package org.example.businessmodule.converter;

import org.example.businessmodule.dto.EventRead;
import org.example.businessmodule.dto.EventWrite;
import org.example.businessmodule.model.Event;

public class EventConverter {

    public static Event toEvent(EventWrite dto){
        Event event = new Event();
        event.setTitle(dto.getTitle());
        if(dto.getDescription() != null) event.setDescription(dto.getDescription());
        event.setPrice(dto.getPrice());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setCoordinates(CoordinatesConverter.toCoordinates(dto.getCoordinates()));
        event.setLocation(LocationConverter.toLocation(dto.getLocation()));
        event.setDiscount(dto.getDiscount());
        return event;
    }


    public static EventWrite toEventWrite(Event dto){
        EventWrite eventWrite = new EventWrite();
        eventWrite.setTitle(dto.getTitle());
        if(dto.getDescription() != null) eventWrite.setDescription(dto.getDescription());
        eventWrite.setPrice(dto.getPrice());
        eventWrite.setStartTime(dto.getStartTime());
        eventWrite.setEndTime(dto.getEndTime());
        eventWrite.setCoordinates(CoordinatesConverter.toCoordinatesWrite(dto.getCoordinates()));
        eventWrite.setLocation(LocationConverter.toLocationWrite(dto.getLocation()));
        eventWrite.setDiscount(dto.getDiscount());
        return eventWrite;
    }

    public static EventRead toEventRead(Event dto){
        EventRead eventWrite = new EventRead();
        eventWrite.setId(dto.getId());
        eventWrite.setTitle(dto.getTitle());
        if(dto.getDescription() != null) eventWrite.setDescription(dto.getDescription());
        eventWrite.setPrice(dto.getPrice());
        eventWrite.setStartTime(dto.getStartTime());
        eventWrite.setEndTime(dto.getEndTime());
        eventWrite.setCoordinates(CoordinatesConverter.toCoordinatesWrite(dto.getCoordinates()));
        eventWrite.setLocation(LocationConverter.toLocationWrite(dto.getLocation()));
        eventWrite.setDiscount(dto.getDiscount());
        return eventWrite;
    }
}
