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
     * @param x x
     */
    public BookStack(float x){
        TopBookStack = new Texture("toptube.png");
        BottomBookStack = new Texture("bottomtube.png");
        random = new Random();

        positionTopBookStack = new Vector2(x, random.nextInt(FLUCTUATION) + GAP_BETWEEN_STACKS + LOWEST_OPENING);
        positionBottomBookStack = new Vector2(x, positionTopBookStack.y - GAP_BETWEEN_STACKS - BottomBookStack.getHeight());
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
}
