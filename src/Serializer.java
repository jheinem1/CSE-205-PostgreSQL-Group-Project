import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @author James Heinemann Used to serialize Person and Item objects into JSON
 *         for easy storage
 */

public class Serializer {

    public static void main(String[] args) {
        var item = new Item("Milk", 1.50, 4);
        var itemSerialized = serialize(item);
        System.out.println(item); // Milk:	$1.50, 4
        System.out.println(itemSerialized); // {"type":"Item","name":"Milk","price":"1.50","amount":"4"}
        System.out.println(deserializeItemObject(itemSerialized)); // Milk:	$1.50, 4

        var items = new Item[] {item, new Item("Eggs", 5.00, 8)};
        var itemsSerialized = serialize(items);
        System.out.println(Arrays.toString(items)); // [Milk:	$1.50, 4, Eggs:	$5.00, 8]
        System.out.println(itemsSerialized); // [{"type":"Item","name":"Milk","price":"1.50","amount":"4"}, {"type":"Item","name":"Eggs","price":"5.00","amount":"8"}, ]
        System.out.println(Arrays.toString(deserializeItemArrayObject(itemsSerialized)));
    }

    /**
     * Serializes an iterable thingy of Person objects
     * 
     * @return JSON object (it's a string)
     */
    public static String serialize(Person[] array) {
        var out = new StringBuilder("[");
        for (var v : array)
            out.append(serialize(v)).append(", ");
        return out.deleteCharAt(out.length() - 1).append("]").toString();
    }

    /**
     * Serializes an iterable thingy of Item objects
     * 
     * @return JSON object (it's a string)
     */
    public static String serialize(Item[] array) {
        var out = new StringBuilder("[");
        for (var v : array)
            out.append(serialize(v)).append(",");
        return out.deleteCharAt(out.length() - 1).append("]").toString();
    }

    /**
     * Serializes an individual Person object into a JSON object
     * 
     * @return JSON object (it's a string)
     */
    public static String serialize(Person person) {
        return String.format(
                "{\"type\":\"Person\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"address\":\"%s\",\"emailAddress\":\"%s\",\"position\":\"%s\",\"username\":\"%s\",\"password\":\"%s\"}",
                person.getfName(), person.getlName(), person.getAddress(), person.getEmailAddress(),
                person.getPosition(), person.getUsername(), person.getPassword());
    }

    /**
     * Serializes an individual Item object into a JSON object
     * 
     * @return JSON object (it's a string)
     */
    public static String serialize(Item item) {
        return String.format(
                "{\"type\":\"Item\",\"name\":\"%s\",\"price\":\"%.2f\",\"amount\":\"%d\"}",
                item.getName(), item.getPrice(), item.getQuantity());
    }

    /**
     * Deserializes a JSON array of Person objects
     */
    public static Person[] deserializePersonArrayObject(String json) {
        var objectPattern = Pattern.compile("\\{[^{}]*}");
        var objectMatcher = objectPattern.matcher(json);
        var personArray = new ArrayList<Person>();
        while (objectMatcher.find())
            personArray.add(deserializePersonObject(objectMatcher.group()));
        return personArray.toArray(new Person[0]);
    }

    /**
     * Deserializes a JSON array of Item objects
     */
    public static Item[] deserializeItemArrayObject(String json) {
        var objectPattern = Pattern.compile("\\{[^{}]*}");
        var objectMatcher = objectPattern.matcher(json);
        var itemArray = new ArrayList<Item>();
        while (objectMatcher.find())
            itemArray.add(deserializeItemObject(objectMatcher.group()));
        return itemArray.toArray(new Item[0]);
    }

    /**
     * Deserializes a Person object from a valid JSON object
     */
    public static Person deserializePersonObject(String json) {
        var propertyPattern = Pattern.compile("\"([^\"\\\\]+)\"\\s*:\\s*\"([^\"\\\\]*)\"");
        var propertyMatcher = propertyPattern.matcher(json);
        var propertyTable = new HashMap<String, String>();
        while (propertyMatcher.find())
            propertyTable.put(propertyMatcher.group(1), propertyMatcher.group(2));
        if (propertyTable.get("type") != null && propertyTable.get("type").equals("Person"))
            return new Person(propertyTable.get("firstName"), propertyTable.get("lastName"),
                    propertyTable.get("address"), propertyTable.get("emailAddress"), propertyTable.get("position"),
                    propertyTable.get("username"), propertyTable.get("password"));
        else
            throw new UnsupportedOperationException("Invalid/Missing type field");
    }

    /**
     * Deserializes an Item object from a valid JSON object
     */
    public static Item deserializeItemObject(String json) {
        var propertyPattern = Pattern.compile("\"([^\"\\\\]+)\"\\s*:\\s*\"([^\"\\\\]*)\"");
        var propertyMatcher = propertyPattern.matcher(json);
        var propertyTable = new HashMap<String, String>();
         while (propertyMatcher.find())
             propertyTable.put(propertyMatcher.group(1), propertyMatcher.group(2));
        if (propertyTable.get("type") != null && propertyTable.get("type").equals("Item"))
            return new Item(propertyTable.get("name"), Double.parseDouble(propertyTable.get("price")),
                    Integer.parseInt(propertyTable.get("amount")));
        else
            throw new UnsupportedOperationException("Invalid/Missing type field");
    }
}