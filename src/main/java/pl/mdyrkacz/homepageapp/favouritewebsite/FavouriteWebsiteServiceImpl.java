package pl.mdyrkacz.homepageapp.favouritewebsite;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategory;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteWebsiteServiceImpl implements FavouriteWebsiteService {
    private final FavouriteWebsiteRepository favouriteWebsiteRepository;


    @Override
    public List<FavouriteWebsite> findAllByUser(User user) {
        return favouriteWebsiteRepository.findAllByUser(user);
    }

    @Override
    public void saveFavouriteWebsite(FavouriteWebsite favouriteWebsite, User user) {
        favouriteWebsite.setUser(user);
        favouriteWebsiteRepository.save(favouriteWebsite);
    }

    @Override
    public FavouriteWebsite findById(Long id) {
        return favouriteWebsiteRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFavouriteWebsite(FavouriteWebsite favouriteWebsite) {
        favouriteWebsiteRepository.delete(favouriteWebsite);
    }

    @Override
    public List<FavouriteWebsite> findAllByUserAndCategory(User user, FavouriteWebsiteCategory favouriteWebsiteCategory) {
        return favouriteWebsiteRepository.findAllByUserAndAndFavouriteWebsiteCategory(user, favouriteWebsiteCategory);
    }
}
