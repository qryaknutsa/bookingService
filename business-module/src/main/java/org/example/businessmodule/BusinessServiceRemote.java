package org.example.businessmodule;

import jakarta.ejb.Remote;
import org.example.businessmodule.dto.EventRead;
import org.example.businessmodule.dto.EventWrite;
import org.example.businessmodule.model.Event;

import java.util.List;

@Remote
public interface BusinessServiceRemote {
    List<EventRead> getAll();
    EventRead getById(String idStr);
    Event save(EventWrite dto);
    void delete(String eventIdStr);
    Object copyTicketWithDoublePriceAndVip(String ticketIdStr, String personIdStr);
}