package game.engine.thread;

import game.graphics.graphics.GraphicsPane;

import java.util.LinkedList;
import java.util.List;

import static game.architecture.Constants.SECOND;
import static game.architecture.Constants.TICKS_PER_SECOND;

/**
 * Class that is responsible for tick counting.
 * <br><br>
 * <b>Class responsibilities</b>
 * <ul>
 * <li>Counting and performing ticks in infinite loop.</li>
 * <li>Adding new {@liobjects</li>
 * </ul>
 *
 * @author Jamnic
 */
public class GameTickThread implements Runnable {

    /* ========== PRIVATE ========== */
    private List<game.engine.thread.Tickable> tickables = new LinkedList<Tickable>();
    private GraphicsPane graphicsPane;

    /* ========== PUBLIC ========== */
    public GameTickThread(GraphicsPane graphicsPane) {
        this.graphicsPane = graphicsPane;
    }

    @Override
    public void run() {
        long previous = System.currentTimeMillis();
        while (true) {
            long current = System.currentTimeMillis();

            if (current - previous > (SECOND / TICKS_PER_SECOND)) {
                previous = current;
                for (game.engine.thread.Tickable tickable : tickables) {
                    tickable.tick();
                }
            }

            graphicsPane.repaint();
        }
    }

    public void addTickable(game.engine.thread.Tickable tickable) {
        tickables.add(tickable);
    }
}