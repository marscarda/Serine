package serine.blogging.publication;
//*************************************************************************
import java.sql.PreparedStatement;
import java.sql.SQLException;
import methionine.Alcyone;
import serine.blogging.DBPubs;
import methionine.sql.MySQLEngine;
import methionine.sql.SQLCreateTable;
//*************************************************************************
public class PubsTables extends Alcyone {
    //**********************************************************************
    public void ensureTables() throws Exception {
        String[] tables;// = getTables();
        connection = electra.masterConnection();
        this.setDataBase();
        try { tables =  getTables(); }
        catch (Exception e) {
            StringBuilder err = new StringBuilder("Failed to check tables existence in database " + databasename + "\n");
            err.append(e.getMessage());
            throw new Exception(err.toString());
        }
        //===================================================================
        if (!checkTableExists(DBPubs.PostRecord.TABLE, tables)) createPostRecords();
        //-------------------------------------------------------------------
        //===================================================================
        /*
        //===================================================================
        if (!checkTableExists(DBSurvey.PViewCand.TABLE, tables)) createCandidates();
        //-------------------------------------------------------------------
        if (!checkTableExists(DBSurvey.ActionSet.TABLE, tables)) createQForms();
        if (!checkTableExists(DBSurvey.ActionItemPointers.TABLE, tables)) createSurveyItems();
        //===================================================================
        if (!checkTableExists(DBSurvey.Samples.TABLE, tables)) createSamples();
        //===================================================================
        if (!checkTableExists(DBSurvey.ResponseSubjects.TABLE, tables)) createResponseObjects();
        if (!checkTableExists(DBSurvey.Reactions.TABLE, tables)) createReactions();
        //===================================================================
        */
    }
    //**********************************************************************
    private void createPostRecords () throws Exception {
        //-------------------------------------------------------------------
        SQLCreateTable create = new SQLCreateTable(DBPubs.PostRecord.TABLE);
        create.setEngine(MySQLEngine.INNODB);
        create.addField(DBPubs.PostRecord.POSTID, "BIGINT NOT NULL");
        create.addField(DBPubs.PostRecord.TITLE, "VARCHAR (100) NOT NULL");
        //-------------------------------------------------------------------
        PreparedStatement st = null;
        this.setDataBase();
        try {
            st = connection.prepareStatement(create.getText());
            st.execute();
        }
        catch (SQLException e) {
            StringBuilder err = new StringBuilder("Failed to create ");
            err.append(DBPubs.PostRecord.TABLE);
            err.append(" table\n");
            err.append(e.getMessage());
            throw new Exception(err.toString());
        }
        finally {
            try { if (st != null) st.close(); } catch (Exception e) {}
        }
        //-------------------------------------------------------------------
        /*
        create.addField(DBSurvey.PViewCand.CODE, "VARCHAR (50) NOT NULL");
        create.addField(DBSurvey.PViewCand.CATEGORY, "VARCHAR (50) NOT NULL");
        create.addField(DBSurvey.PViewCand.LABEL, "VARCHAR (100) NOT NULL");
        */
        //-------------------------------------------------------------------
    }
    //**********************************************************************
    
    
    //**********************************************************************
}
//*************************************************************************
