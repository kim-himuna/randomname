package example.domain.model.user;

import lombok.Getter;

@Getter
public class User {
    private UserId userId;
    private UserName userName;
    private UserRole userRole;
    private UserPassword userPassword;

    @Deprecated
    public User(){}

    public User(UserId userId,UserName userName,UserRole userRole,UserPassword userPassword){
        this.userId = userId;
        this.userName = userName;
        this.userRole = userRole;
        this.userPassword = userPassword;
    }

}
