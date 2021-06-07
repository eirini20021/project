import java.util.*;
public class Donator extends User {
    
   private Offers offersList;
   Donator(String name, String phone) {
        super(name, phone);
        offersList = new Offers();
   }
   
   public Offers getoffersList() {
       return offersList;
   }
   //οι wrapper μέθοδοι για την add και commit
   public void donatorAdd(RequestDonation rd, Organization o) throws EntityOfRequestDoesNotExits, NegativeQuantity {
       offersList.add(rd, o);
   }
   
   public void donatorCommit(Organization o) throws EntityOfRequestDoesNotExits, NegativeQuantity {
       offersList.commit(o);
   }
}
