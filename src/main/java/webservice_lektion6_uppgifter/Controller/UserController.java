package webservice_lektion6_uppgifter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice_lektion6_uppgifter.Model.User;
import webservice_lektion6_uppgifter.Repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    // Dependency Injektion av min repository
    @Autowired
    UserRepository userRepository;

    // Metod för att hämta alla användare
    @GetMapping
    public ResponseEntity <List <User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    // Metod för att använda en POST för att lägga till en användare
    @PostMapping
    public ResponseEntity <User> saveUsertoUserList(
            @RequestBody User user
    ){
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PutMapping
    public ResponseEntity <User> updateUser(
            @RequestBody User user
    ){
        return ResponseEntity.ok(userRepository.save(user));
    }
}
