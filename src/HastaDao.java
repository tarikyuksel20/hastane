import java.sql.ResultSet;
import java.sql.SQLException;

public interface HastaDao {

    boolean hastaEkle(
            String hastaIsmi,
            String hastaTc,
            String hastaSifre,
            String hastaGsm,
            Cinsiyet hastaCinsiyet
    ) throws SQLException;


    boolean hastagiriş(
            String hasta_tc,
            String hasta_şifre
    ) throws SQLException;


    ResultSet hasta_al(String hasta_tc);



}
