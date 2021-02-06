package serine.blogging.publication;
//*************************************************************************
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import methionine.AppException;
import methionine.sql.SQLCondition;
import serine.pubs.DBPubs;
import methionine.sql.SQLInsert;
import methionine.sql.SQLQueryCmd;
import methionine.sql.SQLSelect;
import methionine.sql.SQLWhere;
import serine.pubs.PubsTables;
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
        insert.addValue(DBPubs.PostRecord.SUMARY, post.sumary);
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
     * Selects a Post Record given its ID
     * @param postid
     * @return
     * @throws AppException
     * @throws Exception 
     */
    protected PostRecord selectPostRecord (long postid) throws AppException, Exception {
        SQLQueryCmd sql = new SQLQueryCmd();
        SQLSelect select = new SQLSelect(DBPubs.PostRecord.TABLE);
        select.addItem(DBPubs.PostRecord.POSTID);
        select.addItem(DBPubs.PostRecord.TITLE);
        select.addItem(DBPubs.PostRecord.SUMARY);
        select.addItem(DBPubs.PostRecord.PUBLISHED);
        SQLWhere whr = new SQLWhere();
        whr.addCondition(new SQLCondition(DBPubs.PostRecord.POSTID, "=", postid));
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
            if (!rs.next())
                throw new AppException("Post not found", AppException.OBJECTNOTFOUND);
            PostRecord post;
            post = new PostRecord();
            post.postrecordid = rs.getLong(DBPubs.PostRecord.POSTID);
            post.title = rs.getString(DBPubs.PostRecord.TITLE);
            post.sumary = rs.getString(DBPubs.PostRecord.SUMARY);
            post.published = rs.getInt(DBPubs.PostRecord.PUBLISHED);
            return post;
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
        select.addItem(DBPubs.PostRecord.SUMARY);
        select.addItem(DBPubs.PostRecord.PUBLISHED);
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
                post.sumary = rs.getString(DBPubs.PostRecord.SUMARY);
                post.published = rs.getInt(DBPubs.PostRecord.PUBLISHED);
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
    /**
     * Inserts a new post part into the database
     * @param part
     * @throws Exception 
     */
    protected void insertPostPart (PostPart part) throws Exception {
        SQLInsert insert = new SQLInsert(DBPubs.PostParts.TABLE);
        insert.addValue(DBPubs.PostParts.PARTID, part.partid);
        insert.addValue(DBPubs.PostParts.PARTTYPE, part.partType);
        insert.addValue(DBPubs.PostParts.POSTID, part.postid);
        insert.addValue(DBPubs.PostParts.TEXT, part.text);
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert.getText());
            insert.setParameters(st, 1);
            st.execute();            
        }
        catch (SQLException e) {
            StringBuilder msg = new StringBuilder("Failed to insert new Post part \n");
            msg.append(e.getMessage());
            throw new Exception(msg.toString());
        }
        finally {
            if (st != null) try {st.close();} catch(Exception e){}
        }        
    }
    //*********************************************************************
    /**
     * Selects and returns post parts given the post id
     * @param postid
     * @return
     * @throws Exception 
     */
    protected PostPart[] selectPostPartsByPost (long postid) throws Exception {
        //-------------------------------------------------------
        SQLQueryCmd sql = new SQLQueryCmd();
        SQLSelect select = new SQLSelect(DBPubs.PostParts.TABLE);
        select.addItem(DBPubs.PostParts.PARTID);
        select.addItem(DBPubs.PostParts.PARTTYPE);
        select.addItem(DBPubs.PostParts.POSTID);
        select.addItem(DBPubs.PostParts.TEXT);
        SQLWhere whr = new SQLWhere();
        whr.addCondition(new SQLCondition(DBPubs.PostParts.POSTID, "=", postid));
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
            List<PostPart> parts = new ArrayList<>();
            PostPart part;
            while (rs.next()) {
                part = new PostPart();
                part.partid = rs.getLong(DBPubs.PostParts.PARTID);
                part.partType = rs.getInt(DBPubs.PostParts.PARTTYPE);
                part.postid = rs.getLong(DBPubs.PostParts.POSTID);
                part.text = rs.getString(DBPubs.PostParts.TEXT);
                parts.add(part);
            }
            return parts.toArray(new PostPart[0]);
        }
        catch (SQLException e) {
            StringBuilder msg = new StringBuilder("Failed to select Post Parts\n");
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

