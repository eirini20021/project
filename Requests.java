import java.util.*;
public class Requests extends RequestDonationList {
    public void add(RequestDonation rd, Beneficiary b, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, EntityOfRequestDoesNotExits, NegativeQuantity {
        RequestDonation rd2 = o.getCurrentDonations().get(rd.getEntity().getId());
        if((rd2 != null) && (rd.getQuantity() <= rd2.getQuantity()) && (rd.getQuantity() >= 0)) { 
            if(validRequestDonation(rd, b)) {
                super.add(rd, o);
            } else {
                throw new WrongBeneficiaryQuantity2();
            }
        } else {
            throw new WrongBeneficiaryQuantity1();
        }
    }
    public void modify(RequestDonation rd, Beneficiary b, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, NegativeQuantity {
        if((rd.getQuantity() <= o.getCurrentDonations().get(rd.getEntity().getId()).getQuantity()) && (rd.getQuantity() >=0)) {  //ελέγχουμε αν η ποσότητα του Request 
                                                                                                                                //είναι μικρότερη από αυτή που υπάρχει ή αρνητική
            if(validRequestDonation(rd, b)) {
                super.modify(rd);
            } else {
                throw new WrongBeneficiaryQuantity2();
            }
        } else {
            throw new WrongBeneficiaryQuantity1();
        }
    }
    public boolean validRequestDonation(RequestDonation rd, Beneficiary b) {
        if(rd.getEntity().getClass().equals(Material.class)) {  //ελέγχουμε τη κλάση του Entity από το RequestDonation
            double total;
            if(b.getrecievedList().get(rd.getEntity().getId()) != null) {   //ελέγχουμε αν o Beneficiary έχει λάβει donation από αυτό το Material
                total = b.getrecievedList().get(rd.getEntity().getId()).getQuantity() + rd.getQuantity();
            } else {
                total = rd.getQuantity();
            }
            if(b.getnoPersons() == 1) {
                if(((Material)rd.getEntity()).getLevel1() < total) {
                    return false;
                } else {
                    return true;
                }
            } else if(b.getnoPersons() <= 4) {
                if(((Material)rd.getEntity()).getLevel2() < total) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if(((Material)rd.getEntity()).getLevel3() < total) {
                    return false;
                } else {
                    return true;
                }
            }   
        } else {
            return true;
        }
    }
    public void commit(Beneficiary b, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, EntityOfRequestDoesNotExits, NegativeQuantity { 
        List<RequestDonation> list = new ArrayList<RequestDonation>();
        for(RequestDonation rd : getrdEntities()) {
            RequestDonation rd2 = o.getCurrentDonations().get(rd.getEntity().getId());   //παίρνουμε το RequestDonation του οργανισμού
            if((rd2 != null) && (rd.getQuantity() <= rd2.getQuantity())) { 
                if(validRequestDonation(rd, b)) {
                    double q = rd.getQuantity();   //το αποθηκεύουμε για την εκτύπωση παρακάτω
                    rd.setQuantity(o.getCurrentDonations().get(rd.getEntity().getId()).getQuantity() - rd.getQuantity());
                    o.getCurrentDonations().modify(rd);
                    list.add(rd);   //δεν μπορούμε να τα διαγράψουμε εδώ γιατί θα προκύψει εξαίρεση για αυτό τα αποθηκεύουμε σε μία λίστα και τα διαγράφουμε μετά
                    b.getrecievedList().add(rd, o);
                    System.out.println("Request for " + q + " of " + rd.getEntity().getName() + " has been successful.");
                } else {
                    throw new WrongBeneficiaryQuantity2(rd);
                }
            } else {
                throw new WrongBeneficiaryQuantity1(rd);
            }
        }
        for(RequestDonation rd : list) {
            remove(rd);
        }
    }   
}
