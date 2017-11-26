package engine.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.system.StarSystem;

/**
 * Parses input into JSON format.
 */
public final class JsonSystemParser {

    /* ========== PUBLIC ========== */
    public static StarSystem parseStarSystem(String filepath) throws JsonParseException, JsonMappingException,
            IOException {

        StarSystem system = MAPPER.readValue(new File("res/" + filepath + ".json"), StarSystem.class);
        return system;
    }

    public static void saveStarSystem(String filepath, StarSystem system) throws JsonParseException,
            JsonMappingException, IOException {

        MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
        MAPPER.writeValue(new File("res/" + filepath + ".json"), system);
    }

    /* ========== PRIVATE ========== */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonSystemParser() {
    }
}
