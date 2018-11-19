package com.ebay.jimo.tacocloud.web;

import com.ebay.jimo.tacocloud.data.IngredientRepository;
import com.ebay.jimo.tacocloud.data.TacoRepository;
import com.ebay.jimo.tacocloud.domain.Ingredient;
import com.ebay.jimo.tacocloud.domain.Ingredient.Type;
import com.ebay.jimo.tacocloud.domain.Order;
import com.ebay.jimo.tacocloud.domain.Taco;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order") // Ensure object like order attribute that should be kept in session and available across multiple requests.
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        //List<Ingredient> ingredients = Arrays.asList(
        //    new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        //    new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        //    new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        //    new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        //    new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        //    new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        //    new Ingredient("CHED", "Cheddar", Type.CHEESE),
        //    new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        //    new Ingredient("SLSA", "Salsa", Type.SAUCE),
        //    new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        //);
        List<Ingredient> ingredients = (ArrayList<Ingredient>)ingredientRepo.findAll();
        log.info("The number of ingredients loaded from data base is {}", ingredients.size());
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        log.info("Process design. {}", design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
            .stream()
            .filter(ingredient -> ingredient.getType().equals(type))
            .collect(Collectors.toList());
    }
}
