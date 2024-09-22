package base_de_datos;

import java.sql.*;

public class aaa2 {

	public static void main(String[] args) {


        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");

            
            String sql = "SELECT * FROM eventos";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
          
            ResultSetMetaData rsmd = rs.getMetaData();
            int cantidadC = rsmd.getColumnCount();
            
            
            
            while(rs.next()) {
            	Object[]  filas = new Object[cantidadC];
            	
            	for(int i = 0 ; i< cantidadC ; i++) {
            		filas[i] = rs.getObject(i+1);
            	}
            }
            
        }catch (Exception e){
           System.out.print("aa"); 
        }

	}

}
