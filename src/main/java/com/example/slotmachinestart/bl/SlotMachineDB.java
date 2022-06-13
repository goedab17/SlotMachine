package com.example.slotmachinestart.bl;

import com.example.slotmachinestart.db.DB_Access;
import com.example.slotmachinestart.db.DB_CachedConnection;
import com.example.slotmachinestart.db.DB_Database;
import com.example.slotmachinestart.db.DB_PrepStat;
import com.example.slotmachinestart.pojo.User;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.slotmachinestart.db.DB_PrepStat.getUserByID;
import static org.eclipse.persistence.config.ParameterDelimiterType.Hash;

/*
    Klasse:  4BHIF
    @author: Daniel Götz
*/
@Data
public class SlotMachineDB {

    public DB_Access db_access;
    public DB_Database db_database;
    private Connection connection;
    private DB_CachedConnection cache;
    private static SlotMachineDB theInstance;
    private PreparedStatement getUserByName;
    private PreparedStatement getUserBalance;
    private PreparedStatement updateUserBalance;
    private PreparedStatement checkUserPassword;
    private PreparedStatement checkUserDuplicate;


    public SlotMachineDB() throws ClassNotFoundException, SQLException {
        db_access = new DB_Access();
        db_access.connect();
        db_database = db_access.getDatabase();
        connection = db_database.getConnection();
    }

    public static SlotMachineDB getInstance() throws SQLException, ClassNotFoundException {

        if(theInstance == null)
        {
            theInstance = new SlotMachineDB();
        }
        return theInstance;
    }
    public int getUserBalance(String name) throws SQLException {
        if (getUserBalance == null) {
            getUserBalance = connection.prepareStatement(DB_PrepStat.getUserBalance.sqlValue);
        }
        getUserBalance.setString(1,name);
        //cache.releaseStatement(getGamesByID); //TODO ?
        ResultSet rs = getUserBalance.executeQuery();
        rs.next();
        return rs.getInt("balance");
    }
    public boolean checkIfUserExists(String name) throws SQLException {
        if(checkUserDuplicate == null)
        {
            checkUserDuplicate = connection.prepareStatement(DB_PrepStat.checkUserDuplicate.sqlValue);
        }
        checkUserDuplicate.setString(1,name);
        ResultSet rs = checkUserDuplicate.executeQuery();
        rs.next();
        if(rs.getInt("count")==0)
        {
            return false;
        }
        return true;
    }
    public String checkTheUserPassword(String name) throws SQLException {

        if(checkUserPassword == null)
        {
            checkUserPassword = connection.prepareStatement(DB_PrepStat.checkUserPassword.sqlValue);
        }
        checkUserPassword.setString(1,name);
        ResultSet rs = checkUserPassword.executeQuery();
        rs.next();
        return rs.getString(1);
    }
    public boolean updateBalance(String name,int balance) throws SQLException {
        if(updateUserBalance == null)
        {
            updateUserBalance = connection.prepareStatement(DB_PrepStat.updateBalanceName.sqlValue);
        }
        updateUserBalance.setString(2,name);
        updateUserBalance.setInt(1,balance);
        int rs = updateUserBalance.executeUpdate();
        return rs == 1;
    }
    public static void main(String[] args) {
        try {
            SlotMachineDB slotMachineDB = getInstance();
            System.out.println("Connected?");
            //System.out.println(blackjackDB.getPlayerByID(1));
//            blackjackDB.getUserByID2(1);
//            Connection connection = slotMachineDB.getConnection();
//            System.out.println(connection);
////            DBplayer userByID = blackjackDB.getUserDataByID(0);
//            System.out.println(slotMachineDB.checkIfUserExists("admin"));
//            System.out.println(slotMachineDB.getUserBalance("crook"));
//            System.out.println(slotMachineDB.updateBalance("crook",20));
//            System.out.println(slotMachineDB.getUserBalance("crook"));
            System.out.println(slotMachineDB.checkTheUserPassword("mrrich"));
            //            connection.close();
//            System.out.println("Closed Connection");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    }

