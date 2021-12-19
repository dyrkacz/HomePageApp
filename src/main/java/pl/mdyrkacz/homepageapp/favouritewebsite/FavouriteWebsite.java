package pl.mdyrkacz.homepageapp.favouritewebsite;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategory;
import pl.mdyrkacz.homepageapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class FavouriteWebsite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    @Size(max = 30, message = "The name of the website must be max 30 characters long")
    private String websiteName;
    @URL
    private String websiteUrl;
    @ManyToOne
    private User user;
    @ManyToOne
    private FavouriteWebsiteCategory favouriteWebsiteCategory;
}
