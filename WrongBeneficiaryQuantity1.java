public class WrongBeneficiaryQuantity1 extends Exception{
    public WrongBeneficiaryQuantity1() {
        System.out.println("You must enter the right quantity for your request. The organization does not support this quantity.");
    }
    public WrongBeneficiaryQuantity1(RequestDonation rd) {
        System.out.println("Request for " + rd.getQuantity() + " of " + rd.getEntity().getName() + " did not succeed because this quantity exceedes the one offered by the organization");
    }
}
