import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public abstract class ItemHolder {

    protected final HashMap<String, String> itemMap = new HashMap<String, String>();

    public Item getItemByName(String name) {
        return Serializer.deserializeItemObject(itemMap.get(name));
    }

    public ItemHolder pushItem(Item item) {
        itemMap.put(item.getName(), Serializer.serialize(item));
        return this;
    }

    public Item removeItem(String name) {
        return Serializer.deserializeItemObject(itemMap.remove(name));
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

    public Set<String> getNames() {
        return itemMap.keySet();
    }
}