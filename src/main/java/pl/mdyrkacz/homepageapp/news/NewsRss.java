package pl.mdyrkacz.homepageapp.news;


import lombok.Getter;
import lombok.Setter;
import pl.mdyrkacz.homepageapp.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class NewsRss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String newsCategoryName;
    private String url;


}
