package pl.mdyrkacz.homepageapp.game;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mdyrkacz.homepageapp.favouritewebsite.FavouriteWebsite;
import pl.mdyrkacz.homepageapp.favouritewebsite.FavouriteWebsiteService;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategory;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategoryService;
import pl.mdyrkacz.homepageapp.user.CurrentUser;
import pl.mdyrkacz.homepageapp.user.User;
import pl.mdyrkacz.homepageapp.user.UserService;
import pl.mdyrkacz.homepageapp.weather.Weather;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final FavouriteWebsiteService favouriteWebsiteService;
    private final FavouriteWebsiteCategoryService favouriteWebsiteCategoryService;
    private final UserService userService;

    @GetMapping("/gamesmanager")
    public String gamesManager(Model model) {
        model.addAttribute("game", new Game());
        return "user/game/gamesmanager";
    }

    @PostMapping("/gamesmanager")
    public String gamesManager(@AuthenticationPrincipal CurrentUser customUser, Model model, @Valid Game game, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("game", game);
            return "user/game/gamesmanager";
        } else {
            gameService.saveGame(game, customUser.getUser());
            model.addAttribute("game", new Game());
            return "redirect:/user/game/gamesmanager";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id) {
        if (Objects.equals(gameService.findById(id).getUser().getId(), customUser.getUser().getId())) {
            gameService.deleteGame(gameService.findById(id));
        }
        return "redirect:/user/game/gamesmanager";
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
