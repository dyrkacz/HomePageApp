package pl.mdyrkacz.homepageapp.favouritewebsitecategory;

import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

public interface FavouriteWebsiteCategoryService {

    List<FavouriteWebsiteCategory> findAllByUser(User user);

    void saveFavouriteWebsiteCategory(FavouriteWebsiteCategory favouriteWebsiteCategory, User user);

    void deleteFavouriteWebsiteCategory(FavouriteWebsiteCategory favouriteWebsiteCategory);

    FavouriteWebsiteCategory findById(Long id);
}
