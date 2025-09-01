package webservice_lektion6_uppgifter.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import webservice_lektion6_uppgifter.Model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
