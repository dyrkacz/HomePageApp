package pl.mdyrkacz.homepageapp.holiday;

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
@RequestMapping("/user/holiday")
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;
    private final GameService gameService;
    private final FavouriteWebsiteService favouriteWebsiteService;
    private final FavouriteWebsiteCategoryService favouriteWebsiteCategoryService;
    private final UserService userService;

    @GetMapping("/add")
    public String addHoliday(Model model) {
        model.addAttribute("holiday", new Holiday());
        return "user/holiday/addholiday";

    }

    @PostMapping(value = {"/add"})
    public String addHoliday(@Valid Holiday holiday, BindingResult result, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("holiday", holiday);
            return "user/holiday/addholiday";
        } else {
            holidayService.saveHoliday(holiday, customUser.getUser());
            return "redirect:/user/holiday/list";
        }
    }

    @GetMapping("/edit/{id}")
    public String editHoliday(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id, Model model) {
        if (Objects.equals(holidayService.findById(id).getUser().getId(), customUser.getUser().getId())) {
            model.addAttribute("holiday", holidayService.findById(id));
            return "user/holiday/editholiday";
        } else {
            return "redirect:/user/holiday/list";
        }

    }

    @PostMapping(value = {"/edit/{id}"})
    public String editHoliday(@Valid Holiday holiday, BindingResult result, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("holiday", holiday);
            return "user/holiday/editholiday";
        } else {
            holidayService.saveHoliday(holiday, customUser.getUser());
            return "redirect:/user/holiday/list";
        }
    }

    @GetMapping("/list")
    public String holidayList(@AuthenticationPrincipal CurrentUser customUser, Model model,
                              @RequestParam(defaultValue = "active") String status,
                              @RequestParam(defaultValue = "noSearch") String search,
                              @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("holidays", holidayService.findHolidays(customUser.getUser(), page, status, search));
        model.addAttribute("pages", holidayService.getNumberOfPages(customUser.getUser(), status, search));
        return "user/holiday/holidaylist";

    }

    @GetMapping("/delete/{id}")
    public String deleteHoliday(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id) {
        if (Objects.equals(holidayService.findById(id).getUser().getId(), customUser.getUser().getId())) {
            holidayService.deleteHoliday(holidayService.findById(id));
        }
        return "redirect:/user/holiday/list";
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
