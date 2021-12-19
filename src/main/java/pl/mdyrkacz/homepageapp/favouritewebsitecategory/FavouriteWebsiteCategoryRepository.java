package pl.mdyrkacz.homepageapp.favouritewebsitecategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mdyrkacz.homepageapp.favouritewebsite.FavouriteWebsite;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

@Repository
public interface FavouriteWebsiteCategoryRepository extends JpaRepository<FavouriteWebsiteCategory, Long> {

    List<FavouriteWebsiteCategory> findAllByUser(User user);

}
