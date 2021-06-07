public class Main {
    public static void Main(String[] args) {
        Organization o = new Organization("Donation System");
        Material milk = new Material("milk", "a white liquid", 2, 6, 11);
        Material sugar = new Material("sugar", "white and sweet", 1, 3, 5.5);
        Material rice = new Material("rice", "big on carbs", 2, 5.5, 10);
        Service MedicalSupport = new Service("MedicalSupport", "provided by a doctor");
        Service NurserySupport = new Service("NurserySupport", "provided by a nurse");
        Service BabySitting = new Service("BabySitting", "provided by an experienced babysitter");
        Admin admin = new Admin("John", "6930184620");
        Beneficiary b1 = new Beneficiary("Maria", "6939143220");
        Beneficiary b2 = new Beneficiary("Paul", "6981563100");
        Donator d1 = new Donator("George", "6992470137");
        Donator d2 = new Donator("Lisa", "6132870130");
        o.setAdmin(admin);
        
        try {
            o.addEntity(milk);
            o.addEntity(sugar);
            o.addEntity(rice);
            o.addEntity(MedicalSupport);
            o.addEntity(NurserySupport);
            o.addEntity(BabySitting);
            o.insertBeneficiary(b1);
            o.insertBeneficiary(b2);
            o.insertDonator(d1);
            o.insertDonator(d2);
            RequestDonation rd1 = new RequestDonation(milk, 20);
            RequestDonation rd2 = new RequestDonation(rice, 15);
            RequestDonation rd3 = new RequestDonation(MedicalSupport, 8);
            RequestDonation rd4 = new RequestDonation(NurserySupport, 15);
            RequestDonation rd5 = new RequestDonation(sugar, 15);
            RequestDonation rd6 = new RequestDonation(MedicalSupport, 1);
            RequestDonation rd7 = new RequestDonation(milk, 2);
            d1.donatorAdd(rd1, o);
            d1.donatorAdd(rd3, o);
            d1.donatorAdd(rd5, o);
            d1.donatorCommit(o);
            d2.donatorAdd(rd2, o);
            d2.donatorAdd(rd4, o);
            
            b1.beneficiaryRequestsAdd(rd7, o);
            b1.beneficiaryRequestsAdd(rd6, o);
            
            Menu menu = new Menu(o);
        } catch(UserAlreadyExists u) {
        } catch(AddEntityWithSameId a) {
        } catch(EntityOfRequestDoesNotExits e) {
        } catch(WrongBeneficiaryQuantity1 w1) {   
        } catch(WrongBeneficiaryQuantity2 w2) {
        } catch(NegativeQuantity nq) {}
        
    }
}
