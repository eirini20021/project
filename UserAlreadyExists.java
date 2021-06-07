public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(User u) {
        System.out.println("User " + u.getName() + " already exists.");
    }
}
