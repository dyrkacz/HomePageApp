package pl.mdyrkacz.homepageapp.setting;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import pl.mdyrkacz.homepageapp.user.UserServiceImpl;
import pl.mdyrkacz.homepageapp.weather.CityWeatherRepository;
import pl.mdyrkacz.homepageapp.weather.Weather;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user/setting")
@RequiredArgsConstructor
public class SettingController {
    private final SettingService settingService;
    private final CityWeatherRepository cityWeatherRepository;
    private final UserServiceImpl userService;
    private final GameService gameService;
    private final FavouriteWebsiteService favouriteWebsiteService;
    private final FavouriteWebsiteCategoryService favouriteWebsiteCategoryService;

    @GetMapping("/settings")
    public String settings(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("settingCurrentLocation", settingService.sendSettingCurrentLocation(customUser.getUser()));
        model.addAttribute("settingTwitterId", settingService.sendSettingTwitterId(customUser.getUser()));
        model.addAttribute("settingTwitterToken", new Setting());
        model.addAttribute("cities", cityWeatherRepository.findAllOrderedByName());
        return "user/setting/settings";
    }

    @PostMapping("/settings")
    public String settings(@AuthenticationPrincipal CurrentUser customUser, Model model, @Valid Setting setting, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("settingCurrentLocation", settingService.sendSettingCurrentLocation(customUser.getUser()));
            model.addAttribute("settingTwitterId", settingService.sendSettingTwitterId(customUser.getUser()));
            model.addAttribute("settingTwitterToken", new Setting());
            return "user/setting/settings";
        } else {
            if (setting.getSettingKey().equals("twitterToken")) {
                settingService.saveSettingEncoded(setting, customUser.getUser());
            } else {
                settingService.saveSetting(setting, customUser.getUser());
            }
            model.addAttribute("settingCurrentLocation", settingService.sendSettingCurrentLocation(customUser.getUser()));
            model.addAttribute("settingTwitterId", settingService.sendSettingTwitterId(customUser.getUser()));
            model.addAttribute("settingTwitterToken", new Setting());
            return "redirect:/user/setting/settings";
        }
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
