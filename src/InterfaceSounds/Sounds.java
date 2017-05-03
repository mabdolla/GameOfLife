package InterfaceSounds;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * The Game Of Life application created for HIOA
 * The Sound interface contains methods creating sound used in application.
 *
 * @author Fredrik, Hans-Jacob, Mohammad
 * Studentnr : S309293, s
 */

public interface Sounds  {

    /**
     * This method creates a sound that plays when a button is clicked.
     * Public domain. Source:http://soundbible.com/1280-Click-On.html
     */
    static void btnSound() {
        String buttonSound = "btnclick.wav";
        Media btnSound = new Media(new File(buttonSound).toURI().toString());
        MediaPlayer mPlayer = new MediaPlayer(btnSound);
        mPlayer.play();
    }

    /**
     * This method creates a sound that plays when application starts up.
     * Public domain. Source:http://soundbible.com/1589-Computer-Start-Up.html.
     */
    static void startUpSound() {
        String startSound = "golstartup.wav";
        Media btnSound = new Media(new File(startSound).toURI().toString());
        MediaPlayer mPlayer = new MediaPlayer(btnSound);
        mPlayer.play();
    }

    /**
     * This method creates a error sound that plays when something dont work or error occure.
     * Public domain.  https://www.myinstants.com/instant/windows-xp-error/.
     */
    static void errorSound(){
        String errorSound = "erro.mp3";
        Media btnSound = new Media(new File(errorSound).toURI().toString());
        MediaPlayer mPlayer = new MediaPlayer(btnSound);
        mPlayer.play();
    }



}
