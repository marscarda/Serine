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
        if (!checkTableExists(DBPubs.PostParts.TABLE, tables)) createPostParts();
        //===================================================================
    }
    //**********************************************************************
    private void createPostRecords () throws Exception {
        //------------------------------------------------------------------
        SQLCreateTable create = new SQLCreateTable(DBPubs.PostRecord.TABLE);
        create.setEngine(MySQLEngine.INNODB);
        create.addField(DBPubs.PostRecord.POSTID, "BIGINT NOT NULL");
        create.addField(DBPubs.PostRecord.TITLE, "VARCHAR (100) NOT NULL");
        //------------------------------------------------------------------
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
        //------------------------------------------------------------------
    }
    //**********************************************************************
    private void createPostParts () throws Exception {
        //------------------------------------------------------------------
        SQLCreateTable create = new SQLCreateTable(DBPubs.PostParts.TABLE);
        create.setEngine(MySQLEngine.INNODB);
        create.addField(DBPubs.PostParts.PARTTYPE, "INTEGER NOT NULL DEFAULT 0");
        create.addField(DBPubs.PostParts.POSTID, "BIGINT NOT NULL");
        create.addField(DBPubs.PostParts.TEXT, "VARCHAR (100) NOT NULL");
        //------------------------------------------------------------------
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
        //------------------------------------------------------------------
    }
    //**********************************************************************
}
//*************************************************************************
