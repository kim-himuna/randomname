package example.domain.model.user;

public class User {
    private UserId userId;
    private UserName userName;
    private UserRole userRole;

    public User(UserId userId,UserName userName,UserRole userRole){
        this.userId = userId;
        this.userName = userName;
        this.userRole = userRole;
    }

    public UserId GetUserId(){
        return userId;
    }
    public UserName GetUserName(){
        return userName;
    }
    public UserRole getUserRole(){
        return userRole;
    }
}
