import java.util.*;
public class Organization {
    
    private String name;
    private Admin admin;
    private List<Entity> entityList;
    List<Donator> donatorList;
    List<Beneficiary> beneficiaryList;
    RequestDonationList currentDonations;
    
    public Organization(String name) {
        this.name = name;
        entityList = new ArrayList<Entity>();
        donatorList = new ArrayList<Donator>();
        beneficiaryList = new ArrayList<Beneficiary>();
        currentDonations = new RequestDonationList();
    }
    
    public List<Entity> getentityList() {
        return entityList;
    }
    
    public List<Donator> getdonatorList() {
        return donatorList;
    }
    
    public List<Beneficiary> getbeneficiaryList() {
        return beneficiaryList;
    }
    
    public RequestDonationList getCurrentDonations() {
        return currentDonations;
    }
    
    public String getName() {
        return name;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    public Admin getAdmin() {
        return admin;
    }
    
    public void addEntity(Entity e) throws AddEntityWithSameId {
        boolean flag = true;
        if(entityList.size() > 0) {
            for(Entity entity : entityList) {
                if(entity.getId() == e.getId()) {
                    flag = false;
                    throw new AddEntityWithSameId(); 
                }
            }
        }
        if(flag) {
            entityList.add(e);
        }
    }
    
    public void removeEntity(Entity e) {
        entityList.remove(e);
    }
    
    public void insertDonator(Donator d) throws UserAlreadyExists {
        boolean flag = true;
        if(donatorList.size() > 0) {
            for(Donator donator : donatorList) {
                if(donator.getName().equals(d.getName())) { //συγκρίνουμε τον Donator που δώθηκε ως όρισμα με τους Donators στη donatorList ως προς το όνομα
                    flag = false;
                    throw new UserAlreadyExists(d);
                }
            }
        }
        if(flag) {
            donatorList.add(d);
        }
    }
    
    public void removeDonator(Donator d) {
        donatorList.remove(d);
    }
    
    public void insertBeneficiary(Beneficiary b) throws UserAlreadyExists{
        boolean flag = true;
        if(beneficiaryList.size() > 0) {
            for(Beneficiary beneficiary : beneficiaryList) {  //συγκρίνουμε τον Beneficiary που δώθηκε ως όρισμα με τους Beneficiaries στη beneficiaryList ως προς το όνομα
                if(beneficiary.getName().equals(b.getName())) {
                    flag = false;
                    throw new UserAlreadyExists(b);
                }
            }
        }
        if(flag) {
            beneficiaryList.add(b);
        }
    }
    
    public void removeBeneficiary(Beneficiary b) {
        beneficiaryList.remove(b);
    }
    
    public void listEntities() {
        System.out.println("Materials: ");
        for(Entity e : entityList) {
            if(e.getClass().equals(Material.class)) {
                System.out.println(e.getEntityInfo());
            }
        }
        System.out.println("\nServices: ");
        for(Entity e : entityList) {
            if(e.getClass().equals(Service.class)) {
                System.out.println(e.getEntityInfo());
            }
        }
    }
    
    public void listBeneficiaries() {
        int count = 1;
        for(Beneficiary b : beneficiaryList) {
            System.out.println(count + ". " + b.getName());
            ++count;
        }
    }
    
    public void listDonators() {
        int count = 1;
        for(Donator d : donatorList) {
            System.out.println(count + ". " + d.getName());
            ++count;
        }
    }
}
