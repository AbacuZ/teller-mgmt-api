package th.co.nuttida.tellermgmt.service;

import java.util.List;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.nuttida.tellermgmt.dao.jpa.UserRepository;
import th.co.nuttida.tellermgmt.domain.GenericResponse;
import th.co.nuttida.tellermgmt.domain.User;
import th.co.nuttida.tellermgmt.domain.UserLogin;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerNewUser(User user) {
//		if (usernameExist(user.getUsername())) {
//			throw new TellerMgmtUserAlreadyExistException(
//					"There is an account with that username: " + user.getUsername());
//		}
        user.setUsername(user.getUsername());
        user.setFirstname(user.getFirstname());
        user.setEmail(user.getEmail());
        user.setLastname(user.getLastname());
        user.setPassword(user.getPassword());
        user.setPosition(user.getPosition());
        user.setRoleId(user.getRoleId());
        user.setTelephone(user.getTelephone());
        return userRepository.save(user);
    }

    private boolean usernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(int id, User userUpdates) {
        User user = userRepository.findById(id);
        BeanUtils.copyProperties(userUpdates, user, "username");
        user.setUserId(id);
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(int id) {
        User found = userRepository.findById(id);
        return found;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
    
    public GenericResponse userLogin(UserLogin data) {
        User userFound = userRepository.checkLoginUser(data.getUsername(), data.getPassword());
        if (userFound != null) {
            return new GenericResponse(true, "found user");
        } else {
            return new GenericResponse(false, "not found user");
        }
    }

}
