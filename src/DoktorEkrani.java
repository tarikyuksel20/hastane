import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DoktorEkrani extends JFrame {
    private static DoktorEkrani doktorEkrani = null;
    private DoktorAnaSayfa doktorAnaSayfa = null;
    private JLabel doktor;
    private JTextField tcTextField;
    private JButton girişYapButton;
    private JPasswordField şifreTextField;
    private JPanel mainPanel;

    private DoktorEkrani doktorEkrani1;

    public static DoktorEkrani getInstance() {

        if (doktorEkrani == null) {
            doktorEkrani = new DoktorEkrani();
            return doktorEkrani;
        } else {
            return doktorEkrani;
        }

    }


    public DoktorEkrani() {

        this.add(mainPanel);
        this.setSize(new Dimension(400, 250));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Doktor Ekranı");
        mainPanel.setVisible(true);
        manageDoktorgirişyapbutton();

        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection con = DriverManager
                            .getConnection("jdbc:mariadb://localhost:3306/hastane?user=root&password=Assasin-164");
                    String doktor_tc = tcTextField.getText();
                    String doktor_şifre = şifreTextField.getText();
                    Statement stm = con.createStatement();


                    String sql = "select * from hastane.doktor where doktor_tc='"+doktor_tc+"'and doktor_şifre='"+doktor_şifre+"'";
                    ResultSet rs = stm.executeQuery(sql);
                    if(rs.next()){
                        //dispose();
                        System.out.printf("Working ");
                        System.out.println(rs.getString("doktor_ad"));
                        doktorAnaSayfa = DoktorAnaSayfa.getInstance();
                        doktorAnaSayfa.setVisible(true);
                        mainPanel.setVisible(false);

                    }


                }
                catch (Exception ee){
                    System.out.println("Mistake");
                    System.out.println(ee.getMessage());
                    //System.out.println(ee.getMessage());
                }
            }
        });
    }


    private void manageDoktorgirişyapbutton() {
        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tcTextField.getText().length()==0 || şifreTextField.getText().length()==0){
                    JOptionPane.showMessageDialog(null,"Lütfen tüm alanları doldurun");
                }
            }
        });
    }


}