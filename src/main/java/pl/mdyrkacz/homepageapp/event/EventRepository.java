package pl.mdyrkacz.homepageapp.event;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mdyrkacz.homepageapp.user.User;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByUser(User user);

    @Query("select e from Event e where e.user=?1 and e.eventExecutionDate > ?2 or " +
            "e.user=?1 and e.eventExecutionDate= ?2 and e.eventExecutionTime > ?3 or " +
            "e.user=?1 and e.eventExecutionDate >= ?2 and e.eventExecutionTime is null order by e.eventExecutionDate , e.eventExecutionTime")
    List<Event> findAllActiveEvents(User user, LocalDate date, LocalTime time, Pageable pageable);

    @Query("select count(e) from Event e where e.user=?1 and e.eventExecutionDate > ?2 or " +
            "e.user=?1 and e.eventExecutionDate= ?2 and e.eventExecutionTime > ?3 or " +
            "e.user=?1 and e.eventExecutionDate >= ?2 and e.eventExecutionTime is null")
    double countAllActiveEvents(User user, LocalDate date, LocalTime time);

    @Query("select e from Event e where e.user=?1 and e.eventExecutionDate < ?2 or " +
            "e.user=?1 and e.eventExecutionDate = ?2 and e.eventExecutionTime < ?3 order by e.eventExecutionDate , e.eventExecutionTime desc")
    List<Event> findAllOldEvents(User user, LocalDate date, LocalTime time, Pageable pageable);

    @Query("select count(e) from Event e where e.user=?1 and e.eventExecutionDate < ?2 or " +
            "e.user=?1 and e.eventExecutionDate = ?2 and e.eventExecutionTime < ?3")
    double countAllOldEvents(User user, LocalDate date, LocalTime time);


    @Query("select e from Event e where e.user=?1 and e.eventExecutionDate is null order by e.id desc")
    List<Event> findAllIndefiniteEvents(User user, Pageable pageable);

    @Query("select count(e) from Event e where e.user=?1 and e.eventExecutionDate is null")
    double countAllIndefiniteEvents(User user);

    @Query("select e from Event e where e.user=?1 and e.eventName like ?2 order by e.eventExecutionDate , e.eventExecutionTime")
    List<Event> searchByName(User user, String search, Pageable pageable);

    @Query("select count(e) from Event e where e.user=?1 and e.eventName like ?2")
    double countSearchByName(User user, String search);

    @Query("select e from Event e where e.user=?1 and e.eventExecutionDate > ?2 and e.eventExecutionDate <?4 and e.done = false or " +
            "e.user=?1 and e.eventExecutionDate= ?2 and e.eventExecutionDate <?4 and e.eventExecutionTime > ?3 and e.done = false or " +
            "e.user=?1 and e.eventExecutionDate >= ?2 and e.eventExecutionDate <?4 and e.eventExecutionTime is null and e.done = false order by e.eventExecutionDate , e.eventExecutionTime")
    List<Event> findAllActiveEvents5Days(User user, LocalDate nowDate, LocalTime nowTime, LocalDate fiveDays);

    @Query("select e from Event e where e.user=?1 and e.eventExecutionDate is null and e.done = false order by e.id desc")
    List<Event> findAllIndefinitePanel(User user);

    @Query("select e from Event e where e.user=?1 and e.eventExecutionDate < ?2  and e.eventExecutionDate > ?4 or " +
            "e.user=?1 and e.eventExecutionDate = ?2 and e.eventExecutionTime < ?3 and e.eventExecutionDate > ?4 order by e.eventExecutionDate , e.eventExecutionTime desc")
    List<Event> findAllOldEvents5Days(User user, LocalDate nowDate, LocalTime time, LocalDate fiveDays);

    @Query("select e from Event e where e.user=?1 and e.eventExecutionDate > ?2 and e.eventExecutionDate <?4 and e.done = true or " +
            "e.user=?1 and e.eventExecutionDate= ?2 and e.eventExecutionDate <?4 and e.eventExecutionTime > ?3 and e.done = true or " +
            "e.user=?1 and e.eventExecutionDate >= ?2 and e.eventExecutionDate <?4 and e.eventExecutionTime is null and e.done = true order by e.eventExecutionDate , e.eventExecutionTime")
    List<Event> findAllActiveEvents5DaysDone(User user, LocalDate nowDate, LocalTime nowTime, LocalDate fiveDays);
}
