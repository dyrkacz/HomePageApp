package pl.mdyrkacz.homepageapp.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.mdyrkacz.homepageapp.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final int pageSize = 10;

    @Override
    public List<Event> findAllByUser(User user) {
        return eventRepository.findAllByUser(user);
    }

    @Override
    public void saveEvent(Event event, User user) {
        event.setUser(user);
        eventRepository.save(event);
    }

    @Override
    public void doneEvent(Long id, User user) {
        Event event = findById(id);
        event.setDone(true);
        saveEvent(event, user);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public List<Event> findAllActiveEvents(User user, int pageNumber, int pageSize) {
        int pageTemp = pageNumber - 1;
        Pageable pageableEvent = PageRequest.of(pageTemp, pageSize);
        return eventRepository.findAllActiveEvents(user, LocalDate.now(), LocalTime.now(), pageableEvent);
    }

    @Override
    public List<Event> findAllOldEvents(User user, int pageNumber, int pageSize) {
        int pageTemp = pageNumber - 1;
        Pageable pageableEvent = PageRequest.of(pageTemp, pageSize);
        return eventRepository.findAllOldEvents(user, LocalDate.now(), LocalTime.now(), pageableEvent);
    }

    @Override
    public List<Event> findAllIndefiniteEvents(User user, int pageNumber, int pageSize) {
        int pageTemp = pageNumber - 1;
        Pageable pageableEvent = PageRequest.of(pageTemp, pageSize);
        return eventRepository.findAllIndefiniteEvents(user, pageableEvent);
    }

    @Override
    public List<Event> searchByName(User user, String search, int pageNumber, int pageSize) {
        int pageTemp = pageNumber - 1;
        Pageable pageableEvent = PageRequest.of(pageTemp, pageSize);
        return eventRepository.searchByName(user, "%" + search + "%", pageableEvent);
    }

    @Override
    public double countActiveEvents(User user) {
        return eventRepository.countAllActiveEvents(user, LocalDate.now(), LocalTime.now());
    }

    @Override
    public double countOldEvents(User user) {
        return eventRepository.countAllOldEvents(user, LocalDate.now(), LocalTime.now());
    }

    @Override
    public double countIndefiniteEvents(User user) {
        return eventRepository.countAllIndefiniteEvents(user);
    }

    @Override
    public double countSearchByName(User user, String search) {
        return eventRepository.countSearchByName(user, "%" + search + "%");
    }

    @Override
    public List<Event> findAllActiveEvents5Days(User user) {
        return eventRepository.findAllActiveEvents5Days(user, LocalDate.now(), LocalTime.now(), LocalDate.now().plusDays(5));
    }

    @Override
    public List<Event> findAllIndefiniteEventsPanel(User user) {
        return eventRepository.findAllIndefinitePanel(user);
    }

    @Override
    public List<Event> findAllOldEvents5Days(User user) {
        return eventRepository.findAllOldEvents5Days(user, LocalDate.now(), LocalTime.now(), LocalDate.now().minusDays(5));
    }

    @Override
    public List<Event> findAllActiveEvents5DaysDone(User user) {
        return eventRepository.findAllActiveEvents5DaysDone(user, LocalDate.now(), LocalTime.now(), LocalDate.now().plusDays(5));
    }

    @Override
    public List<Event> findEvents(User user, int pageNumber, String status, String search) {
        int pageTemp = pageNumber - 1;
        Pageable pageableEvent = PageRequest.of(pageTemp, pageSize);
        if (status.equals("active") && search.equals("noSearch")) {
            return eventRepository.findAllActiveEvents(user, LocalDate.now(), LocalTime.now(), pageableEvent);
        } else if (status.equals("indefinite")) {
            return eventRepository.findAllIndefiniteEvents(user, pageableEvent);
        } else if (!search.equals("noSearch")) {
            return eventRepository.searchByName(user, "%" + search + "%", pageableEvent);
        } else if (status.equals("old")) {
            return eventRepository.findAllOldEvents(user, LocalDate.now(), LocalTime.now(), pageableEvent);
        } else {
            return null;
        }
    }

    @Override
    public double getNumberOfPages(User user, String status, String search) {
        if (status.equals("active") && search.equals("noSearch")) {
            return Math.ceil(eventRepository.countAllActiveEvents(user, LocalDate.now(), LocalTime.now()) / pageSize);
        } else if (status.equals("indefinite")) {
            return Math.ceil(eventRepository.countAllIndefiniteEvents(user) / pageSize);
        } else if (!search.equals("noSearch")) {
            return Math.ceil(eventRepository.countSearchByName(user, "%" + search + "%") / pageSize);
        } else if (status.equals("old")) {
            return Math.ceil(eventRepository.countAllOldEvents(user, LocalDate.now(), LocalTime.now()) / pageSize);
        } else {
            return 0;
        }
    }
}
