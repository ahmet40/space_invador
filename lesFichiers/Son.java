//import javax.sound.sampled.*;
//import java.io.File;
//import javafx.scene.media.MediaPlayer;
//import com.sun.media.jfxmedia.Media;
//
///**
// * permet de cree la class Son
// */
//public class Son{
//    SourceDataLine sdl;
//    /**
//     * constructeur de la class Son
//     */
//    Son (){
//        try {
//            sdl=AudioSystem.getSourceDataLine(new AudioFormat(8000f,8,1,true,false));
//            sdl.open(new AudioFormat(8000f,8,1,true,false));
//            sdl.start();
//            sdl.flush () ;
//        }
//        catch(LineUnavailableException e){
//        }
//    }
//    /**
//     * permet de mettre en pause le son
//     */
//    public void pause(){
//        try {
//            Thread.sleep(100);
//        }
//        catch(InterruptedException e){
//        }
//    }
//    
//    /**
//     * emet un son 
//     * @param msecs le temps de l'emission du son en milieme de seconde
//     */
//    public void tone(int msecs)
//    {
//        byte [] buf = new byte [ msecs *8];
//        for( int i=0;i<msecs*8;i++){
//            double angle = i/(8000f/440)*2.0*Math.PI;
//            buf[i]=(byte)(Math.sin(angle)*127.0);
//        }
//        sdl.write(buf,0,8*msecs);
//        sdl.drain();
//    }
//    /**
//     * permet de jouer le Son 
//     * @param args les arguments
//     * @throws Exception gerer les exeption
//     */
//    public static void main(String[] args) throws Exception {
//        //final URL musicURL = getClass().getResource("../lesSons/spaceinvarders1.mp3");   
//        //final  Media media = new Media(musicURL.toExternalForm());
//        final File file = new File("C:\\Home\\baba\\iuto\\java\\sae_space_invador\\spaceinvaders.mp3"); 
//        Media media = new Media(file);  
//        final MediaPlayer mediaPlayer = new MediaPlayer(media); 
//        mediaPlayer.play(); 
//    }
//}