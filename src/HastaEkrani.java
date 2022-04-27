import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HastaEkrani extends JFrame{

    Connection connection = DriverManager
            .getConnection("jdbc:mariadb://localhost:3306/hastane?user=root&password=Assasin-164");

    //Singleton Pattern
    private static HastaEkrani hastaEkrani = null;
    private HastaAnaSayfa hastaAnaSayfa = null;

    HastaDaoImpl hastaDaoımpl = new HastaDaoImpl(connection);

    ButtonGroup buttonGroup = new ButtonGroup();

    ButtonGroup entrybuttonGroup = new ButtonGroup();
    private JPanel mainPanel;
    private JTextField isimTextField;
    private JTextField tcTextField;
    private JPasswordField passwordField;
    private JTextField gsmTextField;
    private JRadioButton kadınRadioButton;
    private JRadioButton erkekRadioButton;
    private JButton actionButton;
    private JRadioButton girişradiobutton;
    private JRadioButton kayıtolradiobutton;
    private JLabel adsoyadlabel;
    private JLabel tclabel;
    private JLabel şifrelabel;
    private JLabel gsmlabel;

    private boolean isFemale;
    private boolean isSignIn;


    public static HastaEkrani getInstance() throws SQLException {

        if (hastaEkrani == null){
            hastaEkrani = new HastaEkrani();
            return hastaEkrani;
        }else{
            return hastaEkrani;
        }

    }


    public HastaEkrani() throws SQLException {

        this.add(mainPanel);
        this.setSize(new Dimension(400,250));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        manageKayıtolbuton();
        isFemale = kadınRadioButton.isSelected();
        isSignIn = girişradiobutton.isSelected();
        entrybuttonGroup.add(girişradiobutton);
        entrybuttonGroup.add(kayıtolradiobutton);
        manageEntryRadioGroup();
    }
    private void manageKayıtolbuton(){
        buttonGroup.add(kadınRadioButton);
        buttonGroup.add(erkekRadioButton);
        actionButton.addActionListener(new ActionListener() {
            //DAO Pattern
            @Override
            public void actionPerformed(ActionEvent e) {
                kadınRadioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (kadınRadioButton.isSelected()){
                            isFemale = true;
                        }else{
                            isFemale = false;
                        }
                    }
                });

                erkekRadioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (erkekRadioButton.isSelected()){
                            isFemale = false;
                        }else{
                            isFemale = true;
                        }
                    }
                });

                if (isSignIn){
                    try {
                        girişyap();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    kayıtol();
                }


            }
        });
    }


    private void girişyap() throws SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:mariadb://localhost:3306/hastane?user=root&password=Assasin-164");
        String hasta_tc = tcTextField.getText();
        String hasta_şifre = passwordField.getText();
        if(hastaDaoımpl.hastagiriş(
                hasta_tc,
                hasta_şifre
        )){
            HastaAnaSayfa.getInstance(hasta_tc);
        }
        else {
            JOptionPane.showMessageDialog(null,"Bir hata oluştu");
        }

    }
    private void kayıtol(){

        String hastaAdı = isimTextField.getText();
        String hastaTc = tcTextField.getText();
        String hasta_şifre = passwordField.getText();
        String hasta_gsm = gsmTextField.getText();
        Cinsiyet hastaCinsiyet;
        if (isFemale){
            hastaCinsiyet = Cinsiyet.K;
        }else{
            hastaCinsiyet = Cinsiyet.E;
        }

        if(hastaAdı.length()==0||
                hastaTc.length()==0 ||
                hasta_şifre.length()==0 ||
                hasta_gsm.length()==0){

            JOptionPane.showMessageDialog(null,"Lütfen tüm alanları doldurun");

        }else {

            try {
                boolean isSuccessful = hastaDaoımpl.hastaEkle(
                        hastaAdı,
                        hastaTc,
                        hasta_şifre,
                        hasta_gsm,
                        hastaCinsiyet
                );

                if (isSuccessful){
                    //JOptionPane.showMessageDialog(null, "Başarıyla Kaydoldunuz");
                    hastaAnaSayfa=HastaAnaSayfa.getInstance(hastaTc);


                }else{
                    JOptionPane.showMessageDialog(null,"Bir hata oldu");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }


    private void manageEntryRadioGroup(){

        if (isSignIn){

            actionButton.setText("Giriş Yap");
            adsoyadlabel.setVisible(false);
            gsmlabel.setVisible(false);
            kadınRadioButton.setVisible(false);
            erkekRadioButton.setVisible(false);
            isimTextField.setVisible(false);
            gsmTextField.setVisible(false);

        }

        girişradiobutton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (girişradiobutton.isSelected()){

                    actionButton.setText("Giriş Yap");
                    adsoyadlabel.setVisible(false);
                    gsmlabel.setVisible(false);
                    kadınRadioButton.setVisible(false);
                    erkekRadioButton.setVisible(false);
                    isimTextField.setVisible(false);
                    gsmTextField.setVisible(false);
                    isSignIn = true;


                }else{
                    actionButton.setText("Kayıt Ol");
                }

            }
        });
        kayıtolradiobutton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(kayıtolradiobutton.isSelected()){
                    actionButton.setText("Kayıt ol");

                    adsoyadlabel.setVisible(true);
                    gsmlabel.setVisible(true);
                    kadınRadioButton.setVisible(true);
                    erkekRadioButton.setVisible(true);
                    isimTextField.setVisible(true);
                    gsmTextField.setVisible(true);
                    isSignIn = false;

                }
                else{
                    actionButton.setText("Giriş yap");
                }
            }
        });


    }

}
