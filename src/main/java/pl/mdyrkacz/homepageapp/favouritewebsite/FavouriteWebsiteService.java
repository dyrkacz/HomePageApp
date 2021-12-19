package pl.mdyrkacz.homepageapp.favouritewebsite;

import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategory;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

public interface FavouriteWebsiteService {

    List<FavouriteWebsite> findAllByUser(User user);

    void saveFavouriteWebsite(FavouriteWebsite favouriteWebsite, User user);

    FavouriteWebsite findById(Long id);

    void deleteFavouriteWebsite(FavouriteWebsite favouriteWebsite);

    List<FavouriteWebsite> findAllByUserAndCategory(User user, FavouriteWebsiteCategory favouriteWebsiteCategory);
}
