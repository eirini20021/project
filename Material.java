public class Material extends Entity {
    
    final private double level1; 
    final private double level2; 
    final private double level3;
    
    public String getDetails() {
       return " " + getName() + " is a Material object and has level1 = " + level1 + ", level2 = " + level2 + ", level3 = " + level3 + ".";
    }
    public Material(String name, String description, double l1, double l2, double l3) {
        super(name, description);
        level1 = l1;
        level2 = l2;
        level3 = l3;
    }
    public double getLevel1() {
        return level1;
    }
    public double getLevel2() {
        return level2;
    }
    public double getLevel3() {
        return level3;
    }
}
