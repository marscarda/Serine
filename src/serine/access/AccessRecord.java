package serine.access;
//**************************************************************************
public class AccessRecord {
    //==============================================
    long accessid = 0;
    int objecttype = 0;
    long objectid = 0;
    String name = null;
    long userid = 0;
    //==============================================
    public void setObjectType (int objtype) { objecttype = objtype; }
    public void setObjectId (long objid) { objectid = objid; }
    public void setName (String name) { this.name = name; }
    public void setUserID (long userid) { this.userid = userid; }
    //==============================================
    public long getID () { return accessid; }
    //==============================================
}
//**************************************************************************
