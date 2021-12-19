package pl.mdyrkacz.homepageapp.favouritewebsitecategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteWebsiteCategoryServiceImpl implements FavouriteWebsiteCategoryService {
    private final FavouriteWebsiteCategoryRepository favouriteWebsiteCategoryRepository;

    @Override
    public List<FavouriteWebsiteCategory> findAllByUser(User user) {
        return favouriteWebsiteCategoryRepository.findAllByUser(user);
    }

    @Override
    public void saveFavouriteWebsiteCategory(FavouriteWebsiteCategory favouriteWebsiteCategory, User user) {
        favouriteWebsiteCategory.setUser(user);
        favouriteWebsiteCategoryRepository.save(favouriteWebsiteCategory);
    }

    @Override
    public FavouriteWebsiteCategory findById(Long id) {
        return favouriteWebsiteCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFavouriteWebsiteCategory(FavouriteWebsiteCategory favouriteWebsiteCategory) {
        favouriteWebsiteCategoryRepository.delete(favouriteWebsiteCategory);


    }
}
