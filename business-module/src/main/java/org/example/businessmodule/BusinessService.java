package org.example.businessmodule;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.businessmodule.converter.EventConverter;
import org.example.businessmodule.dto.EventRead;
import org.example.businessmodule.dto.EventWrite;
import org.example.businessmodule.dto.TicketWithEventWrite;
import org.example.businessmodule.dto.TicketWrite;
import org.example.businessmodule.exception.*;
import org.example.businessmodule.model.Event;
import org.example.businessmodule.model.Person;
import org.jboss.ejb3.annotation.Pool;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Pool("soa")
public class BusinessService implements BusinessServiceRemote {
    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;


    @Override
    public List<EventRead> getAll() {
        List<Event> list = em.createQuery("SELECT l FROM Event l", Event.class).getResultList();
        List<EventRead> toReturn = new ArrayList<>();

        for (Event e : list) {
            EventRead eventRead = EventConverter.toEventRead(e);
            eventRead.setTicketsNum(TicketService.findTicketsByEventId(e.getId()));
            toReturn.add(eventRead);
        }

        if (toReturn.isEmpty()) throw new CustomNotFound("По вашему запросу мероприятия не найдены.");
        return toReturn;
    }

    @Override
    public EventRead getById(String idStr) {
        int id = validateId(idStr);
        Event e = em.find(Event.class, id);
        if (id < 0) {
            List<String> messages = new ArrayList<>();
            messages.add("Значение event_id должно быть больше нуля");
            throw new InvalidParameter(messages);
        }
        if (e == null) throw new CustomNotFound("По вашему запросу мероприятие не найдено.");
        return EventConverter.toEventRead(e);
    }

    @Override
    public Event save(EventWrite dto) {
        Event event = EventConverter.toEvent(dto);
        em.persist(event);
        em.flush();

        TicketWithEventWrite ticket = new TicketWithEventWrite();

        ticket.setCoordinates(dto.getCoordinates());
        ticket.setPrice(dto.getPrice());
        ticket.setName(dto.getTitle());
        ticket.setDiscount(dto.getDiscount());
        ticket.setEventId(event.getId());

        Object object = TicketService.saveTickets(ticket, dto.getTicketsNum());
        List<Integer> tickets = new ArrayList<>();
        String str = "";
        if (object instanceof String) str = (String) object;
        else tickets = (List<Integer>) object;

        if (!str.isEmpty()) {
            em.remove(event);
            throw new TicketServiceNotAvailable(str);
        } else if (tickets.size() != dto.getTicketsNum()) {
            em.remove(event);
            throw new TicketServiceNotAvailable("Не вышло сохранить такое количество билетов");
        }
        return event;
    }

    @Override
    public void delete(String eventIdStr) {
        int event_id = validateId(eventIdStr);
        if (event_id < 0) {
            List<String> messages = new ArrayList<>();
            messages.add("Значение event_id должно быть больше нуля");
            throw new InvalidParameter(messages);
        }

        Event event = em.find(Event.class, event_id);
        if (event != null) {
            if (event.getStartTime().isBefore(ZonedDateTime.now()))
                throw new TooLateToDelete("Мероприятие уже началось, отменить невозможно.");
            else if (ZonedDateTime.now().isAfter(event.getEndTime()))
                throw new TooLateToDelete("Мероприятие уже прошло, отменить невозможно.");
            else {
                TicketService.deleteTickets(event_id);
                em.remove(event);
            }
        } else {
            throw new CustomNotFound("По вашему запросу мероприятие не найдено.");
        }
    }


    @Override
    public Object copyTicketWithDoublePriceAndVip(String ticketIdStr, String personIdStr) {
        int ticketId = 0;
        int personId = 0;
        boolean invalidTicketId = false;
        boolean invalidPersonId = false;
        List<String> messages = new ArrayList<>();

        try {
            ticketId = Integer.parseInt(ticketIdStr);
        } catch (NumberFormatException e) {
            invalidTicketId = true;
        }
        try {
            personId = Integer.parseInt(personIdStr);
        } catch (NumberFormatException e) {
            invalidPersonId = true;
        }
        if (invalidTicketId && invalidPersonId) {
            messages.add("Некорректное значение параметра ticket_id: " + ticketIdStr);
            messages.add("Некорректное значение параметра person_id: " + personIdStr);
            throw new IncorrectParameter(messages);
        }
        if (invalidTicketId) {
            messages.add("Некорректное значение параметра ticket_id: " + ticketIdStr);
            throw new IncorrectParameter(messages);
        }
        if (invalidPersonId) {
            messages.add("Некорректное значение параметра person_id: " + personIdStr);
            throw new IncorrectParameter(messages);
        }


        if (ticketId < 0) invalidTicketId = true;
        if (personId < 0) invalidPersonId = true;
        if (invalidTicketId && invalidPersonId) {
            messages.add("Значение ticket_id должно быть больше нуля");
            messages.add("Значение person_id должно быть больше нуля");
            throw new InvalidParameter(messages);
        }
        if (invalidTicketId) {
            messages.add("Значение ticket_id должно быть больше нуля");
            throw new InvalidParameter(messages);
        }
        if (invalidPersonId) {
            messages.add("Значение person_id должно быть больше нуля");
            throw new InvalidParameter(messages);
        }


        TicketWithEventWrite foundTicket = TicketService.findTicket(ticketId);
        Person foundPerson = TicketService.findPerson(personId);

        if (foundTicket == null) invalidTicketId = true;
        if (foundPerson == null) invalidPersonId = true;
        if (invalidTicketId && invalidPersonId) {
            messages.add("Билет с данным ID не найден.");
            messages.add("Человек с данным ID не найден.");
            throw new MultipleNotFound(messages);
        }
        if (invalidTicketId) {
            messages.add("Билет с данным ID не найден.");
            throw new MultipleNotFound(messages);
        }
        if (invalidPersonId) {
            messages.add("Человек с данным ID не найден.");
            throw new MultipleNotFound(messages);
        }

        if (foundTicket.getPerson() == null) {
            messages.add("У билета нет владельца.");
            throw new IncorrectParameter(messages);
        }

        if (foundTicket.getPerson().getId() == personId) {
            if (foundTicket.getType() != null) {
                if (foundTicket.getType().equals("VIP")) {
                    throw new AlreadyVIPException();
                }
            }
            TicketWrite newTicket = new TicketWrite();
            if (foundTicket.getRefundable() != null) newTicket.setDiscount(foundTicket.getDiscount());
            newTicket.setCoordinates(foundTicket.getCoordinates());
            newTicket.setName(foundTicket.getName());
            newTicket.setDiscount(foundTicket.getDiscount());
            newTicket.setPrice(foundTicket.getPrice() * 2);
            newTicket.setType("VIP");
            newTicket.setPerson(foundTicket.getPerson());
            if (foundTicket.getRefundable() != null) newTicket.setRefundable(foundTicket.getRefundable());
            else newTicket.setRefundable(false);
            return TicketService.saveTicket(newTicket);
        } else {
            messages.add("У этого билета нет владельца с данным id.");
            throw new IncorrectParameter(messages);
        }
    }



    private int validateId(String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            List<String> messages = new ArrayList<>();
            messages.add("Некорректное значение параметра ticket_id: " + idStr);
            throw new IncorrectParameter(messages);
        }
        return id;
    }
}