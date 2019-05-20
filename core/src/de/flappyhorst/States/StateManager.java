package de.flappyhorst.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

//=========================================================================================//
//                                 StateManager                                            //
//=========================================================================================//

public class StateManager {

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Stack von den verschiedenen States des Spiels
     */
    private Stack<State> states;

    //========================================================================//
    //                            Konstruktoren                               //
    //========================================================================//

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
        states.pop();
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
