public class Offers extends RequestDonationList {
    public void commit(Organization o) throws EntityOfRequestDoesNotExits, NegativeQuantity{
       for(RequestDonation rd : getrdEntities()) {
           if(o.getCurrentDonations().get(rd.getEntity().getId()) == null) {
               o.getCurrentDonations().add(rd, o);
           } else {
               o.getCurrentDonations().modify(rd); 
           }
       }
       reset();
    }
}
