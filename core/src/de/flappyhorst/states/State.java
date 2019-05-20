package de.flappyhorst.states;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

//=========================================================================================//
//                                     State                                               //
//=========================================================================================//

public abstract class State {

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Jeder State braucht eine Camera, um die Position in der "Welt" zu lokalisieren
     */
    protected OrthographicCamera camera;

    /**
     * Vektor als Pointer
     */
    protected Vector3 mouse;

    /**
     * StateManager, um die verschiedenen States zu koordinieren
     */
    protected StateManager stateManager;


    //========================================================================//
    //                            Konstruktoren                               //
    //========================================================================//

    protected State(StateManager stateManager){
        this.stateManager = stateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * handleInput()
     */
    protected abstract void handleInput();

    /**
     * Update
     *
     * @param deltaTime deltaTime
     */
    public abstract void update(float deltaTime); //deltaTime ist die Different zwischen einem Frame zu dem Nächsten

    /**
     * Batch initsialisieren und die Textures zeichnen
     *
     * @param batch batch
     */
    public abstract void render(SpriteBatch batch);

    /**
     * Entferne alle Textures
     */
    public abstract void dispose();
}
