package NesneProje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	public static void main(String[] args) {
		final String dbUrl="jdbc:mysql://localhost:3306/NESPRO";
		 final String userName="*****";
		 final String password="*****";
		try (Connection connection = DriverManager.getConnection(dbUrl, userName, password)) {
           if (connection != null) {
               System.out.println("Veritabanına başarıyla bağlandı!");
               Statement statement = connection.createStatement();
               //Database oluşturma sorgusu
               //String sql="CREATE DATABASE NESPRO";
               //statement.executeUpdate(sql);
       		

               // Tablo oluşturma sorgusu
               String createTableSQL = "CREATE TABLE MASALAR "
                       + "(id INT AUTO_INCREMENT PRIMARY KEY,	"
                       + "MasaAdi VARCHAR(20), "
                       + "Durum VARCHAR(20)  )";

               // Tablo oluşturma sorgusunu çalıştırma
              //statement.executeUpdate(createTableSQL);
               //tabloya veri ekleme
              String sql3 ="INSERT INTO MASALAR VALUES ('Masa1','BOS')";
             // statement.executeUpdate(sql3);

             //  System.out.println("tablosu oluşturuldu!");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

	}

}
