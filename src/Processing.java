public class Processing extends ItemHolder {
    public Processing shipItem(String name) {
        removeItem(name);
        return this;
    }

    public Processing shipItems(String[] names) {
        removeItems(names);
        return this;
    }

    public Processing synchronizeWithDB() {
        //TODO make do stuff
        return this;
    }
}
