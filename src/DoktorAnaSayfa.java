import javax.swing.*;

public class DoktorAnaSayfa extends JFrame {
    private static DoktorAnaSayfa doktorAnaSayfa = null;

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
    }

}

