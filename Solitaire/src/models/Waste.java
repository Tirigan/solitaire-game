package models;

public class Waste extends Pile {
    private static Waste instance;

    private Waste() {
        super();
    }

    public static Waste getInstance() {
        if (instance == null) {
            instance = new Waste();
        }
        return instance;
    }

    public boolean canAdd(Card card) {
        return false;
    }

    @Override
    public void clear() {
        super.clear();
        instance = null;
    }
}
