package NesneProje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SiparisYonetim {
	
	 public void insert(String MasaAdi,String YemekAdi,int Fiyat )//alınan siparişleri siparişler tablosuna ekler
	    {
	        Connection connection;
	        Baglan bag = new Baglan();
	        PreparedStatement statement;
	        try{
	            connection = bag.getConnection();
	            String sql = "insert into SİPARİSLAR (MasaAdi,YemekAdi,Fiyat) values (?,?,?)";
	            statement = connection.prepareStatement(sql);
	            statement.setString(1,MasaAdi);
	            statement.setString(2,YemekAdi);
	            statement.setInt(3,Fiyat);
	            statement.executeUpdate();
	        }catch (SQLException exception){
	            bag.showErrorMessage(exception);
	        }
	    }
	
	 
	 

	 public void deleteOrder(String MasaAdi)//ödeme aldıktan sonra siparişler tablosundan siparişi siler
	    {
	        Connection connection;
	        Baglan bag = new Baglan();
	        PreparedStatement statement;
	        try{
	            connection= bag.getConnection();
	            statement = connection.prepareStatement("delete from SİPARİSLAR where MasaAdi =?");
	            statement.setString(1,MasaAdi);
	            statement.executeUpdate();
	        }catch (SQLException exception){
	            bag.showErrorMessage(exception);
	        }
	    }
	 
	 public void getPaid(String MasaAdi) {//masaya ait siparişler kısmından fiyatları çekerek faturayı hesaplar
		 int fatura = 0;
		Connection connection;
	        Baglan bag = new Baglan();
	        PreparedStatement Statement;
	        ResultSet resultSet;
	        try{
	            connection = bag.getConnection();
	            Statement = connection.prepareStatement("select  id,MasaAdi,YemekAdi,Fiyat from SİPARİSLAR where MasaAdi=?");
	            Statement.setString(1,MasaAdi);
	            resultSet = Statement.executeQuery();
	            List<Integer> fiyatlar = new ArrayList<>();
	            while (resultSet.next()){
	    			//System.out.print(resultSet.getString("YemekAdi")+"      ");
	    			//System.out.println(resultSet.getInt("Fiyat")+" ");
	    			int fiyat = resultSet.getInt("Fiyat");
	    	        fiyatlar.add(fiyat);
	              
	            }
	            for (int fiyat : fiyatlar) {
	                fatura += fiyat;
	            }
	            if(fatura!=0) {//fatura sıfır ise kasa tablosuna veri gönderilmesin
	            KasaYonetim pay1 = new KasaYonetim();
	            pay1.insert(MasaAdi,fatura);
	            }
	           // System.out.println("Toplam Fiyat: " + fatura);
	            
	            //masa durumunu ödeme aldıktan sonra bos olarak yazar
	            deleteOrder(MasaAdi);
	            updateTable(MasaAdi,"BOS");
	            
	            
	        }catch (SQLException exception){
	            bag.showErrorMessage(exception);
	        }
	    }
	 public void updateTable(String MasaAdi, String yeniDurum) {//masa durumunu güncelleme
		    Connection connection;
		    Baglan bag = new Baglan();
		    PreparedStatement statement;
		    try {
		        connection = bag.getConnection();
		        String sql = "UPDATE MASALAR SET Durum = ? WHERE MasaAdi = ?";
		        statement = connection.prepareStatement(sql);
		        statement.setString(1, yeniDurum);
		        statement.setString(2, MasaAdi);
		        statement.executeUpdate();
		    } catch (SQLException exception) {
		        bag.showErrorMessage(exception);
		    }
		}
	 

}
