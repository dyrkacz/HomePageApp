package pl.mdyrkacz.homepageapp.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeather, Long> {

    @Query("select c from CityWeather c order by c.cityName")
    List<CityWeather> findAllOrderedByName();
}
