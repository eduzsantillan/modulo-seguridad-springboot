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
    public UserDTO addUser(User user) {

        Optional<User> userToAdd = userRepository.findUserByEmail(user.getEmail());

        if(userToAdd.isPresent()){
            return UserDTO.whenUserEmailAlreadyExists();
        }else{
            userRepository.save(user);
            return UserDTO.whenUserRegistrationSucced();
        }
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


    @Override
    public UserDTO updateUser(User user, Long id) {
        try{
            Optional<User> userToUpdate = userRepository.findById(id);
            if(userToUpdate.isPresent()){
                userToUpdate.get().setName(user.getName()!=null? user.getName() : userToUpdate.get().getName() );
                userToUpdate.get().setLastname(user.getLastname()!=null? user.getLastname() : userToUpdate.get().getLastname() );
                userToUpdate.get().setEmail(user.getEmail()!=null? user.getEmail() : userToUpdate.get().getEmail() );
                userToUpdate.get().setUrlPhoto(user.getUrlPhoto()!=null? user.getUrlPhoto() : userToUpdate.get().getUrlPhoto() );
                userRepository.save(userToUpdate.get());
                return UserDTO.whenUserRegistrationSucced();
            }else{
                return UserDTO.whenError("Usuario a actualizar no está en la base de datos");
            }
        }catch (Exception e){
            return UserDTO.whenError(e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userToDelete = userRepository.findById(id);
        userToDelete.ifPresent(user -> userRepository.delete(user));
    }

    @Override
    public User findUserById(Long id) {

        UserDTO userDTO = UserDTO.builder()
                .code("200")
                .build();




        return userRepository.findById(id).orElse(null);
    }


}
