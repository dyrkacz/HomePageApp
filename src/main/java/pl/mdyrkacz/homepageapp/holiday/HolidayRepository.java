package pl.mdyrkacz.homepageapp.holiday;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mdyrkacz.homepageapp.user.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    Holiday findByHolidayName(String holidayName);

    List<Holiday> findAllByUser(User user);

    @Query("select h from Holiday h where h.user=?1 and h.holidayDate >= ?2 or h.user=?1 and h.annual = true order by h.holidayDate ")
    List<Holiday> findActiveHolidays(User user, LocalDate localDate, Pageable pageable);

    @Query("select count(h) from Holiday h where h.user=?1 and h.holidayDate >= ?2 or h.user=?1 and h.annual = true")
    double countActiveHolidays(User user, LocalDate localDate);

    @Query("select h from Holiday h where h.user=?1 and h.holidayDate < ?2 and h.annual=false order by h.holidayDate desc")
    List<Holiday> findOldHolidays(User user, LocalDate localDate, Pageable pageable);

    @Query("select count(h) from Holiday h where h.user=?1 and h.holidayDate < ?2 and h.annual=false")
    double countOldHolidays(User user, LocalDate localDate);

    @Query("select h from Holiday h where h.user=?1 and h.holidayName like ?2 order by h.holidayDate")
    List<Holiday> searchByName(User user, String search, Pageable pageable);

    @Query("select count(h) from Holiday h where h.user=?1 and h.holidayName like ?2 order by h.holidayDate")
    double countSearchByName(User user, String search);

    @Query("select h from Holiday h where  h.holidayDate < ?1 and h.annual = true")
    List<Holiday> findAllAnnual(LocalDate date);

    @Query("select h from Holiday h where h.user=?1 and h.holidayDate >= ?2 and h.holidayDate <=?3 order by h.holidayDate")
    List<Holiday> findAll30Days(User user, LocalDate now, LocalDate fiveDays);
}
