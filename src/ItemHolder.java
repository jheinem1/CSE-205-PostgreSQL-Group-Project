import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public abstract class ItemHolder {

    private final HashMap<String, Item> itemMap = new HashMap<String, Item>();

    public Item getItemByName(String name) {
        return itemMap.get(name);
    }

    public ItemHolder pushItem(Item item) {
        itemMap.put(item.getName(), item);
        return this;
    }

    public Item removeItem(String name) {
        return itemMap.remove(name);
    }

    public Item[] removeItems(String[] names) {
        var out = new Item[names.length];
        var i = 0;
        for (var name : names)
            out[i++] = removeItem(name);
        return out;
    }

    public ItemHolder updateItem(String name, double price, int quantity) {
        if (getItemByName(name) != null)
            pushItem(new Item(name, price, quantity));
        return this;
    }

    public ItemHolder updateItems(String[] names, double price, int quantity) {
        for (var name : names)
            updateItem(name, price, quantity);
        return this;
    }

    public Collection<Item> toArray() {
        return itemMap.values();
    }

    public Set<String> getNames() {
        return itemMap.keySet();
    }

    protected String encode() {
        return Serializer.serialize(toArray().toArray(new Item[0]));
    }
}