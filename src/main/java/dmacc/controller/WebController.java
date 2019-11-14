package dmacc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Meal;
import dmacc.beans.User;
import dmacc.repository.MealPlannerRepository;
import dmacc.repository.MealPlannerRepositoryUser;

@Controller
public class WebController {

	@Autowired
	MealPlannerRepository repo;
	
	@Autowired
	MealPlannerRepositoryUser userRepo;
	
	@GetMapping("/viewAllMeals")
	public String viewAllMeals(Model model) {
		model.addAttribute("mealPlanner", repo.findAll());
		return "results";
	}
	
	@GetMapping("/inputMeal")
	public String addNewMeal(Model model) {
	    Meal m = new Meal();
	    model.addAttribute("newMeal", m);
	    return "inputMeal";
	}
	
	@PostMapping("/inputMeal")
	public String addNewMeal(@ModelAttribute ("mealName") Meal m, Model model) {
		repo.save(m);
		model.addAttribute("mealName", repo.findAll());
		return "results";
	}
	
	@GetMapping("/inputUser")
	public String addNewUser(Model model) {
		User u = new User();
		Meal m = new Meal();
		model.addAttribute("newUser", u);
		model.addAttribute("newMeal", m);
		return "inputUser";
	}
	
	@PostMapping("/inputUser")
	public String addNewUser(@ModelAttribute ("newUser") User u, @ModelAttribute ("mealName") Meal m, Model model) {
		userRepo.save(u);
		repo.save(m);
		model.addAttribute("newUser", repo.findAll());
		model.addAttribute("mealName", repo.findAll());
		return "results";
	}
}
