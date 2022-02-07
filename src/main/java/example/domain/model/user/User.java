package example.domain.model.user;

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

    public UserId GetUserId(){
        return userId;
    }
    public UserName GetUserName(){
        return userName;
    }
    public UserRole getUserRole(){
        return userRole;
    }
    public UserPassword getPassword(){
        return userPassword;
    }

    public void setUserId(UserId userId){
        this.userId = userId;
    }
    
    public void setUserName(UserName userName){
        this.userName = userName;
    }
    
    public void setUserPassword(UserPassword userPassword){
        this.userPassword = userPassword;
    }
    
    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }
}
