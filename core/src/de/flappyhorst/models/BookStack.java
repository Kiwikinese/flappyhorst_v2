/**
 * Die Klasse BookStack repräsentiert einen Bücherstapel im Spiel. Hier werden sämtliche Werte,
 * die zu einem Bücherstapel gehören (z.B. Breite, Positionierung, etc.) festgelegt.
 *
 */

package de.flappyhorst.models;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
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
    private Texture topBookStack;

    /**
     * Texture für den unteren Bücherstapel
     */
    private Texture bottomBookStack;

    /**
     * Position des topBookStack
     */
    private Vector2 positionTopBookStack;

    /**
     * Position des bottomBookStack
     */
    private Vector2 positionBottomBookStack;

    /**
     * Random, um die BookStacks zufällig erstellen zu lassen
     */
    private Random random;

    /**
     * Rechteck für die Kollisionserkennung des topBookStack
     */
    private Rectangle rectangleTopBookStack;

    /**
     * Rechteck für die Kollisionserkennung des bottomBookStack
     */
    private Rectangle rectangleBottomBookStack;


    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor
     *
     * @param x x-Achse
     */
    public BookStack(float x){
        topBookStack = new Texture("toptube.png");
        bottomBookStack = new Texture("bottomtube.png");
        random = new Random();

        // Initialisiere die Positionen des Bottom- und des Top-BookStacks
        // Top
        positionTopBookStack = new Vector2(x, random.nextInt(FLUCTUATION) + GAP_BETWEEN_STACKS + LOWEST_OPENING);

        // Bottom
        positionBottomBookStack = new Vector2(x, positionTopBookStack.y - GAP_BETWEEN_STACKS - bottomBookStack.getHeight());

        // Initialisiere die Rechtecke, die um die Bücherstapel liegen, damit eine Kollision mit dem Spielecharakter erkannt werden kann
        // Top
        rectangleTopBookStack = new Rectangle(
                positionTopBookStack.x,
                positionTopBookStack.y,
                topBookStack.getWidth(),
                topBookStack.getHeight());

        // Bottom
        rectangleBottomBookStack = new Rectangle(
                positionBottomBookStack.x,
                positionBottomBookStack.y,
                bottomBookStack.getWidth(),
                bottomBookStack.getHeight());
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
        // Setze die Bücherstapel an die entsprechende Position
        positionTopBookStack.set(x, random.nextInt(FLUCTUATION) + GAP_BETWEEN_STACKS + LOWEST_OPENING);
        positionBottomBookStack.set(x, positionTopBookStack.y - GAP_BETWEEN_STACKS - bottomBookStack.getHeight());

        // Setze die Rechtecke über die Bücherstapel für die Kollisionserkennung
        rectangleTopBookStack.setPosition(positionTopBookStack.x, positionTopBookStack.y);
        rectangleBottomBookStack.setPosition(positionBottomBookStack.x, positionBottomBookStack.y);
    }

    /**
     * Überprüfe, ob der Spielecharakter mit einem Bücherstapel kollidiert
     *
     * @param student   student
     * @return  studet.overlaps
     *
     */
    public boolean collide(Rectangle student){
        return student.overlaps(rectangleTopBookStack) || student.overlaps(rectangleBottomBookStack);
    }

    /**
     * Lösche die Textures des oberen und unteren Bücherstapels, um Speicher zu sparen
     */
    public void dispose(){
        bottomBookStack.dispose();
        topBookStack.dispose();
    }

    //========================================================================//
    //                             Getter/Setter                              //
    //========================================================================//

    public Texture getTopBookStack() {
        return topBookStack;
    }

    public Texture getBottomBookStack() {
        return bottomBookStack;
    }

    public Vector2 getPositionTopBookStack() {
        return positionTopBookStack;
    }

    public Vector2 getPositionBottomBookStack() {
        return positionBottomBookStack;
    }

    public int getBookStackWidth(){
        return BOOKSTACK_WIDTH;
    }
}
