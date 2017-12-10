package game.architecture;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstraction over all entity classes. Assigns and manages id's.
 */
@SuppressWarnings("rawtypes")
public abstract class Entity {

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private static Map<Class, Integer> map = new HashMap<Class, Integer>();
    private long id;

    /* ========== PUBLIC ========== */
    public Entity(Class clazz) {
        Integer integer = map.get(clazz);

        if (integer == null) {
            integer = -1;
        }

        id = integer + 1;
        map.put(clazz, integer + 1);
    }

    /* ========== PROPERTIES ========== */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}