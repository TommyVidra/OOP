import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class SimulatedTaskFrame extends JFrame {

    private SwingWorker<Long, String> worker;
    private int numMessages = 25;
    private JLabel label = new JLabel("Message transfer:", JLabel.CENTER);
    private JProgressBar progressBar = new JProgressBar();
    private JButton startButton = new JButton("Start");
    private JTextArea textArea = new JTextArea();

    public SimulatedTaskFrame() {
// progress bar (0, numMessages), inkrement vrijednostiza svaku poruku
        progressBar.setIndeterminate(false);
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(numMessages);
// TO DO Dodaj odgovarajuće panele, layout, ...

        JPanel gornji = new JPanel(new FlowLayout());
        gornji.add(label);
        gornji.add(progressBar);

        textArea.setEnabled(false);

        JScrollPane srednji = new JScrollPane(textArea);
        JPanel doljnji = new JPanel(new FlowLayout()); // mora ici radi izgleda u sredini gumb

        doljnji.add(startButton);
        add(gornji, BorderLayout.NORTH);
        add(srednji, BorderLayout.CENTER);
        add(doljnji, BorderLayout.SOUTH);

        startButton.addActionListener((ActionEvent e) -> {
            startButton.setEnabled(false);
            progressBar.setValue(0);
            textArea.setText("");
            MessageWorker ms = new MessageWorker(numMessages);
            ms.execute();
        });

        setLocation(100, 100);
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    // TO DO MessageWorker dopunite
    private class MessageWorker extends SwingWorker<Long, String> {

        private int numMessages;
        public MessageWorker(int numMessages) {
            this.numMessages = numMessages;
        }
// TO DO dopisati potrebne metode MessageWorkera

        @Override
        protected Long doInBackground() throws Exception{

            long totalTime = 0;
            for (int i = 0; i < numMessages; ++i){
                long timePased = System.currentTimeMillis();
                sendMessage();
                timePased = System.currentTimeMillis() - timePased;
                String stat = "Sending " + (i+1) + ". message, " + timePased + " msec";

                publish(stat);
                totalTime += timePased;
            }
            return totalTime;
        }

        @Override
        protected void done(){

            try{
                textArea.append("All messages were transfered in: " + get() + "ms\n");
            }
            catch (Exception e){
                System.out.println(e);
            }

            startButton.setEnabled(true);
            progressBar.setValue(0);
        }

        @Override
        protected void process(List<String> lista){
            for(String djelovi : lista){
                textArea.append(djelovi + "\n");
                progressBar.setValue(progressBar.getValue() + 1);
            }
        }
    }
    void sendMessage() throws InterruptedException {
        Random rnd = new Random(System.currentTimeMillis());
        long millis = rnd.nextInt(1000);
        Thread.sleep(millis);
    }

}
