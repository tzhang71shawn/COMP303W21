import java.io.File;

public class Tests {
	public static void main(String[] args) {
				Library lib = new Library();
			
			
			File aa = new File("/Users/zhangtian/eclipse-workspace/Mulan.mp4");
			File bb = new File("/Users/zhangtian/eclipse-workspace/LionKing.mp4");
			File cc = new File("/Users/zhangtian/eclipse-workspace/Mulan.mp4");
			File dd = new File("/Users/zhangtian/eclipse-workspace/LionKing.mp4");
			
			Movie a1 = new Movie(aa,"Mulan", Language.ENGLISH, "Disney");
			Movie a2 = new Movie(bb, "Mulan", Language.ENGLISH, "Disney");
			Movie a3 = new Movie(cc, "007", Language.ENGLISH, "WarnerBrothers");
			Movie a4 = new Movie(dd, "008", Language.FRENCH, "WarnerBrothers");
			WatchList a = new WatchList("WatchList 1");
		    WatchList b = new WatchList("WatchList 2");
		    WatchList c = new WatchList("WatchList 3");
		    File qq = new File("/Users/zhangtian/eclipse-workspace/StarWar.mp4");
		    File ee = new File("/Users/zhangtian/eclipse-workspace/BaBai.mp4");
		    File ww = new File("/Users/zhangtian/eclipse-workspace/LionKing.mp4");
		    Movie num2 = new Movie(qq,"StarWar", Language.ENGLISH, "Lucasfilm");
			Movie num3 = new Movie(ee,"BaBai", Language.ENGLISH, "Huayi Brothers");
			Movie num4 = new Movie(ww,"LionKing",Language.ENGLISH, "Disney");
			a.addWatchable(num2);
			a.addWatchable(num3);
			a.addWatchable(num4);
			b.addWatchable(num2);
			b.addWatchable(num3);
			b.addWatchable(num4);
			c.addWatchable(num3);
			c.addWatchable(num2);
			c.addWatchable(num4);
			TVShow show1= new TVShow("show",Language.ENGLISH,"WarnerBrothers");
			
			//question 1
			
			LanguageFilterStrategy l1 = new LanguageFilterStrategy(Language.ENGLISH);
			LanguageFilterStrategy l2 = new LanguageFilterStrategy(Language.FRENCH);
			filterOne s1= new filterOne("WarnerBrothers");
			
			CompositeFilter enhanced = new CompositeFilter(l1);
			//enhanced.addFilter(l2);
			enhanced.addFilter(s1);
			// this filter using  l1 and s1 
			enhancedFilterAND fil = new enhancedFilterAND(enhanced);
			
			//this filter using fil or l2
			CompositeFilter en = new CompositeFilter(fil);
			en.addFilter(l2);
			enhancedFilterOR fil2 = new enhancedFilterOR(en);
			
			
			WatchList question1 = lib.generateWatchList("question1", fil2);
			System.out.println(question1);
			//question 2
			show1.setPrototype();
			
}
}