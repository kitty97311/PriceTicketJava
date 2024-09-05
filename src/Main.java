import main.java.service.Alarm;
import main.java.service.PriceSymbol;
import main.java.service.Welcome;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static String sharedVariable = "Este es un valor compartido";
    static double actualPrice;
    static double entryPrice;
    static double factorRisk;
    public static double gap;

    public static void main(String[] args) {


        factorRisk = Welcome.screen();
        entryPrice = Double.parseDouble(PriceSymbol.priceSym().getPrice());
        System.out.println("Entry price: " + entryPrice);
        repeatInstructions();
        windowInfo();
    }

    public static void repeatInstructions() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    actualPrice = Double.parseDouble(PriceSymbol.priceSym().getPrice());
                    gap = actualPrice - entryPrice;


                    if (gap >= 25) {
                        entryPrice += gap;
                    }

                    if (actualPrice <= (entryPrice - factorRisk)) {
                        System.out.println("buy buy buy...");
                        Thread thread = new Thread(() -> {
                            screenAlert();
                        });
                        thread.start();
                        Alarm.soundAlarm();
                    } else if (actualPrice >= (entryPrice + factorRisk)) {
                        System.out.println("Up sell sell sell...");
                        Thread thread = new Thread(() -> {
                            screenAlert();
                        });
                        thread.start();
                        Alarm.soundAlarm();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }


    public static void windowInfo() {
        JFrame frame = new JFrame("$SYMBOL    ||  ENTRY       ||  GAP ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 55);

        JLabel label = new JLabel("SYMBOL REAL TIME");
        Font font = label.getFont();
        float fontSize = font.getSize() + 3;
        label.setFont(font.deriveFont(fontSize));
        frame.getContentPane().add(label);
        frame.setAlwaysOnTop(true);

        // Crear un hilo para actualizar la información en tiempo real
        Thread thread = new Thread(() -> {
            while (true) {
                // Actualizar la información
                String information = symbolRealTimeInformation();

                // Actualizar el texto del JLabel
                label.setText(information);

                try {
                    Thread.sleep(0); // Esperar 0 segundo antes de la siguiente actualización
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // Iniciar el hilo
        thread.start();
        // Mostrar la ventana
        frame.setVisible(true);
    }

    private static String symbolRealTimeInformation() {
        // Simulación de obtención de información en tiempo real
        // Puedes implementar aquí la lógica para obtener la información actualizada
        DecimalFormat formatDouble = new DecimalFormat("#.00");
//        gap = Double.parseDouble(PriceSymbol.priceSym().getPrice()) - entryPrice;
        String gapSTR = formatDouble.format(gap);
        return "      " + PriceSymbol.priceSym().getPrice() + "    " + entryPrice + "     " + gapSTR + "       Risk:     " + factorRisk;
    }

    public static void screenAlert() {
        DecimalFormat formatDouble = new DecimalFormat("#0.00");
        String gapSTR = formatDouble.format(gap);

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("BTC CHANGE GAP:  $ " + gapSTR);
        optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        JDialog dialog = optionPane.createDialog(null, "BTC = " + PriceSymbol.priceSym().getPrice() + "  " + "START PRICE = " + entryPrice);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}