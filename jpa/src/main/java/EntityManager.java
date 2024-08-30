import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.convert.PeriodStyle;

public class EntityManager {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student details");

}
