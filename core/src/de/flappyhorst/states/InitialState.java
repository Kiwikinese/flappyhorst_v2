package de.flappyhorst.states;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Hauptmenü des Spiels. Von hier aus kann über die Play-Texture das Spiel gestartet werden oder
 * über die Highscore-Texture sich der aktuelle Highscore angeschaut werden.
 * Auch die Musik kann über Textures eingeschaltet oder ausgeschaltet werden.
 *
 */

//=========================================================================================//
//                                     InitialState                                        //
//=========================================================================================//

public class InitialState extends State{

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Hintergrundbild
     */
    private Texture backgroundImage;

    /**
     * Play-Button, um das Spiel zu starten
     */
    private Texture playBtn;

    /**
     * Button, um auf dem Highscore-Screen zu landen
     */
    private Texture highscoreBtn;

    /**
     * Logo des Spiels
     */
    private Texture logo;

    /**
     * Texture für den On- und Off Button zum Abspielen/Stoppen der Musik
     */
    private Texture volumeOn, volumeOff;

    /**
     * Musik des Spiels
     */
    private Music song;

    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor
     *
     * @param stateManager  stateManager
     */
    public InitialState(StateManager stateManager){
        super(stateManager);

        //Textures initialisieren
        backgroundImage = new Texture("flappy_horst_background.png");
        playBtn = new Texture("initial_screen_playbtn.png");
        highscoreBtn = new Texture("initial_screen_highscorebtn.png");
        logo = new Texture("logo.png");
        volumeOn = new Texture("volume_on.png");
        volumeOff = new Texture("volume_off.png");

        initializeSong();
    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * Checkt den Input des Users
     */
    @Override
    public void handleInput() {
       if(Gdx.input.getX() < 490 && Gdx.input.getY() > 1450){
            stateManager.set(new PlayState(stateManager));
        }else if(Gdx.input.getX() > 560 && Gdx.input.getY() > 1450){
           stateManager.set(new GameoverState(stateManager));
       }
    }

    /**
     * Update
     *
     * @param deltaTime deltaTime
     */
    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    /**
     * Batch initsialisieren und die Textures zeichnen
     *
     * @param batch batch
     */
    @Override
    public void render(SpriteBatch batch) {
        //Initialisiere den Batch
        batch.begin();

        //Zeichne das Hintergrundbild an die Position 0, 0 und setze die Breite je nach Größe des Bildschirms
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Zeichne sämtliche Buttons
        batch.draw(playBtn, Gdx.graphics.getWidth() / 2 -460, Gdx.graphics.getHeight() / 2 - 800, 400, 250);
        batch.draw(highscoreBtn, Gdx.graphics.getWidth() / 2 + 60 , Gdx.graphics.getHeight() / 20, 400, 250);
        batch.draw(logo, 50, Gdx.graphics.getHeight() - 700,  Gdx.graphics.getWidth()-50, 300);
        batch.draw(volumeOn, 900, 1600, 100,100);
        batch.draw(volumeOff, 100, 1600, 100,100);

        onClickVolumeButtons();

        //Beende den Batch
        batch.end();
    }

    /**
     * Wenn zu einem anderen State gewechselt wird, rufen wir diese Methode auf, um die Textures zu entfernen
     */
    @Override
    public void dispose() {
        backgroundImage.dispose();
        playBtn.dispose();
        volumeOff.dispose();
        volumeOn.dispose();

        Gdx.app.log("InitialState","InitialState disposed");
    }

    /**
     * Methode, um die Musik bei Touch auf die entsprechenden Textures ein- oder auszuschalten
     */
    public void onClickVolumeButtons(){
        if(Gdx.input.getX() > Gdx.graphics.getWidth() / 2 && Gdx.input.getY() < 400){
            song.play();
        }else if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 - 50 && Gdx.input.getY() < 400){
            song.stop();
        }
    }

    /**
     * Initialisiere die Musik des Spiels
     */
    public void initializeSong(){
        song = Gdx.audio.newMusic(Gdx.files.internal("_pokemon_theme.mp3"));
        song.setLooping(true);
        song.setVolume(0.1f);	//10% von max. 100% Lautstärke
    }
}
