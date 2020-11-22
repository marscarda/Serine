package serine.blogging.publication;
//*************************************************************************
public class PostPart {
    //*********************************************************
    long partid = 0;
    long postid = 0;
    int partType = 0;
    //=========================================================
    public long getID () { return partid; }
    //=========================================================
    public void setType (int parttype) { partType = parttype; }
    public int partType () { return partType; }
    //=========================================================
    public void setPostID (long postid) { this.postid = postid; }
    public long postID () { return postid; }
    //*********************************************************
    //Text piece
    String text = null;
    public void setText (String text) { this.text = text; }
    public String getText () {
        if (text == null) return "";
        return text;
    }
    //*********************************************************
    public static final int PARAGRAPH = 1;
    public static final int PICTURE = 2;
    //*********************************************************
}
//*************************************************************************