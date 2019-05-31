package de.flappyhorst;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

//=========================================================================================//
//                                       BookStack                                         //
//=========================================================================================//

public class BookStack {

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Breite des Bücherstapels
     */
    private static final int BOOKSTACK_WIDTH = 52;

    /**
     * Positionierung des Stapels zwischen 0 und 130
     */
    private static final int FLUCTUATION = 130;

    /**
     * Abstand zwischen den Bücherstapeln
     */
    private static final int GAP_BETWEEN_STACKS = 100;

    /**
     * Mindesthöhe, auf der der Bücherstapel angezeigt werden soll
     */
    private static final int LOWEST_OPENING = 120;

    /**
     * Texture für den oberen Bücherstapel
     */
    private Texture TopBookStack;

    /**
     * Texture für den unteren Bücherstapel
     */
    private Texture BottomBookStack;

    /**
     * Position des TopBookStack
     */
    private Vector2 positionTopBookStack;

    /**
     * Position des BottomBookStack
     */
    private Vector2 positionBottomBookStack;

    /**
     * Random, um die BookStacks zufällig erstellen zu lassen
     */
    private Random random;


    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor
     *
     * @param x x-Achse
     */
    public BookStack(float x){
        TopBookStack = new Texture("toptube.png");
        BottomBookStack = new Texture("bottomtube.png");
        random = new Random();

        positionTopBookStack = new Vector2(x, random.nextInt(FLUCTUATION) + GAP_BETWEEN_STACKS + LOWEST_OPENING);
        positionBottomBookStack = new Vector2(x, positionTopBookStack.y - GAP_BETWEEN_STACKS - BottomBookStack.getHeight());
    }


    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * Umpositionierung des Bücherstapels, der hinter dem Studenten liegt
     *
     * @param x x-Achse
     */
    public void reposition(float x){
        positionTopBookStack.set(x, random.nextInt(FLUCTUATION) + GAP_BETWEEN_STACKS + LOWEST_OPENING);
        positionBottomBookStack.set(x, positionTopBookStack.y - GAP_BETWEEN_STACKS - BottomBookStack.getHeight());
    }

    //========================================================================//
    //                             Getter/Setter                              //
    //========================================================================//

    public Texture getTopBookStack() {
        return TopBookStack;
    }

    public Texture getBottomBookStack() {
        return BottomBookStack;
    }

    public Vector2 getPositionTopBookStack() {
        return positionTopBookStack;
    }

    public Vector2 getPositionBottomBookStack() {
        return positionBottomBookStack;
    }

    public int getBookstackWidth(){
        return BOOKSTACK_WIDTH;
    }
}
