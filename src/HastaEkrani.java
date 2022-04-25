import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HastaEkrani extends JFrame{

    //Singleton Pattern
    private static HastaEkrani hastaEkrani = null;
    private JPanel mainPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton kayıtolbutonu;
    private JLabel hastalabel;
    private JLabel hastalabel2;


    public static HastaEkrani getInstance(){

        if (hastaEkrani == null){
            hastaEkrani = new HastaEkrani();
            return hastaEkrani;
        }else{
            return hastaEkrani;
        }

    }


    public HastaEkrani(){

        this.add(mainPanel);
        this.setSize(new Dimension(400,250));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        hastalabel.setSize(new Dimension(400,250));
        manageKayıtolbuton();
    }
    private void manageKayıtolbuton(){
        kayıtolbutonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText().length()==0||passwordField1.getText().length()==0){
                    JOptionPane.showMessageDialog(null,"Lütfen tüm alanları doldurun");
                }
            }
        });
    }

}
