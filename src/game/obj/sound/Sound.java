package game.obj.sound;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    private final URL shoot;
    private final URL hit;
    private final URL destroy;

    public Sound() {
        this.shoot = this.getClass().getClassLoader().getResource("game/obj/sound/shoot.wav");
        this.hit = this.getClass().getClassLoader().getResource("game/obj/sound/hit.wav");
        this.destroy = this.getClass().getClassLoader().getResource("game/obj/sound/destroy.wav");
    }

    public void soundShoot() {
        play(shoot);
    }

    public void soundHit() {
        play(hit);
    }

    public void soundDestroy() {
        play(destroy);
    }

    private void play(URL url) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });
            audioIn.close();
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println(e);
        }
    }
}
