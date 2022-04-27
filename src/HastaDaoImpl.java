import javax.swing.*;
import java.sql.*;

public class HastaDaoImpl implements HastaDao{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public HastaDaoImpl(Connection connector){
        this.connection = connector;
    }


    @Override
    public boolean hastaEkle(
            String hastaIsmi, String hastaTc, String hastaSifre, String hastaGsm, Cinsiyet hastaCinsiyet
    ) throws SQLException {

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO hastane.hasta (hasta_adı,hasta_tc,hasta_şifre,hasta_gsm,hasta_cinsiyet)" +
                            "VALUES (?,?,?,?,?)"
            );
            preparedStatement.setString(1,hastaIsmi);
            preparedStatement.setString(2,hastaTc);
            preparedStatement.setString(3,hastaSifre);
            preparedStatement.setString(4,hastaGsm);
            preparedStatement.setString(5,hastaCinsiyet.name());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean hastagiriş(String hasta_tc, String hasta_şifre) throws SQLException {
        statement = connection.createStatement();
        ResultSet rs =
        statement.executeQuery(
                "select * from hastane.hasta where hasta_tc='"+hasta_tc+"'and hasta_şifre='"+hasta_şifre+"'"
        );
        if(rs.next()){
            return true;
        }

        return false;
    }

    @Override
    public ResultSet hasta_al(String hasta_tc) {

        try {

            statement = connection.createStatement();
            ResultSet rs =
                    statement.executeQuery(
                            "select * from hastane.hasta where hasta_tc='"+hasta_tc+"'"
                    );
            return  rs;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
