import java.util.*;
public class RequestDonationList {
    
    private List<RequestDonation> rdEntities = new ArrayList<RequestDonation>();
    
    public List<RequestDonation> getrdEntities() {
        return rdEntities;
    }
    
    public RequestDonation get(int id) {
        RequestDonation r = null;
        for(RequestDonation rd : rdEntities) {
            if(rd.getEntity().getId() == id) {
                r = rd;
            }
        }
        return r; 
    }
    
    public void add(RequestDonation rd, Organization o) throws EntityOfRequestDoesNotExits, NegativeQuantity {
        if(rd.getQuantity() >= 0) {
            boolean flag = false;
            if(get(rd.getEntity().getId()) != null) {   //καλούμε τη μέθοδο get() και δίνουμε σαν όρισμα το id του RequestDonation που παίρνει η συνάρτηση add() 
                flag = true;                            //για να δούμε αν το rd περιέχεται στην λίστα rdEntities
                rd.setQuantity(rd.getQuantity() + get(rd.getEntity().getId()).getQuantity());  //αλλάζουμε το Quantity του rd και μετά καλούμε την modify
                modify(rd);
            }
            if(flag == false) {     //αν flag == false τότε το rd δεν περιέχεται στην rdEntities
                for(Entity e : o.getentityList()) {
                    if(rd.getEntity().getId() == e.getId()) {
                        flag = true;
                        rdEntities.add(rd);
                        break;
                    }
                }
                if(flag == false) {
                    throw new EntityOfRequestDoesNotExits();
                }
            }
        } else {
                throw new NegativeQuantity();
        }
    }
    
    public void remove(RequestDonation rd) {
        rdEntities.remove(rd);
    }
    
    public void modify(RequestDonation rd) throws NegativeQuantity {
        if(rd.getQuantity() >= 0) {
            for(int i=0; i<rdEntities.size(); i++) {
                if(rdEntities.get(i).getEntity().getId() == rd.getEntity().getId()) {  //προσπελαύνουμε τα αντικείμενα της rdEntities μέχρι να βτούμε το rd
                    rdEntities.get(i).setQuantity(rd.getQuantity());
                    break;
                }
            }
        } else {
        throw new NegativeQuantity();
        }
    }
    
    public void monitor() {
        for(RequestDonation rd : rdEntities) {
            System.out.println("Entity: " + rd.getEntity().getName());
            System.out.println("Quantity: " + rd.getQuantity() + "\n");
        }
    }
    
    public void reset() {
        rdEntities.clear();
    }
}
