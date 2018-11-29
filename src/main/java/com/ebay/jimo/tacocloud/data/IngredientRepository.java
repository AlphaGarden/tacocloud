package com.ebay.jimo.tacocloud.data;

import com.ebay.jimo.tacocloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
