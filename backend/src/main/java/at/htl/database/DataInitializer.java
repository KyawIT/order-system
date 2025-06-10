package at.htl.database;

import at.htl.entities.*;
import at.htl.repositories.*;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Startup
@ApplicationScoped
public class DataInitializer {

    @Inject
    CustomerRepository customerRepository;
    @Inject
    OrderRepository orderRepository;
    @Inject
    DepartmentRepository departmentRepository;
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    DepManagerRepository depManagerRepository;
    @Inject
    DishRepository dishRepository;
    @Inject
    DrinkRepository drinkRepository;


    @PostConstruct
    @Transactional
    void init() {
        if (customerRepository.findAll().isEmpty() && orderRepository.findAll().isEmpty() &&
                employeeRepository.findAll().isEmpty() && departmentRepository.findAll().isEmpty()){
            addDishes();
            addDrinks();
            List<Department> departments = addDepartment();
            List<DepManager> depManagers = addDepManager(departments);
            addEmployees(departments, depManagers);
            addCustomers();
        }
    }

    private void addDishes() {
        List<Dish> dishes = new ArrayList<>();

        Dish dish1 = new Dish();
        dish1.setName("C-Burge");
        dish1.setType("Burges");
        dish1.setVegan(false);
        dish1.setPrice(5.99);
        dishes.add(dish1);

        Dish dish2 = new Dish();
        dish2.setName("XL-Burge");
        dish2.setType("Burges");
        dish2.setVegan(false);
        dish2.setPrice(8.99);
        dishes.add(dish2);

        Dish dish3 = new Dish();
        dish3.setName("FreshC-Taco");
        dish3.setType("Tacos");
        dish3.setVegan(true);
        dish3.setPrice(5.99);
        dishes.add(dish3);

        Dish dish4 = new Dish();
        dish4.setName("SpicyB-Taco");
        dish4.setType("Tacos");
        dish4.setVegan(false);
        dish4.setPrice(6.49);
        dishes.add(dish4);

        Dish dish5 = new Dish();
        dish5.setName("Frites");
        dish5.setType("Beilagen");
        dish5.setVegan(true);
        dish5.setPrice(2.49);
        dishes.add(dish5);

        Dish dish6 = new Dish();
        dish6.setName("Nuggets de poulet");
        dish6.setType("Beilagen");
        dish6.setVegan(false);
        dish6.setPrice(3.49);
        dishes.add(dish6);

        Dish dish7 = new Dish();
        dish7.setName("Rayures de poulet");
        dish7.setType("Beilagen");
        dish7.setVegan(false);
        dish7.setPrice(4.99);
        dishes.add(dish7);

        Dish dish8 = new Dish();
        dish8.setName("Sac de Pomme");
        dish8.setType("Dessert");
        dish8.setVegan(true);
        dish8.setPrice(2.49);
        dishes.add(dish8);

        Dish dish9 = new Dish();
        dish9.setName("Caki");
        dish9.setType("Dessert");
        dish9.setVegan(false);
        dish9.setPrice(3.99);
        dishes.add(dish9);

        Dish dish10 = new Dish();
        dish10.setName("Do de nut");
        dish10.setType("Dessert");
        dish10.setVegan(false);
        dish10.setPrice(4.49);
        dishes.add(dish10);

        dishRepository.save(dishes);
    }
    private void addDrinks() {
        List<Drink> drinks = new ArrayList<>();

        Drink drink1 = new Drink();
        drink1.setName("Coke");
        drink1.setQuantity(500);
        drink1.setPrice(2.50);
        drinks.add(drink1);

        Drink drink2 = new Drink();
        drink2.setName("Coke Zero");
        drink2.setQuantity(500);
        drink2.setPrice(2.50);
        drinks.add(drink2);

        Drink drink3 = new Drink();
        drink3.setName("Zam-Zam-Water");
        drink3.setQuantity(500);
        drink3.setPrice(3.00);
        drinks.add(drink3);

        drinkRepository.save(drinks);
    }
    private List<Department> addDepartment() {
        List<Department> departments = new ArrayList<>();

        Department dep1 = new Department();
        dep1.setName("Kitchen");
        departments.add(dep1);

        Department dep2 = new Department();
        dep2.setName("Service");
        departments.add(dep2);

        departmentRepository.save(departments);
        return departments;
    }
    private List<DepManager> addDepManager(List<Department> departments) {
        List<DepManager> depManagers = new ArrayList<>();

        DepManager depManager2 = new DepManager();
        depManager2.setName("Waldtraud Heimer");
        depManager2.setDepartment(departments.getFirst());
        depManager2.setSalary(4800.00);
        depManager2.setUserName("KM01");
        depManager2.setPassword("a36c101570cc4410993de5385ad7034adb2dae6a05139ac7672577803084634d");
        depManagers.add(depManager2);

        DepManager depManager3 = new DepManager();
        depManager3.setName("Günther Lauch");
        depManager3.setDepartment(departments.getLast());
        depManager3.setSalary(6500.00);
        depManager3.setUserName("SM02");
        depManager3.setPassword("dbf7d548e89320a79e5e89a337a0f419dc006f2c239acc8d5c875b837b0065aa");
        depManagers.add(depManager3);

        depManagerRepository.save(depManagers);
        return depManagers;
    }
    public void addCustomers() {
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setName("Max Mustermann");
        customer1.setAge(30);
        customer1.setEmail("max.mustermann@gmail.com");
        customer1.setCity("Vienna");
        customer1.setZipCode(1234);
        customer1.setAddress("Hauptstraße 1");
        customer1.setUserName("MM11");
        customer1.setPassword("df4a1f5843ab56ba46a96327503d901db950722bf4c00077ee3f793bc1fd4495");
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Maria Musterfrau");
        customer2.setAge(25);
        customer2.setEmail("maria.musterfrau@gmail.com");
        customer2.setCity("Graz");
        customer2.setZipCode(5678);
        customer2.setAddress("Nebenstraße 12");
        customer2.setUserName("MM12");
        customer2.setPassword("d3bdb65e5d82d70dce234a5e848a529f39a0dc5069bc6b2f7594e19ee3b40fc8");
        customers.add(customer2);

        Customer customer3 = new Customer();
        customer3.setName("John Doe");
        customer3.setAge(40);
        customer3.setEmail("john.doe@gmail.com");
        customer3.setCity("Linz");
        customer3.setZipCode(4321);
        customer3.setAddress("Ringstraße 5");
        customer3.setUserName("JD13");
        customer3.setPassword("ec9b10a4a79c4e8a4a23be6edb064854d91b9aebf348d9a910fe6b38cad08022");
        customers.add(customer3);

        Customer customer4 = new Customer();
        customer4.setName("Lisa Meier");
        customer4.setAge(28);
        customer4.setEmail("lisa.meier@gmail.com");
        customer4.setCity("Salzburg");
        customer4.setZipCode(6789);
        customer4.setAddress("Parkstraße 10");
        customer4.setUserName("LM14");
        customer4.setPassword("cec3a9b89b2e391393d0f68e4bc12a9fa6cf358b3cdf79496dc442d52b8dd528");
        customers.add(customer4);

        Customer customer5 = new Customer();
        customer5.setName("Tom Müller");
        customer5.setAge(35);
        customer5.setEmail("tom.mueller@gmail.com");
        customer5.setCity("Innsbruck");
        customer5.setZipCode(9101);
        customer5.setAddress("Bahnhofstraße 23");
        customer5.setUserName("TM15");
        customer5.setPassword("6443e382eb8c761273871db32937c1f0345fb8f2e756b9568f9dbace9172ec76");
        customers.add(customer5);

        customerRepository.save(customers);
    }
    public void addEmployees(List<Department> departments, List<DepManager> depManagers) {
        List<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setName("Hans Müller");
        employee1.setDepartment(departments.getFirst());
        employee1.setSalary(3000.00);
        employee1.setDepManager(depManagers.getFirst());
        employee1.setUserName("HM21");
        employee1.setPassword("a19e20b13aa6801b34435dd1a9f55f646421c9fae72718ac7836a8b9b8d5a998");
        employees.add(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Lisa Berger");
        employee2.setDepartment(departments.getFirst());
        employee2.setSalary(2500.00);
        employee2.setDepManager(depManagers.getFirst());
        employee2.setUserName("LB22");
        employee2.setPassword("863634f9c3436e888b0889c5a5800dbc3e8f529728610f261a4a777f10abf93f");
        employees.add(employee2);

        Employee employee3 = new Employee();
        employee3.setName("Peter Schmidt");
        employee3.setDepartment(departments.getFirst());
        employee3.setSalary(3200.00);
        employee3.setDepManager(depManagers.getFirst());
        employee3.setUserName("PS23");
        employee3.setPassword("ecdc07467013bf8f08bea175971dc484897f3b277a38a98af714ad2aac4f86fc");
        employees.add(employee3);

        Employee employee4 = new Employee();
        employee4.setName("Julia Fischer");
        employee4.setDepartment(departments.getFirst());
        employee4.setSalary(3000.00);
        employee4.setDepManager(depManagers.getFirst());
        employee4.setUserName("JF24");
        employee4.setPassword("e5c286bb5e8314c9f6173dde74c704b7cf2207a1042df7f2513dba36190dfe4d");
        employees.add(employee4);

        Employee employee5 = new Employee();
        employee5.setName("Thomas Klein");
        employee5.setDepartment(departments.getLast());
        employee5.setSalary(2500.00);
        employee5.setDepManager(depManagers.getLast());
        employee5.setUserName("TK25");
        employee5.setPassword("ec67b8fa0fd9e3970058e6d3c733b8fc0a814249ce2f5d6670f0fb0bf51dfa41");
        employees.add(employee5);

        Employee employee6 = new Employee();
        employee6.setName("Marie Hofmann");
        employee6.setDepartment(departments.getLast());
        employee6.setSalary(4000.00);
        employee6.setDepManager(depManagers.getLast());
        employee6.setUserName("MH26");
        employee6.setPassword("6200543e8f41fafc5108b73bc45d1f05ba52526924769c235faf227069571a85");
        employees.add(employee6);

        Employee employee7 = new Employee();
        employee7.setName("Johann Bauer");
        employee7.setDepartment(departments.getLast());
        employee7.setSalary(2100.00);
        employee7.setDepManager(depManagers.getLast());
        employee7.setUserName("JB27");
        employee7.setPassword("ac710aff83a8d492202800a03bd76e3d30aa4a85a46f2b4b2f7b869055d84a3f");
        employees.add(employee7);

        employeeRepository.save(employees);
    }
}
