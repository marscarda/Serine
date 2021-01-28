package serine.access;
//**************************************************************************
public class AccessRecord {
    //==============================================
    long accessid = 0;
    int objecttype = 0;
    long objectid = 0;
    String name = null;
    String username = null;
    long userid = 0;
    //==============================================
    public void setObjectType (int objtype) { objecttype = objtype; }
    public void setObjectId (long objid) { objectid = objid; }
    public void setName (String name) { this.name = name; }
    public void setUserName (String username) { this.username = username; }
    public void setUserID (long userid) { this.userid = userid; }
    //==============================================
    public long getID () { return accessid; }
    public int getObjectType () { return objecttype; }
    public long getObjectID () { return objectid; }
    public String getName () {
        if (name == null) return "";
        return name;
    }
    public String getUserName () {
        if (username == null) return "";
        return username;
    }
    public long getUserID () { return userid; }
    public boolean isPublic () { return userid == 0; }
    //==============================================
    public static final int OBJTYPESAMPLE = 1;
    //==============================================
}
//**************************************************************************
