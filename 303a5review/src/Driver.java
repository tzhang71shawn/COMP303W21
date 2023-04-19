import java.io.File;
import java.util.Optional;

public class Driver {
    public static void main(String[] args) {
        File f1 = new File("C:\\Users\\tomsa\\Videos\\coolmovie.mp4");
        File f2 = new File("C:\\Users\\tomsa\\Videos\\coolmovie2.mp4");
        File f3 = new File("C:\\Users\\tomsa\\Videos\\coolmovie3.mp4");

        Movie m1 = new Movie(f1, "Movie1", Language.FRENCH, "Warner Bros inc");
        Movie m2 = new Movie(f2, "Movie2", Language.LATIN, "Goldman");
        Movie m3 = new Movie(f3, "Movie3", Language.ENGLISH, "Goldman");
        Movie m4 = new Movie(f3, "Movie4", Language.ENGLISH, "Goldman");

        TVShow t1 = new TVShow("Friends", Language.ENGLISH, "Warner Bros inc");
        TVShow t2 = new TVShow("HIMYM", Language.KLINGON, "Studio2");
        TVShow t3 = new TVShow("B99", Language.LATIN, "Warner Bros inc");

        WatchList w1 = new WatchList("Cool wl");

        // access MediaContent setter methods through inheritance
        m1.setCast("Tom", "Brad Pitt");
        m2.setInfo("is Cool?", "probably yeah");

        w1.addWatchable(m1);
        w1.addWatchable(m2);
        w1.addWatchable(m3);
        w1.addWatchable(m4);

        // Question 1 testing / presentation

        Optional<Watchable> lastWatched = w1.lastWatched();
        // nothing watched -> null
        System.out.println(lastWatched);

        w1.next().watch();
        lastWatched = w1.lastWatched();

        // m1 watched
        System.out.println(lastWatched);

        w1.next().watch();
        lastWatched = w1.lastWatched();

        // m2 watched
        System.out.println(lastWatched);

        // does not rely on next()
        for (Watchable w : w1) {
            w.watch();
            lastWatched = w1.lastWatched();
            System.out.println(lastWatched);
        }


        // Question 3 Testing / presentation

        w1.setName("A first name");
        w1.setName("A cool new name");

        System.out.println(w1.getName()); // last call: cool new name
        w1.undo();
        System.out.println(w1.getName()); // first call: a first name
        w1.redo();
        System.out.println(w1.getName()); // redo: cool new name

        // no more actions to redo: nothing happens
        w1.redo();
        System.out.println(w1.getName()); // redo: cool new name
        w1.redo();
        System.out.println(w1.getName()); // redo: cool new name

        w1.reset();
        System.out.println(w1.getRemainingCount());
        w1.next().watch();

        Watchable redoneAction = w1.redo().get();
        redoneAction.watch();

        // no more actions to perform: nothing happens
        w1.undo();
        w1.undo();
        w1.undo();

        // watch first movie again
        w1.next().watch();

        w1.reset();

        System.out.println("RESET");

        w1.setName("First");
        w1.setName("Second");
        w1.undo(); // name == "First"
        w1.next().watch(); // empties the redo stack
        w1.undo();
        w1.redo(); // re watches the last item
        System.out.println(w1.redo()); // doesn't redo the name change

        System.out.println(w1.getName());




    }
}
