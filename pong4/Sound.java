package pong4;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by icel on 10/22/2016.
 */
public class Sound {
    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
    public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameOver.wav"));
    public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back.wav"));

}
