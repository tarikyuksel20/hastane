import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GirisEkrani extends JFrame {
    private JPanel mainPanel;
    private JLabel entryText;
    private JButton hastaGirişButton;
    private JButton doktorGirişButton;
    private HastaEkrani hastaEkrani;

    private DoktorEkrani doktorEkrani;

    private DoktorAnaSayfa doktorAnaSayfa;

    public GirisEkrani(){

        this.add(mainPanel);
        this.setSize(new Dimension(400,250));
        this.setVisible(true);
        this.setTitle("Giriş Ekranı");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        mainPanel.setVisible(true);
        entryText.setSize(15,15);
        manageHastaGirisButton();
        manageDoktorGirişButton();
    }

    private void manageHastaGirisButton(){

        hastaGirişButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hastaEkrani = HastaEkrani.getInstance();
                hastaEkrani.setVisible(true);
                mainPanel.setVisible(false);


            }
        });
    }

    private void manageDoktorGirişButton(){

        doktorGirişButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                doktorEkrani = DoktorEkrani.getInstance();
                doktorEkrani.setVisible(true);
                mainPanel.setVisible(false);
            }
        });
    }



}
