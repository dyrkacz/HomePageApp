package pl.mdyrkacz.homepageapp.news;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mdyrkacz.homepageapp.favouritewebsite.FavouriteWebsite;
import pl.mdyrkacz.homepageapp.favouritewebsite.FavouriteWebsiteService;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategory;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategoryService;
import pl.mdyrkacz.homepageapp.game.Game;
import pl.mdyrkacz.homepageapp.game.GameService;
import pl.mdyrkacz.homepageapp.user.CurrentUser;
import pl.mdyrkacz.homepageapp.user.User;
import pl.mdyrkacz.homepageapp.user.UserService;
import pl.mdyrkacz.homepageapp.weather.Weather;

import java.util.List;

@Controller
@RequestMapping("/user/news")
@RequiredArgsConstructor
public class NewsRssController {
    private final GameService gameService;
    private final FavouriteWebsiteService favouriteWebsiteService;
    private final FavouriteWebsiteCategoryService favouriteWebsiteCategoryService;
    private final UserService userService;

    @GetMapping("/newsmanager")
    public String newsManager(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("user", customUser.getUser());
        return "user/newsmanager";
    }

    @PostMapping("/newsmanager")
    public String newsManagerPost(@AuthenticationPrincipal CurrentUser customUser, User user) {
        return "user/newsmanager";
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
