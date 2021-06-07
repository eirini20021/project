public class WrongBeneficiaryQuantity2 extends Exception{
    public WrongBeneficiaryQuantity2() {
        System.out.println("You are not entitled to this quantity.");
    }
    public WrongBeneficiaryQuantity2(RequestDonation rd) {
        System.out.println("Request for " + rd.getQuantity() + " of " + rd.getEntity().getName() + " did not succeed because you are not entitled to that quantity.");
    }
}
