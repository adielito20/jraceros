package utp.edu.pe.jracero.service;

import utp.edu.pe.jracero.model.Trabajador;
import utp.edu.pe.jracero.model.enums.Rol;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    public static Trabajador isValidTrabajador(String correo, String pwd) throws SQLException, NamingException, IOException {
        String query = "SELECT * FROM trabajador WHERE correo = ? AND contraseña = ?";
        try (Connection conn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, "java:/MariaDB");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, correo);
            ps.setString(2, md5(pwd));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Trabajador(
                            rs.getInt("id_trabajador"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            Rol.valueOf(rs.getString("rol")),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            rs.getString("contraseña")
                    );
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static String md5(String data) throws IOException {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            MessageDigest msg = (MessageDigest) md.clone();
            msg.update(data.getBytes());
            return byteArrayToHex(msg.digest());
        } catch (CloneNotSupportedException | NoSuchAlgorithmException e) {
            return data;
        }
    }

    /*
    * Link: https://stackoverflow.com/questions/9655181/java-convert-a-byte-array-to-a-hex-string
    * Nota: Metodo altetnativo para JDK17, pero se debe tener cuidado con tener este entorno activado
    * HexFormat hex = HexFormat.of();
    * hex.formatHex(someByteArray)
    * */
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
