package utp.edu.pe.jracero.util;

import java.util.ResourceBundle;

public class AppConfig {
    static ResourceBundle rb = ResourceBundle.getBundle("app");

    public static String getDatasource(){
        return rb.getString("datasource");
    }

    public static String getErrorLogFile(){
        return rb.getString("error_log");
    }

    public static String getCnxString(){
        return rb.getString("cnx_string");
    }
}
