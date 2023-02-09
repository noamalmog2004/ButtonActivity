import com.google.firebase.auth.FirebaseUser;

public class User
{
    public String email, password, fullName, username;
    public FirebaseUser user;
    public User(String email, String password, String fullName, String username, FirebaseUser user)
    {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.username = username;
        this.user = user;

    }
}
