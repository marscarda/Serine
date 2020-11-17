package serine.blogging.publication;
//*************************************************************************
import methionine.Celaeno;
import serine.blogging.DBPubs;
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
}
//*************************************************************************

