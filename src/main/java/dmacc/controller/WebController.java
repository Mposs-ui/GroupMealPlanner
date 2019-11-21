package dmacc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Ingredients;
import dmacc.beans.Meal;
import dmacc.beans.User;
import dmacc.repository.MealPlannerRepository;
import dmacc.repository.MealPlannerRepositoryUser;
import dmacc.repository.MealPlannerRepositoryIngredients;

@Controller
public class WebController {

	@Autowired
	MealPlannerRepository repo;
	
	@Autowired
	MealPlannerRepositoryUser userRepo;
	
	@Autowired
	MealPlannerRepositoryIngredients ingredientsRepo;
	
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
	
	//for viewing ingredients
	@GetMapping("/viewIngredients")
	public String viewAllIngredients(Model model) {
		model.addAttribute("ingredients", ingredientsRepo.findAll());
		return "viewIngredients";
	}
	
	//for adding ingredients
	@GetMapping("/inputIngredients")
	public String addNewIngredients(Model model) {
		Ingredients i = new Ingredients();
		model.addAttribute("newIngredients", i);
		return "inputIngredients";
	}
	//also for adding ingredients
	@PostMapping("/inputIngredients")
	public String addNewIngredients(@ModelAttribute Ingredients i, Model model) {
		ingredientsRepo.save(i);
		model.addAttribute("ingredients", ingredientsRepo.findAll());
		return "viewIngredients";
	}
	
	//for deleting ingredients
	@GetMapping("/delete/{id}")
	public String deleteIngredients(@PathVariable("id") long id, Model model) {
		Ingredients i = ingredientsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
		ingredientsRepo.delete(i);
		model.addAttribute("ingredients", ingredientsRepo.findAll());
		return "viewIngredients";
	}
	
	//for editing ingredients
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable("id") long id, Model model) {
		Ingredients i = ingredientsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
		model.addAttribute("ingredients", i);
		return "editIngredients";
		}
	//also for editing ingredients
	@PostMapping("/update/{id}")
	public String editIngredients(@PathVariable("id") long id, @Valid Ingredients i, BindingResult result, Model model) {
		if(result.hasErrors()) {
			i.setId(id);
			return "editIngredients";
		}
		ingredientsRepo.save(i);
		model.addAttribute("ingredients", ingredientsRepo.findAll());
		return "viewIngredients";
	}
	
}
