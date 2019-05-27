package de.flappyhorst.states;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Anfänglicher Status mit dem Play-Button
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
     * Paly-Button, um das Spiel zu starten
     */
    private Texture playBtn;
    private Texture highschoreBtn;
    private Texture settingsBtn;
    private Texture  image;


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
        backgroundImage = new Texture("flappy_horst_background.png");
        playBtn = new Texture("initial_screen_playbtn.png");
        highschoreBtn = new Texture("initial_screen_highscorebtn.png");
        settingsBtn = new Texture("settings_btn.png");
        image = new Texture("logo.png");


    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * handleInput()
     */
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            stateManager.set(new PlayState(stateManager));
            dispose();
        }
    }

    /**
     * Update
     *
     * @param deltaTime deltaTime
     */
    @Override
    public void update(float deltaTime) {
        handleInput(); //Checkt den Input des Users
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

        //Zeichne den Play-Button und den Highscore-Button
        batch.draw(playBtn, Gdx.graphics.getWidth() / 2 -460, Gdx.graphics.getHeight() / 2 - 800, 400, 250);
        batch.draw(highschoreBtn, Gdx.graphics.getWidth() / 2 +60, Gdx.graphics.getHeight() / 2 - 800, 400, 250);
        batch.draw(settingsBtn, Gdx.graphics.getWidth() - 210, 10, 200, 200);
        //Zeichne das Logo
        batch.draw(image, 50, Gdx.graphics.getHeight()-750,  Gdx.graphics.getWidth()-50, 300);

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
    }
}
