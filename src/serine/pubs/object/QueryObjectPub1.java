package serine.pubs.object;
//**************************************************************************
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import methionine.sql.SQLInsert;
import methionine.sql.SQLQueryCmd;
import methionine.sql.SQLSelect;
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
    protected ObjectPub[] selectObjectPubs () throws Exception {
        SQLQueryCmd sql = new SQLQueryCmd();
        SQLSelect select = new SQLSelect(DBPubs.ObjectPubs.TABLE);
        select.addItem(DBPubs.ObjectPubs.OBJPUBID);
        select.addItem(DBPubs.ObjectPubs.TITLE);
        select.addItem(DBPubs.ObjectPubs.TEXT);
        select.addItem(DBPubs.ObjectPubs.ACCESSID);
        //--------------------------------
        
        
        //--------------------------------
        sql.addClause(select);
        //-------------------------------------------------------
        PreparedStatement st = null;
        ResultSet rs = null;
        //-------------------------------------------------------
        try {
            st = connection.prepareStatement(sql.getText());
            sql.setParameters(st, 1);
            rs = st.executeQuery();
            List<ObjectPub> pubs = new ArrayList<>();
            ObjectPub pub;
            while (rs.next()) {
                pub = new ObjectPub();
                pub.objpubid = rs.getLong(DBPubs.ObjectPubs.OBJPUBID);
                pub.title = rs.getString(DBPubs.ObjectPubs.TITLE);
                pub.text = rs.getString(DBPubs.ObjectPubs.TEXT);
                pub.accessid = rs.getLong(DBPubs.ObjectPubs.ACCESSID);
                pubs.add(pub);
            }
            return pubs.toArray(new ObjectPub[0]);
        }
        catch (SQLException e) {
            StringBuilder msg = new StringBuilder("Failed to select Posts\n");
            msg.append(e.getMessage());
            throw new Exception(msg.toString());
        }
        finally {
            if (st != null) try {st.close();} catch(Exception e){}
            if (rs != null) try {rs.close();} catch(Exception e){}
        }
    }
    //******************************************************************
    
    //******************************************************************
}
//**************************************************************************

