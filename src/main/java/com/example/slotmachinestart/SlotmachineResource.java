package com.example.slotmachinestart;


import com.example.slotmachinestart.bl.SlotMachineDB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/slot")
public class SlotmachineResource {
    private SlotMachineDB slotMachineDB;

    {
        try {
            slotMachineDB = SlotMachineDB.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @GET
    @Produces("application/json")
    public int getBalance(String name) throws SQLException {
        System.out.println("in get balance");
        System.out.println(slotMachineDB.checkIfUserExists(name));
       if(slotMachineDB.checkIfUserExists(name)){
           System.out.println("balance +"+slotMachineDB.getUserBalance(name));
           return slotMachineDB.getUserBalance(name);
       }
        return 0;
    }
//    @GET
//    @Produces("application/json")
//    public Response updateBalance(String name) throws SQLException {
//        if(slotMachineDB.checkIfUserExists(name)){
//        }
//        return Response.accepted().build();
//    }
}

