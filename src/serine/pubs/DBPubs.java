package serine.pubs;
//*************************************************************************
public class DBPubs {
    //*********************************************************************
    public static class PostRecord {
        public static final String TABLE = "postrecords";
        public static final String POSTID = "postid";
        public static final String TITLE = "title";
        public static final String SUMARY = "sumary";
        public static final String PUBLISHED = "published";
    }
    //*********************************************************************
    public static class PostParts {
        public static final String TABLE = "postparts";
        public static final String PARTID = "partid";
        public static final String PARTTYPE = "parttype";
        public static final String POSTID = "postid";
        public static final String TEXT = "text";
    }
    //*********************************************************************
    public static class ObjectPubs {
        public static final String TABLE = "objectpubs";
        public static final String TITLE = "title";
        public static final String TEXT = "text";
        public static final String ACCESSID = "accessid";
    }
    //*********************************************************************
}
//*************************************************************************
