package serine.blogging.publication;
//*************************************************************************
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import serine.blogging.DBPubs;
import methionine.sql.SQLInsert;
import methionine.sql.SQLQueryCmd;
import methionine.sql.SQLSelect;
//*************************************************************************
public class QueryPubs1 extends PubsTables {
    //*********************************************************************
    /**
     * Insert a new post record into the database.
     * @param post
     * @throws Exception 
     */
    protected void insertPostRecord (PostRecord post) throws Exception {
        SQLInsert insert = new SQLInsert(DBPubs.PostRecord.TABLE);
        insert.addValue(DBPubs.PostRecord.POSTID, post.postrecordid);
        insert.addValue(DBPubs.PostRecord.TITLE, post.title);
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert.getText());
            insert.setParameters(st, 1);
            st.execute();            
        }
        catch (SQLException e) {
            StringBuilder msg = new StringBuilder("Failed to insert new Post record \n");
            msg.append(e.getMessage());
            throw new Exception(msg.toString());
        }
        finally {
            if (st != null) try {st.close();} catch(Exception e){}
        }        
    }
    //*********************************************************************
    /**
     * Selects and returns the posts
     * @return
     * @throws Exception 
     */
    protected PostRecord[] selectRecords () throws Exception {
        SQLQueryCmd sql = new SQLQueryCmd();
        SQLSelect select = new SQLSelect(DBPubs.PostRecord.TABLE);
        select.addItem(DBPubs.PostRecord.POSTID);
        select.addItem(DBPubs.PostRecord.TITLE);
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
            List<PostRecord> posts = new ArrayList<>();
            PostRecord post;
            while (rs.next()) {
                post = new PostRecord();
                post.postrecordid = rs.getLong(DBPubs.PostRecord.POSTID);
                post.title = rs.getString(DBPubs.PostRecord.TITLE);
                posts.add(post);
            }
            return posts.toArray(new PostRecord[0]);
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
    //*********************************************************************
}
//*************************************************************************

