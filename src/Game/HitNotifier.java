package Game;

/**
 * The {@code HitNotifier} interface should be implemented by objects that can be hit
 * and want to notify registered listeners about hit events.
 */
public interface HitNotifier {

    /**
     * Adds a {@link HitListener} to the list of listeners to be notified of hit events.
     *
     * @param hl the listener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a {@link HitListener} from the list of listeners to be notified of hit events.
     *
     * @param hl the listener to be removed.
     */
    void removeHitListener(HitListener hl);
}
