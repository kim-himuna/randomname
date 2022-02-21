package example.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.domain.model.user.User;
import example.domain.model.user.UserName;
import example.domain.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByName(new UserName(username));
        if(user == null){
            throw new UsernameNotFoundException(username + "is not found");
        }

        return new UserAuthDetails(user);
    }

    @Transactional
    public void register(User user){
        userRepository.registerUser(user);

    }

    public boolean isExistUser(String username){

        if(userRepository.getUserByName(new UserName(username)) == null){
            return false;
        }

        return true;
    }
}
