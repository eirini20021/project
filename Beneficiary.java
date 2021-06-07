import java.util.*;
public class Beneficiary extends User {
    
    private int noPersons = 1;
    private RequestDonationList receivedList;
    private Requests requestsList;
    
    Beneficiary(String name, String phone) {
        super(name, phone);
        requestsList = new Requests();
        receivedList = new RequestDonationList();
    }
    
    public int getnoPersons() {
        return noPersons;
    }
    
    public void setnoPersons(int p) {
        noPersons = p;
    }
    
    public RequestDonationList getrecievedList() {
        return receivedList;
    }
    
    public Requests getrequestsList() {
        return requestsList;
    }
    //οι wrapper μέθοδοι για τις add, modify, commit και remove
    public void beneficiaryRequestsAdd(RequestDonation rd, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, EntityOfRequestDoesNotExits, NegativeQuantity {
        requestsList.add(rd, this, o);
    }
    
    public void beneficiaryRequestsModify(RequestDonation rd, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, NegativeQuantity {
        requestsList.modify(rd, this, o);
    }
    
    public void beneficiaryRequestsCommit(Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, EntityOfRequestDoesNotExits, NegativeQuantity {
        requestsList.commit(this, o);
    }
    
    public void beneficiaryRemove(RequestDonation rd) {
        requestsList.remove(rd);
    }
}
