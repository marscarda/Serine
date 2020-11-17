package serine.blogging.publication;
//**************************************************************************
public class PostRecord {
    //======================================================
    long postrecordid = 0;
    String title = null;
    //======================================================
    public void setTitle (String title) { this.title = title; }
    //======================================================
    public long postID () { return postrecordid; }
    public String postTitle () {
        if (title == null) return "";
        return title;
    }
    //======================================================
}
//**************************************************************************
