package example.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import example.application.repository.UserRepository;
import example.domain.model.user.User;
import example.domain.model.user.UserName;
import example.domain.model.user.UserPassword;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncorder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);

        User user = userRepository.getUserByName(new UserName(username));
        if(user == null){
            throw new UsernameNotFoundException(username + "is not found");
        }

        System.out.println(user.getUserRole().GetValue());
        return new UserAuthDetails(user);
    }

    public void register(User user){
        System.out.println(user.getPassword());
        user.setUserPassword(new UserPassword(passwordEncorder.encode(user.getPassword().GetValue())));
        System.out.println(user.getPassword());
        userRepository.registerUser(user);

    }
}
