package pl.mdyrkacz.homepageapp.event;


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
@RequestMapping("/user/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final GameService gameService;
    private final FavouriteWebsiteService favouriteWebsiteService;
    private final FavouriteWebsiteCategoryService favouriteWebsiteCategoryService;
    private final UserService userService;


    @GetMapping("/add")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        return "user/event/addevent";

    }

    @PostMapping(value = {"/add"})
    public String addEvent(@Valid Event event, BindingResult result, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "user/event/addevent";
        } else {
            eventService.saveEvent(event, customUser.getUser());
            return "redirect:/user/event/list";
        }
    }

    @GetMapping("/list")
    public String holidayList(@AuthenticationPrincipal CurrentUser customUser, Model model,
                              @RequestParam(defaultValue = "active") String status,
                              @RequestParam(defaultValue = "noSearch") String search,
                              @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("events", eventService.findEvents(customUser.getUser(), page, status, search));
        model.addAttribute("pages", eventService.getNumberOfPages(customUser.getUser(), status, search));
        return "user/event/eventlist";

    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id) {
        if (Objects.equals(eventService.findById(id).getUser().getId(), customUser.getUser().getId())) {
            eventService.deleteEvent(eventService.findById(id));
        }
        return "redirect:/user/event/list";

    }

    @GetMapping("/edit/{id}")
    public String editEvent(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id, Model model) {
        if (Objects.equals(eventService.findById(id).getUser().getId(), customUser.getUser().getId())) {
            model.addAttribute("event", eventService.findById(id));
            return "user/event/editevent";
        } else {
            return "redirect:/user/event/list";
        }
    }

    @PostMapping(value = {"/edit/{id}"})
    public String editEvent(@Valid Event event, BindingResult result, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "user/event/editevent";
        } else {
            eventService.saveEvent(event, customUser.getUser());
            return "redirect:/user/event/list";
        }
    }

    @GetMapping("/done/{id}")
    public String editDone(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id) {
        if (Objects.equals(eventService.findById(id).getUser().getId(), customUser.getUser().getId())) {
            eventService.doneEvent(id, customUser.getUser());
        }
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
