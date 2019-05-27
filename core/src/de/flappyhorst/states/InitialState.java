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
     * Play-Button, um das Spiel zu starten
     */
    private Texture playBtn;
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

        //Zeichne den Play-Button
        batch.draw(playBtn, Gdx.graphics.getWidth() / 2 -160, Gdx.graphics.getHeight() / 2 - 850, 400, 200);

        batch.draw(image, 50, Gdx.graphics.getHeight()-750,  Gdx.graphics.getWidth()-50, 400);
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
