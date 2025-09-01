package webservice_lektion6_uppgifter.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice_lektion6_uppgifter.Exceptions.UserNotFoundException;
import webservice_lektion6_uppgifter.Model.User;
import webservice_lektion6_uppgifter.Repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    // Dependency Injektion av min repository
    @Autowired
    UserRepository userRepository;

    //Metod för att hämta en användare baserat på ID
    @GetMapping ("/{id}")
    public ResponseEntity <User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id)));
    }


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

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User userInformation)
    {
        //Felhantering om något skulle saknas.
        // Vi börjar med ID:t
        if (userInformation.getName() == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (userInformation.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        //? Uppdatera fälten som finns under användaren
        userToUpdate.setName(userInformation.getName());
        userToUpdate.setPassword(userInformation.getPassword());
        userToUpdate.setEnabled(userInformation.getEnabled());

        //! Behövs en return, för att spara och skicka all information
        return ResponseEntity.ok(userRepository.save(userToUpdate));
    }
}
