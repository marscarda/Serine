package serine.pubs.object;
//**************************************************************************
import methionine.Celaeno;
import methionine.sql.SQLLockTables;
import serine.pubs.DBPubs;
//**************************************************************************
public class ObjectPubLambda extends QueryObjectPub1 {
    //******************************************************************
    /**
     * Creates a new object publication.
     * @param pub
     * @throws Exception 
     */
    public void createObjectPub (ObjectPub pub) throws Exception {
        //--------------------------------------------------------------
        connection = electra.masterConnection();
        setDataBase();
        //--------------------------------------------------------------
        SQLLockTables lock = new SQLLockTables();
        lock.setDataBase(databasename);
        lock.addTable(DBPubs.ObjectPubs.TABLE);
        this.getExclusiveTableAccess(lock);
        //--------------------------------------------------------------
        while (true) {
            pub.objpubid  = Celaeno.getUniqueID();
            if (checkValueCount(DBPubs.ObjectPubs.TABLE, DBPubs.ObjectPubs.OBJPUBID, pub.objpubid) == 0) break;
        }
        //--------------------------------------------------------------
        this.insertObjectPub(pub);
        this.releaseExclusiveTableAccess();
        //--------------------------------------------------------------
    }
    //******************************************************************
}
//**************************************************************************
