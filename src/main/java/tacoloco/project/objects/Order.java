package tacoloco.project.objects;

/**
 *
 * @author kenji
 */
public class Order {
    private int veggie;
    private int chicken;
    private int beef;
    private int chorizo;
    
    public Order() {}
    public Order(int veggie, int chicken, int beef, int chorizo) {
        this.veggie = veggie;
        this.chicken = chicken;
        this.beef = beef;
        this.chorizo = chorizo;
    }
    public int getVeggie() {
        return veggie;
    }
    public int getChicken() {
        return chicken;
    }
    public int getBeef() {
        return beef;
    }
    public int getChorizo() {
        return chorizo;
    }
}
