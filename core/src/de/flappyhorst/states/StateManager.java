package de.flappyhorst.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

//=========================================================================================//
//                                 StateManager                                            //
//=========================================================================================//

/**
 * Die Klasse StatMangaer initialisert einen Stack von States und die entsprechenden Methoden, um
 * von diesem Stack States zu entfernen, hinzuzufügen und diese durch Aufruf der Methoden in den
 * verschiedenen State-Klassen die Änderungen zu aktualisieren.
 *
 */
public class StateManager {

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Stack von den verschiedenen States des Spiels
     */
    private Stack<State> states;

    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor
     */
    public StateManager(){
        states = new Stack<State>();
    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * Methode, um einen State zu pushen (wird dem Stack hinzugefügt)
     *
     * @param state state
     */
    public void push(State state){
        states.push(state);
    }

    /**
     * Methode, um den State aus dem Stack zu holen (wird aus dem Stack entfernt)
     */
    public void pop(){
        states.pop().dispose();
    }

    /**
     * Methode, um den State vom Stack zu holen und direkt zu pushen (bzw. zu setzen)
     *
     * @param state state
     */
    public void set(State state){
        states.pop();
        states.push(state);
    }

    /**
     * Methode, um das oberste Objekt des Stacks abzurufen. Das passiert mit "peek()"
     *
     * @param deltaTime deltaTime
     */
    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }

    /**
     * Batch
     *
     * @param batch batch
     */
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }
}
