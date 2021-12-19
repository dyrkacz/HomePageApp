package pl.mdyrkacz.homepageapp.favouritewebsitecategory;

import lombok.Getter;
import lombok.Setter;
import pl.mdyrkacz.homepageapp.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FavouriteWebsiteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @ManyToOne
    private User user;
}
