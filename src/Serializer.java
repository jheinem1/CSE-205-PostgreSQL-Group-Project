import java.util.ArrayList;
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
        System.out.println(item);
        System.out.println(itemSerialized);
        System.out.println(deserializeItemObject(itemSerialized));
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
        return out.append("]").toString();
    }

    /**
     * Serializes an iterable thingy of Item objects
     * 
     * @return JSON object (it's a string)
     */
    public static String serialize(Item[] array) {
        var out = new StringBuilder("[");
        for (var v : array)
            out.append(serialize(v)).append(", ");
        return out.append("]").toString();
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
    public static Person[] deserializeArrayObject(String json) throws InvalidJsonException {
        var arrayPattern = Pattern.compile("^[.*]$");
        var arrayMatcher = arrayPattern.matcher(json);
        if (arrayMatcher.matches()) {
            var objectPattern = Pattern.compile("{.*}");
            var objectMatcher = objectPattern.matcher(json);
            var personArray = new ArrayList<Person>();
            while (objectMatcher.group() != null) {
                personArray.add(deserializePersonObject(objectMatcher.group()));
                objectMatcher.find();
            }
            return (Person[]) personArray.toArray();
        } else {
            throw new InvalidJsonException();
        }
    }

    /**
     * Deserializes a Person object from a valid JSON object
     */
    public static Person deserializePersonObject(String json) {
        var propertyPattern = Pattern.compile("\"([^\"\\\\]+)\"\s*=\s*\"([^\"\\\\]*)\"");
        var propertyMatcher = propertyPattern.matcher(json);
        var propertyTable = new HashMap<String, String>();
        while (propertyMatcher.group() != null) {
            propertyTable.put(propertyMatcher.group(1), propertyMatcher.group(2));
            propertyMatcher.find();
        }
        if (propertyTable.get("type") != null && propertyTable.get("type") == "Person")
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
        var props = json.split(",");
        var propertyPattern = Pattern.compile("\".*([^\"\\\\]+)\"\\s*=\\s*\"([^\"\\\\]*)\".*");
        // var propertyMatcher = propertyPattern.matcher(json);
        var propertyTable = new HashMap<String, String>();
        for (var prop : props) {
            var propertyMatcher = propertyPattern.matcher(prop);
            propertyTable.put(propertyMatcher.group(1), propertyMatcher.group(2));
        }
        // while (propertyMatcher.find())
        //     propertyTable.put(propertyMatcher.group(1), propertyMatcher.group(2));
        if (propertyTable.get("type") != null && propertyTable.get("type") == "Item")
            return new Item(propertyTable.get("name"), Double.parseDouble(propertyTable.get("price")),
                    Integer.parseInt(propertyTable.get("amount")));
        else
            throw new UnsupportedOperationException("Invalid/Missing type field");
    }
}

class InvalidJsonException extends Exception {
}