import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public abstract class ItemHolder {

    private final HashMap<String, String> itemMap;

    public ItemHolder() {
        itemMap = new HashMap<String, String>();
    }

    public ItemHolder(HashMap<String, String> itemMap) {
        this.itemMap = itemMap;
    }

    public ItemHolder(Item[] items) {
        itemMap = new HashMap<String, String>();
        addItems(items);
    }

    public ItemHolder clear() {
        itemMap.clear();
        return this;
    }

    public Item getItemByName(String name) {
        return itemMap.get(name) == null ? null : Serializer.deserializeItemObject(itemMap.get(name));
    }

    public ItemHolder addItem(Item item) {
        if (getItemByName(item.getName()) == null)
            itemMap.put(item.getName(), Serializer.serialize(item));
        else
            updateItem(item.getName(), item.getPrice(), getItemByName(item.getName()).getQuantity() + item.getQuantity());
        return this;
    }

    public ItemHolder addItems(Item[] items) {
        for (var item : items)
            addItem(item);
        return this;
    }

    public Item removeItem(String name) {
        return Serializer.deserializeItemObject(itemMap.remove(name));
    }

    public Item removeItem(String name, int quantity) {
        var item = getItemByName(name);
        if (item.getQuantity() - quantity > 0) {
            updateItem(name, item.getPrice(), item.getQuantity() - quantity);
            return item;
        } else
            return Serializer.deserializeItemObject(itemMap.remove(name));
    }

    public Item[] removeItems(String[] names) {
        var out = new Item[names.length];
        var i = 0;
        for (var name : names)
            out[i++] = removeItem(name);
        return out;
    }

    public Item[] removeItems(String[] names, int quantity) {
        var out = new Item[names.length];
        var i = 0;
        for (var name : names)
            out[i++] = removeItem(name, quantity);
        return out;
    }

    public ItemHolder updateItem(String name, double price, int quantity) {
        if (getItemByName(name) != null)
            itemMap.put(name, Serializer.serialize(new Item(name, price, quantity)));
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

    public Item[] getValues() {
        var out = new Item[itemMap.size()];
        var i = 0;
        for (var serializedItem : itemMap.values())
            out[i++] = Serializer.deserializeItemObject(serializedItem);
        return out;
    }

    public HashMap<String, String> getMap() {
        return itemMap;
    }
}