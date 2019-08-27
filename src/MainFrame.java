import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    CounterWorker worker;

    public MainFrame(){
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        stop.setEnabled(false);

        JTextField text = new JTextField("");
        text.setEditable(false);
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setFont(new Font("Courier", Font.BOLD, 30));

        JTextField txtCount = new JTextField("");
        txtCount.setEditable(false);

        //TO DO: Dopisati inicijalizacije ostalih kontrola i posložiti ih kao na gornjim slikama
        JPanel gornji = new JPanel(new GridLayout());//Probati sa FlowLayoutom
        gornji.add(start);
        gornji.add(stop);

        add(gornji, BorderLayout.NORTH);
        add(text, BorderLayout.CENTER);
        add(txtCount, BorderLayout.SOUTH);



        start.addActionListener(e -> {
            start.setEnabled(false);
            txtCount.setText("0");
            //TO DO: Inicijalizirati Worker
            CounterWorker worker = new CounterWorker((s) -> text.setText(s));
            worker.execute();
            stop.setEnabled(true);
        });


        stop.addActionListener(e -> {
            start.setEnabled(false);
            text.setText("");
            worker.stop();
            Integer total = null;
            try {
                //TO DO: dohvatiti rezultat iz workera (napisati na crtu)
                total = worker.get();
                txtCount.setText("Total: " + total);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            start.setEnabled(true);
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Car recognizer");
                frame.setBounds(200, 200, 300, 300);
                frame.setVisible(true);
            }
        });
    }
}


