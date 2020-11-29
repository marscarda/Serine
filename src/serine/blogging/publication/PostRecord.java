package serine.blogging.publication;
//**************************************************************************
public class PostRecord {
    //======================================================
    long postrecordid = 0;
    String title = null;
    String sumary = null;
    int published = 0;
    PostPart[] parts = null;
    //======================================================
    public void setTitle (String title) { this.title = title; }
    public void setSumary (String sumary) { this.sumary = sumary; }
    //======================================================
    public long postID () { return postrecordid; }
    public String postTitle () {
        if (title == null) return "";
        return title;
    }
    public String postSumary () {
        if (sumary == null) return "";
        return sumary;
    }
    //------------------------------------------------------
    public PostPart[] postParts () {
        if (parts == null) return new PostPart[0];
        return parts;
    }
    //------------------------------------------------------
    public boolean isPublished () { return published != 0; }
    //======================================================
}
//**************************************************************************
