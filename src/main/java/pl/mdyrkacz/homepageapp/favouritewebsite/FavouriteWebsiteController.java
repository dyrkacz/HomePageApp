package pl.mdyrkacz.homepageapp.favouritewebsite;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategory;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategoryService;
import pl.mdyrkacz.homepageapp.game.Game;
import pl.mdyrkacz.homepageapp.game.GameService;
import pl.mdyrkacz.homepageapp.user.CurrentUser;
import pl.mdyrkacz.homepageapp.user.User;
import pl.mdyrkacz.homepageapp.user.UserService;
import pl.mdyrkacz.homepageapp.weather.Weather;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user/favouritewebsite")
@RequiredArgsConstructor
public class FavouriteWebsiteController {
    private final FavouriteWebsiteService favouriteWebsiteService;
    private final GameService gameService;
    private final FavouriteWebsiteCategoryService favouriteWebsiteCategoryService;
    private final UserService userService;

    @GetMapping("/fwmanager")
    public String fwManager(Model model) {
        model.addAttribute("favouriteWebsite", new FavouriteWebsite());
        model.addAttribute("favouriteWebsiteCategory", new FavouriteWebsiteCategory());
        return "user/favouritewebsite/fwmanager";
    }

    @PostMapping("/fwmanager")
    public String fwManager(@AuthenticationPrincipal CurrentUser customUser, Model model, @Valid FavouriteWebsite favouriteWebsite,
                            BindingResult resultWebsite, @RequestParam boolean ifFavouriteWebsite,
                            @Valid FavouriteWebsiteCategory favouriteWebsiteCategory, BindingResult resultCategory) {
        if (resultWebsite.hasErrors() || resultCategory.hasErrors()) {
            model.addAttribute("favouriteWebsite", favouriteWebsite);
            model.addAttribute("favouriteWebsiteCategory", favouriteWebsiteCategory);
            return "user/favouritewebsite/fwmanager";
        } else {
            if (ifFavouriteWebsite) {
                favouriteWebsiteService.saveFavouriteWebsite(favouriteWebsite, customUser.getUser());
            } else {
                favouriteWebsiteCategoryService.saveFavouriteWebsiteCategory(favouriteWebsiteCategory, customUser.getUser());
            }
            model.addAttribute("favouriteWebsite", new FavouriteWebsite());
            model.addAttribute("favouriteWebsiteCateory", new FavouriteWebsiteCategory());
            return "redirect:/user/favouritewebsite/fwmanager";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id) {
        if (Objects.equals(favouriteWebsiteService.findById(id).getUser().getId(), customUser.getUser().getId())) {
            favouriteWebsiteService.deleteFavouriteWebsite(favouriteWebsiteService.findById(id));
        }
        return "redirect:/user/favouritewebsite/fwmanager";
    }

    @ModelAttribute("currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser customUser) {
        return customUser.getUser();
    }

    @ModelAttribute("games")
    public List<Game> games(@AuthenticationPrincipal CurrentUser customUser) {
        return gameService.findAllByUser(customUser.getUser());
    }

    @ModelAttribute("favouriteWebsites")
    public List<FavouriteWebsite> favouriteWebsites(@AuthenticationPrincipal CurrentUser customUser) {
        return favouriteWebsiteService.findAllByUser(customUser.getUser());
    }

    @ModelAttribute("favouriteWebsiteCategories")
    public List<FavouriteWebsiteCategory> favouriteWebsiteCategories(@AuthenticationPrincipal CurrentUser customUser) {
        return favouriteWebsiteCategoryService.findAllByUser(customUser.getUser());
    }

    @ModelAttribute("weather")
    public Weather weather(@AuthenticationPrincipal CurrentUser customUser) {
        return userService.getWeather(customUser.getUser());
    }
}
