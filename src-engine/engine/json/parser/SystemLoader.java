package engine.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import model.system.StarSystem;

public final class SystemLoader {

    public static StarSystem loadSystemFromFile(String filepath) {

        try {
            return JSONParser.parseStarSystem(filepath);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    /* ========== PRIVATE ========== */
    private SystemLoader() {
    }
}