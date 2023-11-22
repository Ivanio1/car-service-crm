package carservicecrm.controllers;

import carservicecrm.models.*;
import carservicecrm.services.OfferService;
import carservicecrm.services.QuestionService;
import carservicecrm.services.ReviewService;
import carservicecrm.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;
    private final OfferService offerService;
    private final QuestionService questionService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("offers", offerService.listOffers(""));
        return "profile";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с почтой: " + user.getEmail() + " уже существует.");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));

        //Добавить purchases

        return "user-info";
    }

    @PostMapping("/delete/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user/reviews")
    public String adminusers(Model model, Principal principal) {
        model.addAttribute("reviews", reviewService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "reviews";
    }

    @PostMapping("/user/add/review")
    public String addEmployee(@RequestParam("email") String email, @RequestParam String reviewText,@RequestParam Integer rating,@RequestParam String offer) {
        Review review = new Review();
        review.setUser(userService.getUserByEmail(email));
        review.setReviewText(reviewText);
        review.setRating(rating);
        review.setOffer(offerService.getOfferByName(offer));
        if (!reviewService.saveReview(review)) {
            return "redirect:/error";
        }
        return "redirect:/user/reviews";
    }

    @PostMapping("/user/add/question")
    public String saveQuestion(@RequestParam("email") String email, @RequestParam String questionText) {
        Question question = new Question();
        question.setUser(userService.getUserByEmail(email));
        question.setQuestionText(questionText);
        questionService.save(question);
        return "redirect:/profile";
    }


}