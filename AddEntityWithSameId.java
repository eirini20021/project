public class AddEntityWithSameId extends Exception {
    public AddEntityWithSameId() {
        System.out.println("An entity with this id already exists.");
    }
}
