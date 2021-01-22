package serine.access;
//**************************************************************************
import java.sql.PreparedStatement;
import java.sql.SQLException;
import methionine.sql.SQLInsert;
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
        insert.addValue(DBAccess.ObjectAccess.USERID , record.userid);
        insert.addValue(DBAccess.ObjectAccess.NAME, record.name);
        insert.addValue(DBAccess.ObjectAccess.OBJECTTYPE, record.objecttype);
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

    //******************************************************************
}
//**************************************************************************
