package pe.isil.seguridad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> userToFind = userRepository.findUserByEmail(email);
        return userToFind.orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void updateUser(User user, Long id) {
        Optional<User> userToUpdate = userRepository.findById(id);
        if(userToUpdate.isPresent()){
            userToUpdate.get().setName(user.getName()!=null? user.getName() : userToUpdate.get().getName() );
            userToUpdate.get().setLastname(user.getLastname()!=null? user.getLastname() : userToUpdate.get().getLastname() );
            userToUpdate.get().setEmail(user.getEmail()!=null? user.getEmail() : userToUpdate.get().getEmail() );
            userToUpdate.get().setUrlPhoto(user.getUrlPhoto()!=null? user.getUrlPhoto() : userToUpdate.get().getUrlPhoto() );
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userToDelete = userRepository.findById(id);
        userToDelete.ifPresent(user -> userRepository.delete(user));
    }
}
