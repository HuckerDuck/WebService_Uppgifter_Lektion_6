package webservice_lektion6_uppgifter.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    // Sätter en Collumn för att lösenordet alltid behöver vara med
    // Ser till att lösenordet endast kan skickas med en write
    // Alltså kan man inte använda en Read för att hämta det
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;
    private Boolean isEnabled;

    // Tom konstruktor för Jackson behövs
    public User() {
    }

    // Konstruktor med de fyra parametrarna jag har över
    public User(Long id, String name, String password, Boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    // Getter & Setter
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public Boolean getEnabled() {return isEnabled;}
    public void setEnabled(Boolean enabled) {isEnabled = enabled;}
}
