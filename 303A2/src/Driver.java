import java.io.File;

public class Driver {
		public static void main() {
			Library lib = new Library();
			Movie num1 = new Movie(/Users/zhangtian/eclipse-workspace/Mulan.mp4, "Mulan", "English", "Disney");
			Movie num2 = new Movie(/Users/zhangtian/eclipse-workspace/StarWar.mp4, "StarWar", "English", "Lucasfilm");
			Movie num3 = new Movie(/Users/zhangtian/eclipse-workspace/BaBai.mp4, "BaBai", "Chinese", "Huayi Brothers");
			Movie num4 = new Movie(/Users/zhangtian/eclipse-workspace/LionKing.mp4, "LionKing", "English", "Disney");
			lib.addMovie(num1);
			lib.addMovie(num2);
			lib.addMovie(num3);
			lib.addMovie(num4);
			
			Episode e1=new (/Users/zhangtian/downloads/e1.mp4, [sqbqjq123]);
			Episode e2=new (/Users/zhangtian/downloads/e1.mp4, [sqbqjq124]);
			Episode e3=new (/Users/zhangtian/downloads/e1.mp4, [sqbqjq125]);
			Episode e4=new (/Users/zhangtian/downloads/e1.mp4, [sqbqjq126]);
			Episode e5=new (/Users/zhangtian/downloads/e1.mp4, [sqbqjq127]);
			TVShows n = new TVShows("StandUpComedy");
			n.addEpisode(e1);
			n.addEpisode(e2);
			n.addEpisode(e3);
			n.addEpisode(e4);
			n.addEpisode(e5);
			lib.addTVShows(n);
			
			
			WatchList a = lib.generatorByLanANDStudio("English", "Disney");
			WatchList b = lib.RandomTVShows(3);
			for(Movie mov : a.getMovies()) {
			System.out.println(mov.getTitle()+" \n");
			}
			for(Episode epi : b.getEpisodes()) {
				System.out.println(String.valueOf(epi.getSequentialNumber())+" \n");
				}
		}
}
