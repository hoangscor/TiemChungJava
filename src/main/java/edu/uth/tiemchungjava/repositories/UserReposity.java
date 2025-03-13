package edu.uth.tiemchungjava.repositories;
import edu.uth.tiemchungjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserReposity extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
