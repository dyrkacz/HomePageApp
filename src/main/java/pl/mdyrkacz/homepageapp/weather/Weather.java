package pl.mdyrkacz.homepageapp.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private String cityName;
    private double temp;
    private String weatherDescription;

}
