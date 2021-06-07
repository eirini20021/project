public abstract class User {
 
    private String name;
    private String phone;
    
    User(String n, String p) {
        name = n;
        phone = p;
    }
    public String getName() {
        return name;
    }
    public void setAdmin(String name, String phone) {
        Admin admin = new Admin(name, phone);
    }
    public String getPhone() {
        return phone;
    }
}
