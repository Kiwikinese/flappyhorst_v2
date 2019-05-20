package de.flappyhorst.states;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//=========================================================================================//
//                                       PlayState                                         //
//=========================================================================================//

public class PlayState extends State{

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Texture des Vogels beziehungsweise des Studenten, der als Spielecharakter dient
     */
    private Texture bird;


    //========================================================================//
    //                            Konstruktoren                               //
    //========================================================================//

    public PlayState(StateManager stateManager) {
        super(stateManager);
        bird = new Texture("flappy_horst_icon.png");
    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * handleInput()
     */
    @Override
    protected void handleInput() {

    }

    /**
     * Update
     *
     * @param deltaTime deltaTime
     */
    @Override
    public void update(float deltaTime) {

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

        //Zeichne das Texture des Studenten
        batch.draw(bird, Gdx.graphics.getWidth() / 2 - bird.getWidth() / 2, Gdx.graphics.getHeight() / 2 - bird.getHeight() / 2, 200, 200);

        //Beende den Batch
        batch.end();

    }

    /**
     * Wenn zu einem anderen State gewechselt wird, rufen wir diese Methode auf, um die Textures zu entfernen
     */
    @Override
    public void dispose() {

    }
}
