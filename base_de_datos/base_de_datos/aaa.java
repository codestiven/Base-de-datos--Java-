package base_de_datos;

import java.sql.*;

public class aaa {

	public static void main(String[] args) {


        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");
            PreparedStatement pst = cn.prepareStatement("insert into eventos values(?,?,?,?,?)");

            pst.setString(1, "0");
            pst.setString(2,"aaa");
            pst.setString(3, "aa");
            pst.setString(4, "aa");
            pst.setString(5, "aa");
            pst.executeUpdate();
            
         //  txt_nombre.setText("");
         //   txt_grupo.setText("");
          //  label_status.setText("Registro exitoso.");
        }catch (Exception e){
           System.out.print("aa"); 
        }

	}

}
