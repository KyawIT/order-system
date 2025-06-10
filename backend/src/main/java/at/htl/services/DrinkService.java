package at.htl.services;

import at.htl.entities.Dish;
import at.htl.entities.Drink;
import at.htl.repositories.DishRepository;
import at.htl.repositories.DrinkRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/drinks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DrinkService {

    @Inject
    DrinkRepository drinkRepository;

    @GET
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }
}
