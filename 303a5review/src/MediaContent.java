import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// abstract class because shouldn't have a MediaContent object alone
public abstract class MediaContent {
    protected String aTitle;
    protected Language aLanguage;
    protected String aStudio;

    protected Map<String, String> aInfo;
    protected Map<String, String> aCast;

    // allow unique watchlist association
    protected Optional<WatchList> aWatchList;

    public MediaContent(String pTitle, Language pLanguage, String pStudio) {
        assert pTitle != null && pLanguage != null && pStudio != null;
        aTitle = pTitle;
        aLanguage = pLanguage;
        aStudio = pStudio;
        aWatchList = Optional.empty();
        aInfo = new HashMap<>();
        aCast = new HashMap<>();
    }

    public void setWatchList(WatchList pWatchList) {
        assert pWatchList != null;
        aWatchList = Optional.of(pWatchList);
    }

    public void resetWatchList() {
        aWatchList = Optional.empty();
    }

    public Language getLanguage() {
        return aLanguage;
    }

    public String getStudio() {
        return aStudio;
    }

    public String getTitle() {
        return aTitle;
    }

    public String setCast(String pCharacter, String pActor) {
        if (pActor == null) {
            return aCast.remove(pCharacter);
        }
        else {
            return aCast.put(pCharacter, pActor);
        }
    }

    public String getCast(String pCharacter) {
        return aCast.get(pCharacter);
    }

    public String getInfo(String pKey) {
        return aInfo.get(pKey);
    }

    public boolean hasInfo(String pKey) {
        return aInfo.containsKey(pKey);
    }

    public String setInfo(String pKey, String pValue) {
        if (pValue == null) {
            return aInfo.remove(pKey);
        }
        else {
            return aInfo.put(pKey, pValue);
        }
    }
}
