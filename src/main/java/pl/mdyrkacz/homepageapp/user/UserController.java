package pl.mdyrkacz.homepageapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mdyrkacz.homepageapp.event.EventService;
import pl.mdyrkacz.homepageapp.favouritewebsite.FavouriteWebsite;
import pl.mdyrkacz.homepageapp.favouritewebsite.FavouriteWebsiteService;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategory;
import pl.mdyrkacz.homepageapp.favouritewebsitecategory.FavouriteWebsiteCategoryService;
import pl.mdyrkacz.homepageapp.game.Game;
import pl.mdyrkacz.homepageapp.game.GameService;
import pl.mdyrkacz.homepageapp.holiday.HolidayService;
import pl.mdyrkacz.homepageapp.news.NewsRssService;
import pl.mdyrkacz.homepageapp.setting.SettingService;
import pl.mdyrkacz.homepageapp.weather.Weather;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final GameService gameService;
    private final FavouriteWebsiteService favouriteWebsiteService;
    private final FavouriteWebsiteCategoryService favouriteWebsiteCategoryService;
    private final HolidayService holidayService;
    private final EventService eventService;
    private final NewsRssService newsRssService;
    private final SettingService settingService;

    @GetMapping("")
    public String home(@AuthenticationPrincipal CurrentUser customUser, Model model, @RequestParam(defaultValue = "Najnowsze") String newsCategory) {
        User entityUser = customUser.getUser();
        model.addAttribute("holidaysPanel", holidayService.findAll30Days(entityUser));
        model.addAttribute("activeEventsPanel", eventService.findAllActiveEvents5Days(entityUser));
        model.addAttribute("indefinitePanel", eventService.findAllIndefiniteEventsPanel(entityUser));
        model.addAttribute("passedEvents", eventService.findAllOldEvents5Days(entityUser));
        model.addAttribute("activeDoneEventsPanel", eventService.findAllActiveEvents5DaysDone(entityUser));
        model.addAttribute("newsCategories", userService.newsRssListByUser(entityUser));
        model.addAttribute("news", userService.getActiveNews(newsCategory, entityUser));
        model.addAttribute("active", userService.getActiveNewsCategoryName(newsCategory, entityUser));
        model.addAttribute("tweets", customUser.getUser().tweetPageList);
        return "user/homepage";

    }

    @GetMapping("/tweets")
    public String getTweeets(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (settingService.findTwitterIdByUser(customUser.getUser()) != null & settingService.findTwitterTokenByUser(customUser.getUser()) != null) {
            userService.getTwitterPage(settingService.findTwitterIdByUser(customUser.getUser()), settingService.findTwitterTokenByUser(customUser.getUser()), customUser.getUser());
        }
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("user", userService.getUserWithLists(customUser.getUser().getId()));
        return "user/editprofile";
    }

    @PostMapping("/edit")
    public String editPost(@AuthenticationPrincipal CurrentUser customUser, Model model, @Valid User user, BindingResult result, @RequestParam boolean changePassword) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("changePassword", changePassword);
            return "user/editprofile";
        } else {
            if (userService.findByUserName(user.getUsername()) != null && user.getId() != userService.findByUserName(user.getUsername()).getId()) {
                model.addAttribute("user", user);
                model.addAttribute("errorUsername", true);
                return "user/editprofile";
            } else {
                userService.editUser(user, changePassword);
                customUser.getUser().setUsername(user.getUsername());
                customUser.getUser().setEmail(user.getEmail());
                return "redirect:/user";
            }
        }
    }

    @GetMapping("/newsmanager")
    public String newsManager(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("user", userService.getUserWithLists(customUser.getUser().getId()));
        model.addAttribute("newsRssCategoryList", newsRssService.findAll());
        return "user/newsmanager";
    }

    @PostMapping("/newsmanager")
    public String newsManagerPost(User user) {
        userService.editUser(user, false);
        return "redirect:/user";
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