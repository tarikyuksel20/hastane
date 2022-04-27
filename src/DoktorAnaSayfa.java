import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoktorAnaSayfa extends JFrame {
    private static DoktorAnaSayfa doktorAnaSayfa = null;
    private JLabel field1;
    private JButton çıkışYapButton;
    private JPanel mainpanel;

    public static DoktorAnaSayfa getInstance(){

        if (doktorAnaSayfa == null){
            doktorAnaSayfa = new DoktorAnaSayfa();
            return doktorAnaSayfa;
        }else{
            return doktorAnaSayfa;
        }

    }
    public DoktorAnaSayfa(){
        this.setTitle("Doktor Ana Sayfa");
        this.add(mainpanel);
        this.setSize(400,400);
        manageDoktorAnasayfaÇıkışbutton();

    }
    private void manageDoktorAnasayfaÇıkışbutton(){
        çıkışYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doktorAnaSayfa.setVisible(false);
            }
        });
    }
}

