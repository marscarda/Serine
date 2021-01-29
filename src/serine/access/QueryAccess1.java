package serine.access;
//**************************************************************************
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import methionine.sql.SQLCondition;
import methionine.sql.SQLDelete;
import methionine.sql.SQLInsert;
import methionine.sql.SQLQueryCmd;
import methionine.sql.SQLSelect;
import methionine.sql.SQLWhere;
//**************************************************************************
public class QueryAccess1 extends QueryAccessTabs {
    //******************************************************************
    /**
     * Inserts a new Access Record into the Access table.
     * @param record
     * @throws Exception 
     */
    protected void insertAccessRecord (AccessRecord record) throws Exception {
        SQLInsert insert = new SQLInsert(DBAccess.ObjectAccess.TABLE);
        insert.addValue(DBAccess.ObjectAccess.ACCESSID, record.accessid);
        insert.addValue(DBAccess.ObjectAccess.OBJECTTYPE, record.objecttype);
        insert.addValue(DBAccess.ObjectAccess.OBJECTID, record.objectid);
        insert.addValue(DBAccess.ObjectAccess.USERID , record.userid);
        insert.addValue(DBAccess.ObjectAccess.NAME, record.name);
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert.getText());
            insert.setParameters(st, 1);
            st.execute();            
        }
        catch (SQLException e) {
            StringBuilder msg = new StringBuilder("Failed to insert access \n");
            msg.append(e.getMessage());
            throw new Exception(msg.toString());
        }
        finally {
            if (st != null) try {st.close();} catch(Exception e){}
        }        
    }
    //******************************************************************
    protected AccessRecord selectAccessRecord (long accessid) {
        
        //DO SOMETHING HERE        
        
        return null;
    }
    //******************************************************************
    /**
     * Selects and returns access records given the object type and id
     * @param objtype
     * @param objid
     * @return 
     * @throws java.lang.Exception 
     */
    protected AccessRecord[] selectAccessRecordsByObject (int objtype, long objid) throws Exception {
        //-------------------------------------------------------
        SQLQueryCmd sql = new SQLQueryCmd();
        SQLSelect select = new SQLSelect(DBAccess.ObjectAccess.TABLE);
        select.addItem(DBAccess.ObjectAccess.ACCESSID);
        select.addItem(DBAccess.ObjectAccess.NAME);
        select.addItem(DBAccess.ObjectAccess.OBJECTTYPE);
        select.addItem(DBAccess.ObjectAccess.OBJECTID);
        select.addItem(DBAccess.ObjectAccess.USERID);
        //-------------------------------------------------------
        SQLWhere whr = new SQLWhere();
        whr.addCondition(new SQLCondition(DBAccess.ObjectAccess.OBJECTTYPE, "=", objtype));
        whr.addCondition(new SQLCondition(DBAccess.ObjectAccess.OBJECTID, "=", objid));
        //-------------------------------------------------------
        sql.addClause(select);
        sql.addClause(whr);
        //-------------------------------------------------------
        PreparedStatement st = null;
        ResultSet rs = null;
        //-------------------------------------------------------
        try {
            st = connection.prepareStatement(sql.getText());
            sql.setParameters(st, 1);
            rs = st.executeQuery();
            List<AccessRecord> records = new ArrayList<>();
            AccessRecord record;
            while (rs.next()) {
                record = new AccessRecord();
                record.accessid = rs.getLong(DBAccess.ObjectAccess.ACCESSID);
                record.name = rs.getString(DBAccess.ObjectAccess.NAME);
                record.objecttype = rs.getInt(DBAccess.ObjectAccess.OBJECTTYPE);
                record.objectid = rs.getLong(DBAccess.ObjectAccess.OBJECTID);
                record.userid = rs.getLong(DBAccess.ObjectAccess.USERID);
                records.add(record);
            }
            return records.toArray(new AccessRecord[0]);
        }
        catch (SQLException e) {
            StringBuilder msg = new StringBuilder("Failed to select Access Records By Object\n");
            msg.append(e.getMessage());
            throw new Exception(msg.toString());
        }
        finally {
            if (st != null) try {st.close();} catch(Exception e){}
            if (rs != null) try {rs.close();} catch(Exception e){}
        }
    }
    //******************************************************************
    /**
     * deletes an access record from the database.
     * @param accessid
     * @throws Exception 
     */
    protected void deleteAccessRecord (long accessid) throws Exception {
        SQLQueryCmd sql = new SQLQueryCmd();
        SQLDelete delete = new SQLDelete(DBAccess.ObjectAccess.TABLE);
        SQLWhere whr = new SQLWhere();
        whr.addCondition(new SQLCondition(DBAccess.ObjectAccess.ACCESSID, "=", accessid));
        sql.addClause(delete);
        sql.addClause(whr);
        //-------------------------------------------------------
        PreparedStatement st = null;
        ResultSet rs = null;
        //-------------------------------------------------------
        try {
            st = connection.prepareStatement(sql.getText());
            sql.setParameters(st, 1);
            st.execute();            
        }
        catch (SQLException e) {
            StringBuilder msg = new StringBuilder("Failed to delete access \n");
            msg.append(e.getMessage());
            throw new Exception(msg.toString());
        }
        finally {
            if (st != null) try {st.close();} catch(Exception e){}
        }        
        //-------------------------------------------------------
    }
    //******************************************************************
}
//**************************************************************************
