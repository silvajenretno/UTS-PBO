package Entitas;

import Database.database;

/**
 *
 * @author Silva_Jen_Retno
 */
public class pengguna extends database {
    public String ID_User;
    public String Password;
    
    public pengguna(String ID_User, String Password){
        this.ID_User = ID_User;
        this.Password = Password;
    }
}


