package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//      Car car1 = new Car("ZIL", 2);
//      User user5  = new User("User5", "Lastname5", "user5@mail.ru");
//      user5.setCar(car1);
//      userService.add(user5);
//
//      User user6  = new User("User6", "Lastname6", "user6@mail.ru");
//      user6.setCar(car1);
//      userService.add(user6);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         Car car = user.getCar();
         System.out.println("Car = " + (car == null ? "No" :  car.toString()));
         System.out.println();
      }

      User userWithCar =  userService.getUserByCar("ZIL", 2);
      if (userWithCar != null) {
         System.out.println("Id = " + userWithCar.getId());
         System.out.println("First Name = " + userWithCar.getFirstName());
         System.out.println("Last Name = " + userWithCar.getLastName());
         System.out.println("Email = " + userWithCar.getEmail());
      }
      context.close();
   }
}
