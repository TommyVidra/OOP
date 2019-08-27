import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class CounterWorker extends SwingWorker<Integer, String> {

    private String[] cars = {"ST-150-AA", "ZG-1156-GF", "DA-444-Z", "PU-198-ZE", "KA-123-KA",
            "VU-350-RG", "SB-753-R", "GS-999-AA", "ZD-0055-EE"};
    private Random rand = new Random();
    private String detectCar(){
    //...vraća registarske oznake ili null... ne treba pisati kod metode

        int t = rand.nextInt(cars.length + 1);
        if(cars.length == t)
            return null;
        else
            return cars[t];
    }

    Consumer<String> consumer;
    private boolean stopMonitorig;

    public void stop(){
        this.stopMonitorig = true;
    }

    public CounterWorker(Consumer<String> consumer){
        this.consumer = consumer;
    }

    @Override
    protected Integer doInBackground(){

        int counter = 0;
        while(!stopMonitorig){

            try{
                String car = detectCar();
                if (car != null){
                    counter++;
                    publish(car);
                }
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
            }
        }
        return counter;
    }

    @Override
    protected void process(List<String> chunks){
        for(String s : chunks)
            consumer.accept(s);
    }
}
