package com.example.slotmachinestart.db;

/*
    Klasse:  4BHIF
    @author: Daniel Götz
*/
public enum DB_PrepStat {
    getUserByID("SELECT name,balance FROM public.\"balance\" WHERE playerid = 1;"),
    getUserBalance("SELECT balance FROM public.\"balance\" WHERE name = ?;"),
    checkUserDuplicate("SELECT count(*) FROM public.\"balance\" WHERE name = ?;"),
    updateBalanceName("UPDATE public.\"balance\" SET balance = ? WHERE name = ?"),
    checkUserPassword("SELECT pwd FROM public.\"balance\" WHERE name = ?;");

    DB_PrepStat(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public final String sqlValue;


}