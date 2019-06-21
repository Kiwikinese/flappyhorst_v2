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

/**
 * Die Klasse BookStack repräsentiert einen Bücherstapel im Spiel, über den man mit dem
 * Spielecharakter {@link Student} versuchen muss zu springen ohne diesen zu berühren.
 * Es werden sämtliche Werte für diesen festgelegt wie z.B. das Aussehen, die Positionierung etc.
 *
 */
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
     * Konstruktor der Klasse BookStack
     *
     * @param x x-Achse
     */
    public BookStack(float x){
        this.topBookStack = new Texture("Bücherstapel.png");
        this.bottomBookStack = new Texture("Bücherstapel.png");
        this.random = new Random();

        //Initialisiere die Positionen des Bottom- und des Top-BookStacks
        this.positionTopBookStack = new Vector2(x, random.nextInt(FLUCTUATION) + GAP_BETWEEN_STACKS + LOWEST_OPENING);
        this.positionBottomBookStack = new Vector2(x, positionTopBookStack.y - GAP_BETWEEN_STACKS - bottomBookStack.getHeight());

        /*Initialisiere die Rechtecke, die um die Bücherstapel liegen, damit eine Kollision mit dem
          Spielecharakter erkannt werden kann für den oberen und den unteren Bücherstapel.*/
        this.rectangleTopBookStack = new Rectangle(
                this.positionTopBookStack.x,
                this.positionTopBookStack.y,
                this.topBookStack.getWidth(),
                this.topBookStack.getHeight());

        this.rectangleBottomBookStack = new Rectangle(
                this.positionBottomBookStack.x,
                this.positionBottomBookStack.y,
                this.bottomBookStack.getWidth(),
                this.bottomBookStack.getHeight());
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
        this.positionTopBookStack.set(x,  this.random.nextInt(FLUCTUATION) +  GAP_BETWEEN_STACKS + LOWEST_OPENING);
        this.positionBottomBookStack.set(x, positionTopBookStack.y - GAP_BETWEEN_STACKS - bottomBookStack.getHeight());

        // Setze die Rechtecke über die Bücherstapel für die Kollisionserkennung
        this.rectangleTopBookStack.setPosition( this.positionTopBookStack.x,  this.positionTopBookStack.y);
        this.rectangleBottomBookStack.setPosition( this.positionBottomBookStack.x,  this.positionBottomBookStack.y);
    }

    /**
     * Lösche die Textures des oberen und unteren Bücherstapels, um Speicher zu sparen
     */
    public void dispose(){
        this.bottomBookStack.dispose();
        this.topBookStack.dispose();
    }

    //========================================================================//
    //                             Getter/Setter                              //
    //========================================================================//

    public Texture getTopBookStack() {
        return  this.topBookStack;
    }

    public Texture getBottomBookStack() {
        return  this.bottomBookStack;
    }

    public Vector2 getPositionTopBookStack() {
        return  this.positionTopBookStack;
    }

    public Vector2 getPositionBottomBookStack() {
        return  this.positionBottomBookStack;
    }

    public Rectangle getRectangleTopBookStack(){
        return  this.rectangleTopBookStack;
    }

    public Rectangle getRectangleBottomBookStack(){
        return  this.rectangleBottomBookStack;
    }

    public int getBookStackWidth(){
        return  BOOKSTACK_WIDTH;
    }
}
