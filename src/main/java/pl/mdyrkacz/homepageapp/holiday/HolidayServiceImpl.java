package pl.mdyrkacz.homepageapp.holiday;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.mdyrkacz.homepageapp.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {
    private final HolidayRepository holidayRepository;
    private final int pageSize = 10;

    @Override
    public Holiday findByHolidayName(String holidayName) {
        return holidayRepository.findByHolidayName(holidayName);
    }

    @Override
    public void saveHoliday(Holiday holiday, User user) {
        holiday.setUser(user);
        holidayRepository.save(holiday);
    }

    @Override
    public List<Holiday> findHolidaysByUser(User user) {
        return holidayRepository.findAllByUser(user);
    }

    @Override
    public void deleteHoliday(Holiday holiday) {
        holidayRepository.delete(holiday);
    }

    @Override
    public Holiday findById(Long id) {
        return holidayRepository.findById(id).orElse(null);
    }

    @Override
    public List<Holiday> findAllActiveHolidays(User user, int pageNumber, int pageSize) {
        int pageTemp = pageNumber - 1;
        Pageable pageableHoliday = PageRequest.of(pageTemp, pageSize);
        return holidayRepository.findActiveHolidays(user, LocalDate.now(), pageableHoliday);
    }

    @Override
    public double countActiveHolidays(User user) {
        return holidayRepository.countActiveHolidays(user, LocalDate.now());
    }

    @Override
    public List<Holiday> findAllOldHolidays(User user, int pageNumber, int pageSize) {
        int pageTemp = pageNumber - 1;
        Pageable pageableHoliday = PageRequest.of(pageTemp, pageSize);
        return holidayRepository.findOldHolidays(user, LocalDate.now(), pageableHoliday);
    }

    @Override
    public double countOldHolidays(User user) {
        return holidayRepository.countOldHolidays(user, LocalDate.now());
    }

    @Override
    public List<Holiday> searchByName(User user, String search, int pageNumber, int pageSize) {
        int pageTemp = pageNumber - 1;
        Pageable pageableHoliday = PageRequest.of(pageTemp, pageSize);
        return holidayRepository.searchByName(user, "%" + search + "%", pageableHoliday);
    }

    @Override
    public double countSearchByName(User user, String search) {
        return holidayRepository.countSearchByName(user, "%" + search + "%");
    }

    @Override
    @Scheduled(cron = "0 0 0 1 1 ?", zone = "Europe/Paris")
    public void renewAnnualHolidays() {
        holidayRepository.findAllAnnual(LocalDate.now()).forEach(it -> {
            it.setHolidayDate(it.getHolidayDate().plusYears(1));
            holidayRepository.save(it);
        });
    }

    @Override
    public List<Holiday> findAll30Days(User user) {
        return holidayRepository.findAll30Days(user, LocalDate.now(), LocalDate.now().plusDays(30));
    }

    @Override
    public List<Holiday> findHolidays(User user, int pageNumber, String status, String search) {
        int pageTemp = pageNumber - 1;
        Pageable pageableHoliday = PageRequest.of(pageTemp, pageSize);
        if (status.equals("active") && search.equals("noSearch")) {
            return holidayRepository.findActiveHolidays(user, LocalDate.now(), pageableHoliday);
        } else if (status.equals("old")) {
            return holidayRepository.findOldHolidays(user, LocalDate.now(), pageableHoliday);
        } else if (!search.equals("noSearch")) {
            return holidayRepository.searchByName(user, "%" + search + "%", pageableHoliday);
        } else {
            return null;
        }
    }

    @Override
    public double getNumberOfPages(User user, String status, String search) {
        if (status.equals("active") && search.equals("noSearch")) {
            return Math.ceil(holidayRepository.countActiveHolidays(user, LocalDate.now()) / pageSize);
        } else if (status.equals("old")) {
            return Math.ceil(holidayRepository.countOldHolidays(user, LocalDate.now()) / pageSize);
        } else if (!search.equals("noSearch")) {
            return Math.ceil(holidayRepository.countSearchByName(user, "%" + search + "%") / pageSize);
        } else {
            return 0;
        }
    }

}
