package space.tscg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Te
{
    public static void main(String[] args) throws JsonMappingException, JsonProcessingException
    {
        var test = """
                   {
                       "id": 128064128,
                       "id1": 3709670912,
                       "hiringPrice":200000000,
                       "cargoTotalValue":2313849856
                   }
                   """;
        
        var a = new ObjectMapper().readTree(test);
        
        a.fields().forEachRemaining(t -> System.out.println(t.getKey() + " = " + t.getValue().asToken().asByteArray()));
    }
}
