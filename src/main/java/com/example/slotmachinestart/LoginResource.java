package com.example.slotmachinestart;


import com.example.slotmachinestart.bl.Database;
import com.example.slotmachinestart.bl.SlotMachineDB;
import com.example.slotmachinestart.pojo.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/*
    Klasse:  4BHIF
    @author: Daniel Götz
*/
@Slf4j
@Path("/login")
public class LoginResource {
    SlotMachineDB slotMachineDB;
    {
        try {
            slotMachineDB = SlotMachineDB.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final String JWT_SECRET = "this_secret_is_not_looong_enough";
    private String createJWT(String payload) throws JOSEException {
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),new Payload(payload));
        jwsObject.sign(new MACSigner(JWT_SECRET.getBytes()));
        System.out.println("testing123");
        return jwsObject.serialize();

    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response login(User user) throws SQLException {

     //   Database.getTheInstance().createUsers(user.getName(),user.getPwd());
       // Database.getTheInstance().getUserByName(user.getName());
        System.out.println(slotMachineDB.checkIfUserExists(user.getName()));
        if(slotMachineDB.checkIfUserExists(user.getName()))
        {
//            System.out.println("in there");
            String pwd = slotMachineDB.checkTheUserPassword(user.getName());
            pwd=pwd.replaceAll("\n","");
//            System.out.println("pwd"+pwd+"ENDE");
//            System.out.println("pwd"+user.getPwd()+"ENDE");
//            System.out.println(pwd==user.getPwd());
        if(pwd.equals(user.getPwd())) {
            try {
                return Response.ok().header("Authorization", createJWT(user.getName())).build();
            } catch (JOSEException ex) {
                ex.printStackTrace();

            }
        }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
