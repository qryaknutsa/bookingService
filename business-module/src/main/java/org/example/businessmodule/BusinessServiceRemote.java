package org.example.businessmodule;

import jakarta.ejb.Remote;
import org.example.businessmodule.dto.EventRead;
import org.example.businessmodule.dto.EventWrite;
import org.example.businessmodule.model.Event;

import java.util.List;

@Remote
public interface BusinessServiceRemote {
    List<EventRead> getAll(String token);
    EventRead getById(String token, String idStr);
    Event save(String token, EventWrite dto);
    void delete(String token, String eventIdStr);
    Object copyTicketWithDoublePriceAndVip(String token, String ticketIdStr, String personIdStr);
}