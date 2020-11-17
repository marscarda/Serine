package serine.blogging.publication;
//*************************************************************************
public class PostPieceParagraph extends PostPiece {
    //**********************************************************************
    public PostPieceParagraph() { childtype = PARAGRAPH; }
    //**********************************************************************
    String text = null;
    public String getText () {
        if (text == null) return "";
        return text;
    }
    //**********************************************************************
}
//*************************************************************************
