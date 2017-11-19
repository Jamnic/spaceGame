package game.architecture;

import java.util.ArrayList;
import java.util.List;

/**
 * Grants access to all registered {@link Entity} objects.
 *
 * @param <T>
 */
public abstract class Repository<T extends Entity> {

    /* ========== PUBLIC ========== */
    public T getById(long id) {

        if (id < 0) {
            return null;
        }

        for (T t : entities) {
            if (t.getId() == (id)) {
                return t;
            }
        }

        return null;
    }

    public void add(List<T> t) {
        this.entities.addAll(t);
    }

    public void add(T t) {
        this.entities.add(t);
    }

    /* ========== PRIVATE ========== */
    private List<T> entities = new ArrayList<T>();
}