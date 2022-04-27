import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HastaAnaSayfa extends JFrame {

    Connection connection = DriverManager
            .getConnection("jdbc:mariadb://localhost:3306/hastane?user=root&password=Assasin-164");

    HastaDaoImpl hastaDaoImpl = new HastaDaoImpl(connection);

    private static HastaAnaSayfa hastaAnaSayfa = null;
    private JLabel label;
    private JButton randevugörüntülebutton;
    private JButton randevualbutton;
    private JPanel mainpanel;
    private String hastaTc;

    private String hasta_ad;

    private Cinsiyet hasta_cinsiyet;



    public static HastaAnaSayfa getInstance(
            String hasta_tc
    ) throws SQLException {
        if(hastaAnaSayfa==null){
            return new HastaAnaSayfa(hasta_tc);
        }else return hastaAnaSayfa;

    }

    public HastaAnaSayfa(
            String hasta_tc
    ) throws SQLException {

        this.hastaTc = hasta_tc;
        System.out.println(hasta_tc);
        this.setVisible(true);
        this.add(mainpanel);
        this.setSize(300,300);
        this.setTitle("Hasta Anasayfa");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        managehastabilgileri();

        randevualbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void managehastabilgileri() throws SQLException {
        ResultSet rs = hastaDaoImpl.hasta_al(hastaTc);
        if(rs.next()){
            hasta_ad=rs.getString("hasta_adı");
            hasta_cinsiyet=Cinsiyet.valueOf(rs.getString("hasta_cinsiyet"));

            if (hasta_cinsiyet==Cinsiyet.E){
                label.setText("Hoş geldiniz: "+hasta_ad+" Bey");

            }
            else{
                label.setText("Hoş geldiniz: "+hasta_ad+" Hanım");
            }

        }

    }



}
