public class Service extends Entity {
   
   public Service(String name, String description) {
       super(name, description);
   }
   public String getDetails() {
       
       return " " + getName() + " is a Service object.";
   }
}
