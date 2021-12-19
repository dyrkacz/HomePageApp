package pl.mdyrkacz.homepageapp.holiday;

import pl.mdyrkacz.homepageapp.user.User;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {

        Holiday findByHolidayName(String holidayName);

        void saveHoliday(Holiday holiday, User user);

        void deleteHoliday(Holiday holiday);

        List<Holiday> findHolidaysByUser(User user);

        Holiday findById(Long id);

        List<Holiday> findAllActiveHolidays(User user, int pageNumber, int pageSize);

        double countActiveHolidays(User user);

        List<Holiday> findAllOldHolidays(User user, int pageNumber, int pageSize);

        double countOldHolidays(User user);

        List<Holiday> searchByName(User user, String search, int pageNumber, int pageSize);

        double countSearchByName(User user, String search);

        void renewAnnualHolidays();

        List<Holiday> findAll30Days (User user);

        List<Holiday> findHolidays(User user, int pageNumber, String status, String search);

        double getNumberOfPages(User user, String status, String search);
}
