package serine.pubs.object;
//**************************************************************************
public class ObjectPub {
    //==============================================
    long objpubid = 0;
    String title = null;
    String text = null;
    long accessid = 0;
    String accessname = null;
    //==============================================
    public void setAccessName (String accessname) { this.accessname = accessname; }
    public void setTitle (String title) { this.title = title; }
    public void setText (String text) { this.text = text; }
    public void setAccessID (long accessid) { this.accessid = accessid; }
    //==============================================
    public long getID () { return objpubid; }
    public long accessID () { return accessid; }
    public String accessName () { 
        if (accessname == null) return "";
        return accessname;
    }
    public String getTitle () {
        if (title == null) return "";
        return title;
    }
    public String getText () {
        if (text == null) return "";
        return text;
    }
    //==============================================
}
//**************************************************************************
