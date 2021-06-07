public abstract class Entity {
    
    private String name;
    private String description;
    private int id;
    private static int num_entities = 0;    //στατική μεταβλητή για να παίρνουν αυτόματα τα αντικείμενα id όταν δημιουργούνται
    
    public Entity(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = ++num_entities;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEntityInfo() {
        return "Id: " + id + ". " + name + " is " + description + ".";
    }
    
    public int getId() {
        return id;
    }
    
    public abstract String getDetails();
    
    public String toString() {
        String s = getEntityInfo();
        s = s.concat(getDetails());
        return s;
    }
    
}