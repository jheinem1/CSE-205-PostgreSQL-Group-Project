/**
 * @author James Heinemann Used to serialize Person and Item objects into JSON
 *         for easy storage
 */
// import javax."json-api-1.0-javadoc".*;

public class Serializer {

    /**
     * Serializes an iterable thingy of Person objects
     * @return JSON object (it's a string)
     */
    public static <T extends Iterable<Person>> String serialize(T array) {
        var out = new StringBuilder("[");
        for (var v : array)
            out.append(serialize(v)).append(", ");
        return out.append("]").toString();
    }

    /**
     * Serializes an individual Person object into a JSON object
     * @return JSON object (it's a string)
     */
    public static String serialize(Person person) {
        return String.format(
                "{\"type\":\"Person\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"address\":\"%s\",\"emailAddress\":\"%s\",\"position\":\"%s\"}",
                person.getfName(), person.getlName(), person.getAddress(), person.getEmailAddress(),
                person.getPosition());
    }

    public static Person deserialize(String json) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }
}