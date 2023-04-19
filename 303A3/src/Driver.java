import java.io.File;

public class Driver {
		public static void main(String[] args) {
			Library lib = new Library();;
			lib.setName("My Library");
			lib.setEmailID("shawnz@gmail.com");
			System.out.println(lib.getName()+"  "+lib.getEmailID());
			File aa = new File("/Users/zhangtian/eclipse-workspace/Mulan.mp4");
			File bb = new File("/Users/zhangtian/eclipse-workspace/LionKing.mp4");
			Movie.get(aa,"Mulan", Language.ENGLISH, "Disney");
			Movie.get(bb, "Mulan", Language.ENGLISH, "Disney");
			Movie.get("Mulan");
			
			WatchList a = new WatchList("WatchList 1");
		    WatchList b = new WatchList("WatchList 2");
		    WatchList c = new WatchList("WatchList 3");
		    File qq = new File("/Users/zhangtian/eclipse-workspace/StarWar.mp4");
		    File ee = new File("/Users/zhangtian/eclipse-workspace/BaBai.mp4");
		    File ww = new File("/Users/zhangtian/eclipse-workspace/LionKing.mp4");
		    Movie num2 = Movie.get(qq,"StarWar", Language.ENGLISH, "Lucasfilm");
			Movie num3 = Movie.get(ee,"BaBai", Language.ENGLISH, "Huayi Brothers");
			Movie num4 = Movie.get(ww,"LionKing",Language.ENGLISH, "Disney");
			a.addWatchable(num2);
			a.addWatchable(num3);
			a.addWatchable(num4);
			b.addWatchable(num2);
			b.addWatchable(num3);
			b.addWatchable(num4);
			c.addWatchable(num3);
			c.addWatchable(num2);
			c.addWatchable(num4);
			System.out.println(a.equals(b)+" "+a.equals(c)+" "+b.equals(c));
			
			
		
}
}
