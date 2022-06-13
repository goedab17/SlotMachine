package com.example.slotmachinestart.db;

import lombok.Data;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
/*
    Klasse:  4BHIF
    @author: Daniel Götz
*/
@Data
@Getter
public class DB_Properties {

    public static Properties dbProperties = new Properties();

    private String db_driver = "org.postgresql.Driver";
    private String db_url = "jdbc:postgresql://localhost:5432/slotmachine";
    private String db_user = "postgres";
    private String db_password = "postgres";


    public static String getProperty(String key) {
        return dbProperties.getProperty(key);
    }
}