package serine.blogging.publication;
//*************************************************************************
import methionine.AppException;
import methionine.Celaeno;
import serine.pubs.DBPubs;
import methionine.sql.SQLLockTables;
//*************************************************************************
public class PubsLambda extends QueryPubs1 {
    //*********************************************************************
    /**
     * Creates a new post record
     * @param post
     * @throws Exception 
     */
    public void createPost (PostRecord post) throws Exception {
        //-------------------------------------------------------------------
        connection = electra.masterConnection();
        setDataBase();
        //-------------------------------------------------------------------
        SQLLockTables lock = new SQLLockTables();
        lock.setDataBase(databasename);
        lock.addTable(DBPubs.PostRecord.TABLE);
        this.getExclusiveTableAccess(lock);
        //-------------------------------------------------------------------
        while (true) {
            post.postrecordid = Celaeno.getUniqueID();
            if (checkValueCount(DBPubs.PostRecord.TABLE, DBPubs.PostRecord.POSTID, post.postrecordid) == 0) break;
        }
        //-------------------------------------------------------------------
        this.insertPostRecord(post);
        this.releaseExclusiveTableAccess();
        //-------------------------------------------------------------------
    }
    //*********************************************************************
    /**
     * Returns a post record given its ID
     * @param postid
     * @param fillparts
     * @return
     * @throws AppException OBJECTNOTFOUND
     * @throws Exception 
     */
    public PostRecord getPostRecord (long postid, boolean fillparts) throws AppException, Exception {
        //----------------------------------------------------------
        connection = this.electra.slaveConnection();
        setDataBase();
        //----------------------------------------------------------
        PostRecord post = this.selectPostRecord(postid);
        //----------------------------------------------------------
        if (fillparts) 
            post.parts = this.selectPostPartsByPost(postid);
        //----------------------------------------------------------
        return post;
        //----------------------------------------------------------
    }
    //*********************************************************************
    /**
     * Returns post records
     * @return
     * @throws Exception 
     */
    public PostRecord[] getPostRecords () throws Exception {
        //----------------------------------------------------------
        connection = this.electra.slaveConnection();
        setDataBase();
        //----------------------------------------------------------
        return this.selectRecords();
    }
    //*********************************************************************
    /**
     * Adds a part to a post
     * @param part
     * @throws AppException
     * @throws Exception 
     */
    public void addPostPart (PostPart part) throws AppException, Exception {
        //-------------------------------------------------------------------
        connection = electra.masterConnection();
        setDataBase();
        //-------------------------------------------------------------------
        if (checkValueCount(DBPubs.PostRecord.TABLE, DBPubs.PostRecord.POSTID, part.postid) == 0)
            throw new AppException("Post not found", AppException.OBJECTNOTFOUND);
        //-------------------------------------------------------------------
        SQLLockTables lock = new SQLLockTables();
        lock.setDataBase(databasename);
        lock.addTable(DBPubs.PostParts.TABLE);
        this.getExclusiveTableAccess(lock);
        //-------------------------------------------------------------------
        while (true) {
            part.partid = Celaeno.getUniqueID();
            if (checkValueCount(DBPubs.PostParts.TABLE, DBPubs.PostParts.PARTID, part.partid) == 0) break;
        }
        //-------------------------------------------------------------------
        this.insertPostPart(part);
        this.releaseExclusiveTableAccess();
        //-------------------------------------------------------------------
    }
    //*********************************************************************
    /**
     * Returns the post parts given a post ID.
     * @param postid
     * @return
     * @throws Exception 
     */
    public PostPart[] getPostParts(long postid) throws Exception {
        //----------------------------------------------------------
        connection = this.electra.slaveConnection();
        setDataBase();
        //----------------------------------------------------------
        return this.selectPostPartsByPost(postid);
    }
    //*********************************************************************
}
//*************************************************************************

