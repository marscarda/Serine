package serine.bloggin;
//*************************************************************************
public abstract class PostPiece {
    //*********************************************************
    int childtype = 0;
    //=========================================================
    public int childType () { return childtype; }
    //*********************************************************
    public static final int PARAGRAPH = 1;
    public static final int PICTURE = 2;
    //*********************************************************
}
//*************************************************************************