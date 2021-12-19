package pl.mdyrkacz.homepageapp.favouritewebsitecategory;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mdyrkacz.homepageapp.user.CurrentUser;

import java.util.Objects;

@Controller
@RequestMapping("/user/favouritewebsitecategory")
@RequiredArgsConstructor
public class FavouriteWebsiteCategoryController {
    private final FavouriteWebsiteCategoryService favouriteWebsiteCategoryService;

    @GetMapping("/delete/{id}")
    public String deleteGame(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id){
        if (Objects.equals(favouriteWebsiteCategoryService.findById(id).getUser().getId(), customUser.getUser().getId())) {
            favouriteWebsiteCategoryService.deleteFavouriteWebsiteCategory(favouriteWebsiteCategoryService.findById(id));
        }
        return "redirect:/user/favouritewebsite/fwmanager";
    }
}
