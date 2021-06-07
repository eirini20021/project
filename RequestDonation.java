public class RequestDonation {

    private Entity entity;
    private double quantity;
    
    public RequestDonation(Entity e, double q) {
        entity = e;
        quantity = q;
    }
    
    public Entity getEntity() {
        return entity;
    }
    
    public double getQuantity() {
        return quantity;
    }
    
    public void setQuantity(double q) {
        quantity = q;
    }
}
