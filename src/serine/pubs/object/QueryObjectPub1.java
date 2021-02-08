package serine.pubs.object;
//**************************************************************************
import java.sql.PreparedStatement;
import java.sql.SQLException;
import methionine.sql.SQLInsert;
import serine.pubs.DBPubs;
import serine.pubs.PubsTables;
//**************************************************************************
public class QueryObjectPub1 extends PubsTables {
    //******************************************************************
    /**
     * inserts an object publication record in the database.
     * @param pub
     * @throws Exception 
     */
    protected void insertObjectPub (ObjectPub pub) throws Exception {
        SQLInsert insert = new SQLInsert(DBPubs.ObjectPubs.TABLE);
        insert.addValue(DBPubs.ObjectPubs.OBJPUBID, pub.objpubid);
        insert.addValue(DBPubs.ObjectPubs.TITLE, pub.title);
        insert.addValue(DBPubs.ObjectPubs.TEXT, pub.text);
        insert.addValue(DBPubs.ObjectPubs.ACCESSID, pub.accessid);
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert.getText());
            insert.setParameters(st, 1);
            st.execute();            
        }
        catch (SQLException e) {
            StringBuilder msg = new StringBuilder("Failed to insert object pub \n");
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

