package serine.webmedia;
//**************************************************************************
public class WebMediaList {
    //**********************************************************************
    public WebMediaList() {
        //---------------------------------------------------------
        items[0] = new WebMediaObject();
        items[0].name = "blogarg";
        items[0].host = "pulsopublico.com.ar";
        items[0].lang  = BlogLang.SPANISH;
        //---------------------------------------------------------
    }
    //**********************************************************************
    private final int count = 1;
    private final WebMediaObject[] items = new WebMediaObject[count];
    //**********************************************************************
    public WebMediaObject[] itemList () { return items; }
    //======================================================================
    public WebMediaObject itemByName (String name) {
        for (int n = 0; n < count; n++) {
            if (name.equals(items[n].name))
                return items[n];
        }
        return new WebMediaObject();
    }
    //======================================================================
    public WebMediaObject itemByHost (String host) {
        for (int n = 0; n < count; n++) {
            if (host.equals(items[n].host))
                return items[n];
        }
        return new WebMediaObject();
    }
    //**********************************************************************
}
//**************************************************************************
