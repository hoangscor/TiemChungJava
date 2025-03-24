package edu.uth.tiemchungjava.service;
import edu.uth.tiemchungjava.models.User;
import edu.uth.tiemchungjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);

    }
    //Trien khai tiep cac methods save, delete
    //Len lop trien khai them
    //--> Kien thuc hoc tung buoi va tich luy ---> van dap
}
