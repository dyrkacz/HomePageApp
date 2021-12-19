package pl.mdyrkacz.homepageapp.setting;

import lombok.Getter;
import lombok.Setter;
import pl.mdyrkacz.homepageapp.user.User;

import javax.persistence.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String settingKey;
    private String settingValue;
    @ManyToOne
    private User user;

}
