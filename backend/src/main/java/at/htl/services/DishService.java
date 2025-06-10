package at.htl.services;

import at.htl.entities.Dish;
import at.htl.repositories.DishRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("dishes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DishService {

    @Inject
    DishRepository dishRepository;

    @GET
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
}
