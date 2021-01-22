package serine.access;
//**************************************************************************
import java.sql.PreparedStatement;
import java.sql.SQLException;
import methionine.Alcyone;
import methionine.sql.MySQLEngine;
import methionine.sql.SQLCreateTable;
//**************************************************************************
public class QueryAccessTabs extends Alcyone {
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
        if (!checkTableExists(DBAccess.ObjectAccess.TABLE, tables)) createPublicationRecords();
        //===================================================================
    }
    //***********************************************************************
    private void createPublicationRecords () throws Exception {
        //-------------------------------------------------------------------
        SQLCreateTable create = new SQLCreateTable(DBAccess.ObjectAccess.TABLE);
        create.setEngine(MySQLEngine.INNODB);
        create.addField(DBAccess.ObjectAccess.ACCESSID, "BIGINT NOT NULL");
        create.addField(DBAccess.ObjectAccess.OBJECTTYPE, "INTEGER NOT NULL DEFAULT 0");
        create.addField(DBAccess.ObjectAccess.USERID, "BIGINT NOT NULL");
        create.addField(DBAccess.ObjectAccess.NAME, "VARCHAR (100) NOT NULL");
        //-------------------------------------------------------------------
        PreparedStatement st = null;
        this.setDataBase();
        try {
            st = connection.prepareStatement(create.getText());
            st.execute();
        }
        catch (SQLException e) {
            StringBuilder err = new StringBuilder("Failed to create ");
            err.append(DBAccess.ObjectAccess.TABLE);
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
