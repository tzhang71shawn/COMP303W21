import java.util.Optional;

public interface Command {
    Optional<Watchable> execute();
    void undo();
    Optional<Watchable> redo();
}
