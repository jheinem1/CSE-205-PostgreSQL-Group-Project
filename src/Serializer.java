/**
 * @author James Heinemann Used to serialize Person and Item objects into JSON
 *         for easy storage
 */
// import javax."json-api-1.0-javadoc".*;

public class Serializer {

    public static <T extends Iterable<Person>> String serialize(T array) {
        var out = new StringBuilder("[");
        for (var v : array)
            out.append(serialize(v)).append(", ");
        return out.append("]").toString();
    }

    public static String serialize(Person person) {
        return String.format(
                "{\"type\":\"Person\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"address\":\"%s\",\"emailAddress\":\"%s\",\"position\":\"%s\"}",
                person.getfName(), person.getlName(), person.getAddress(), person.getEmailAddress(),
                person.getPosition());
    }
}