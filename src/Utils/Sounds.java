package Utils;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Game Of Life application created for Oslo and Akershus University Collage of Applied Sciences.
 * The Sound class contains methods creating sound used in application.
 *
 * @author Fredrik Kluftodegaard, Hans Jacob Hauge, Mohammad Abdolla
 *         Studentnr : S309293, s305064, s309856
 */

public class Sounds {

    /**
     * This method creates a sound that plays when a button is clicked.
     * Public domain. Source:http://soundbible.com/1280-Click-On.html
     */
    public static void btnSound() {
        String buttonSound = "/btnclick.wav";
        Media btnSound = new Media(new File(getPath() + buttonSound).toURI().toString());
        MediaPlayer mPlayer = new MediaPlayer(btnSound);
        mPlayer.play();
    }

    /**
     * This method creates a sound that plays when application starts up.
     * Public domain. Source:http://soundbible.com/1589-Computer-Start-Up.html.
     */
    public static void startUpSound() {
        String startSound = "/golstartup.wav";
        Media btnSound = new Media(new File(getPath() + startSound).toURI().toString());
        MediaPlayer mPlayer = new MediaPlayer(btnSound);
        mPlayer.play();
    }

    /**
     * This method creates a error sound that plays when something doesn't work or an error occures.
     * Public domain.  https://www.myinstants.com/instant/windows-xp-error/.
     */
    public static void errorSound() {
        String errorSound = "/erro.mp3";
        Media btnSound = new Media(new File(getPath() + errorSound).toURI().toString());
        MediaPlayer mPlayer = new MediaPlayer(btnSound);
        mPlayer.play();
    }

    private static String getPath() {
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }

}
