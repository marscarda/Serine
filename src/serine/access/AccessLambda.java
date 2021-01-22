package serine.access;
//**************************************************************************
import methionine.Celaeno;
import methionine.sql.SQLLockTables;
//**************************************************************************
public class AccessLambda extends QueryAccess1 {
    //******************************************************************
    /**
     * 
     * @param record
     * @throws Exception 
     */
    public void createAccess (AccessRecord record) throws Exception {
        //--------------------------------------------------------------
        connection = electra.masterConnection();
        setDataBase();
        //--------------------------------------------------------------
        SQLLockTables lock = new SQLLockTables();
        lock.setDataBase(databasename);
        lock.addTable(DBAccess.ObjectAccess.TABLE);
        this.getExclusiveTableAccess(lock);
        //--------------------------------------------------------------
        while (true) {
            record.accessid = Celaeno.getUniqueID();
            if (checkValueCount(DBAccess.ObjectAccess.TABLE, DBAccess.ObjectAccess.ACCESSID, record.accessid) == 0) break;
        }
        //--------------------------------------------------------------
        this.insertAccessRecord(record);
        this.releaseExclusiveTableAccess();
        //--------------------------------------------------------------
    }
    //******************************************************************
}
//**************************************************************************
