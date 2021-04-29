import java.util.HashMap;

public class IDandPasswords {

    HashMap<String,String> logininfo = new HashMap<String,String>();

    IDandPasswords(){

        logininfo.put("admin","password");
        logininfo.put("spf2962","password");
        logininfo.put("JHabermas","password");
    }

    public HashMap getLoginInfo(){
        return logininfo;
    }
}