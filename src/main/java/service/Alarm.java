package main.java.service;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Alarm {
    public static void soundAlarm() {
        for (int i = 0; i < 3; i++) {
            try {
                InputStream inSound = Alarm.class.getClassLoader().getResourceAsStream("main/resources/btc_alarm.wav");
                InputStream bufferIn = new BufferedInputStream(inSound);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferIn);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
