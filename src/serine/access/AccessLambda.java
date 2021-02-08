package serine.access;
//**************************************************************************
import methionine.AppException;
import methionine.Celaeno;
import methionine.sql.SQLLockTables;
//**************************************************************************
public class AccessLambda extends QueryAccess1 {
    //******************************************************************
    /**
     * Creates an access to a given object
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
    /**
     * Return an access record by its name.
     * @param accessname
     * @return
     * @throws AppException
     * @throws Exception 
     */
    public AccessRecord getAccessRecordByName (String accessname) throws AppException, Exception {
        //--------------------------------------------------------------
        connection = electra.slaveConnection();
        setDataBase();
        //--------------------------------------------------------------
        return this.selectAccessRecordByName(accessname);
        //--------------------------------------------------------------
    }
    //******************************************************************
    /**
     * Returns an access list for a given object
     * @param objtype
     * @param objid
     * @return
     * @throws Exception 
     */
    public AccessRecord[] getAccessListByObject (int objtype, long objid) throws Exception {
        //--------------------------------------------------------------
        connection = electra.slaveConnection();
        setDataBase();
        //--------------------------------------------------------------
        return this.selectAccessRecordsByObject(objtype, objid);
        //--------------------------------------------------------------
    }
    //******************************************************************
    /**
     * Destroy a specific access.
     * @param accessid
     * @throws Exception 
     */
    public void destroyAccess (long accessid) throws Exception {
        //--------------------------------------------------------------
        connection = electra.masterConnection();
        setDataBase();
        //--------------------------------------------------------------
        this.deleteAccessRecord(accessid);
    }
    //******************************************************************
}
//**************************************************************************
