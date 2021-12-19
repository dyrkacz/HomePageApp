package pl.mdyrkacz.homepageapp.event;

import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

public interface EventService {

    List<Event> findAllByUser(User user);

    void saveEvent(Event event, User user);

    void doneEvent(Long id, User user);

    Event findById(Long id);

    void deleteEvent(Event event);

    List<Event> findAllActiveEvents(User user, int pageNumber, int pageSize);

    double countActiveEvents(User user);

    List<Event> findAllOldEvents(User user, int pageNumber, int pageSize);

    double countOldEvents(User user);

    List<Event> findAllIndefiniteEvents(User user, int pageNumber, int pageSize);

    double countIndefiniteEvents(User user);

    List<Event> searchByName(User user, String search, int pageNumber, int pageSize);

    double countSearchByName(User user, String search);

    List<Event> findAllActiveEvents5Days(User user);

    List<Event> findAllActiveEvents5DaysDone(User user);

    List<Event> findAllOldEvents5Days(User user);

    List<Event> findAllIndefiniteEventsPanel(User user);

    List<Event> findEvents(User user, int pageNumber, String status, String search);

    double getNumberOfPages(User user, String status, String search);

}
