package Game.Collisions.Listeners;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     * Add hl as a listener to hit events.
     *
     * @param hl the hit listener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl the hit listener to be removed
     */
    void removeHitListener(HitListener hl);
}
