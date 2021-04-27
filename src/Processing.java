public class Processing extends ItemHolder {
    public Processing shipItem(String name, int quantity) {
        removeItem(name, quantity);
        return this;
    }

    public Processing shipItems(String[] names, int quantity) {
        removeItems(names, quantity);
        return this;
    }

    public Processing synchronizeWithDB() {
        //TODO make do stuff
        return this;
    }
}
