package NesneProje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KasaYonetim {//ödemesi alınan masaları kasa tablosuna ekler
	public void insert(String MasaAdi,int Fatura )
    {
        Connection connection;
        Baglan bag = new Baglan();
        PreparedStatement statement;
        try{
            connection = bag.getConnection();
            String sql = "insert into KASA (MasaAdi,Fatura) values (?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,MasaAdi);
            statement.setInt(2,Fatura);
            statement.executeUpdate();
        }catch (SQLException exception){
            bag.showErrorMessage(exception);
        }
    }

}
