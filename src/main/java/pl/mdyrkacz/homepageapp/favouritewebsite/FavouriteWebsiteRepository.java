package pl.mdyrkacz.homepageapp.favouritewebsite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategory;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

@Repository
public interface FavouriteWebsiteRepository extends JpaRepository<FavouriteWebsite,Long> {

    List<FavouriteWebsite> findAllByUser(User user);

    List<FavouriteWebsite> findAllByUserAndAndFavouriteWebsiteCategory (User user, FavouriteWebsiteCategory favouriteWebsiteCategory);
}
