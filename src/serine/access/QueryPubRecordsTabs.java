package serine.access;
//**************************************************************************
import java.sql.PreparedStatement;
import java.sql.SQLException;
import methionine.Alcyone;
import methionine.sql.MySQLEngine;
import methionine.sql.SQLCreateTable;
//**************************************************************************
public class QueryPubRecordsTabs extends Alcyone {
    //***********************************************************************
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
        if (!checkTableExists(DBPublications.TABLE, tables)) createPublicationRecords();
        //===================================================================
    }
    //***********************************************************************
    private void createPublicationRecords () throws Exception {
        //-------------------------------------------------------------------
        SQLCreateTable create = new SQLCreateTable(DBPublications.TABLE);
        create.setEngine(MySQLEngine.INNODB);
        create.addField(DBPublications.PUBLICATIONID, "BIGINT NOT NULL");
        create.addField(DBPublications.OBJECTTYPE, "INTEGER NOT NULL DEFAULT 0");
        create.addField(DBPublications.USERID, "BIGINT NOT NULL");
        create.addField(DBPublications.NAME, "VARCHAR (100) NOT NULL");
        //-------------------------------------------------------------------
        PreparedStatement st = null;
        this.setDataBase();
        try {
            st = connection.prepareStatement(create.getText());
            st.execute();
        }
        catch (SQLException e) {
            StringBuilder err = new StringBuilder("Failed to create ");
            err.append(DBPublications.TABLE);
            err.append(" table\n");
            err.append(e.getMessage());
            throw new Exception(err.toString());
        }
        finally {
            try { if (st != null) st.close(); } catch (Exception e) {}
        }
        //-------------------------------------------------------------------
    }
    //***********************************************************************
}
//***************************************************************************
