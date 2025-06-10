package at.htl.mappers;

import at.htl.dtos.OrderDto;
import at.htl.dtos.OrderEmployeeDto;
import at.htl.dtos.PostOrderDto;
import at.htl.entities.Customer;
import at.htl.entities.Dish;
import at.htl.entities.Drink;
import at.htl.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderDto toDto(Order order) {
        String dishName = order.getDishes() != null && !order.getDishes().isEmpty()
                ? order.getDishes().get(0).getName() : null;
        String drinkName = order.getDrinks() != null && !order.getDrinks().isEmpty()
                ? order.getDrinks().get(0).getName() : null;

        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setPrice(order.getPrice());
        dto.setDishName(dishName);
        dto.setDrinkName(drinkName);
        return dto;
    }

    public static List<OrderDto> toDtoList(List<Order> orders) {
        List<OrderDto> dtoList = new ArrayList<>();
        for (Order order : orders) {
            dtoList.add(toDto(order));
        }
        return dtoList;
    }

    public static OrderEmployeeDto toEmployeeDto(Order order) {
        String dishName = order.getDishes() != null && !order.getDishes().isEmpty()
                ? order.getDishes().get(0).getName() : null;
        String drinkName = order.getDrinks() != null && !order.getDrinks().isEmpty()
                ? order.getDrinks().get(0).getName() : null;
        String customerName = order.getCustomer() != null
                ? order.getCustomer().getName() : null;

        OrderEmployeeDto dto = new OrderEmployeeDto();
        dto.setId(order.getId());
        dto.setPrice(order.getPrice());
        dto.setDishName(dishName);
        dto.setDrinkName(drinkName);
        dto.setCustomerName(customerName);
        return dto;
    }

    public static List<OrderEmployeeDto> toEmployeeDtoList(List<Order> orders) {
        List<OrderEmployeeDto> dtoList = new ArrayList<>();
        for (Order order : orders) {
            dtoList.add(toEmployeeDto(order));
        }
        return dtoList;
    }

    public static Order fromPostDto(PostOrderDto dto, List<Dish> dishes, List<Drink> drinks, Customer customer) {
        Order order = new Order();
        order.setPrice(dto.getPrice());
        order.setKtmScore(dto.getKtmScore());
        order.setDate(dto.getDate());
        order.setDishes(dishes);
        order.setDrinks(drinks);
        order.setCustomer(customer);
        return order;
    }
}
