package de.flappyhorst.models;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

//=========================================================================================//
//                                       Student                                           //
//=========================================================================================//

/**
 * Die Klasse Student repräsentiert den Spielecharakter im Spiel. Hier werden sämtliche Werte,
 * die für den Spielecharakter notwendig sind (z.B. Aussehen, Geschwindigkeit, Positionierung, etc.) festgelegt.
 *
 */
public class Student {

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Schwerkraft des Studenten
     */
    private static final int GRAVITY = -15;

    /**
     * Geschwindigkeit des Studenten
     */
    private static final int MOVEMENT = 50;

    /**
     * Position des Studenten auf dem Screen
     */
    private Vector3 position;

    /**
     * Geschwindigkeit beim Hüpfen
     */
    private Vector3 velocity;

    /**
     * Texture des Studenten
     */
    private Texture student;

    /**
     * Rechteck um den Spielecharakter zur Kollisionserkennung
     */
    private Rectangle rectangle;


    private Circle circle;
    private ShapeRenderer shapeRenderer;



    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor
     *
     * @param x Position x-Koordinate
     * @param y Position y-Koordinate
     *
     */
    public Student(int x, int y){
        position = new Vector3(x, y,0);
        velocity = new Vector3(0,0,0);
        student = new Texture("flappy_horst_bird.png");
        rectangle = new Rectangle(x, y, student.getWidth(), student.getHeight());

        circle = new Circle();
        shapeRenderer = new ShapeRenderer();

    }


    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//


/*    public void test(){
        circle.set(Gdx.graphics.getWidth() /2 - 260 , position.y + student.getHeight(), student.getWidth());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(circle.x, circle.y, circle.radius);
        shapeRenderer.end();
    }*/

    /**
     * Methode, um die Position des Studenten zurückzusetzen
     */
    public void update(float deltaTime){

    if(position.y > 0){
        //Fall des Studenten simulieren
        velocity.add(0, GRAVITY,0);
    }

    velocity.scl(deltaTime);

    //Setze Position
    position.add(MOVEMENT * deltaTime, velocity.y, 0);

    //Fallen ins "Unendliche" verhindern
    if(position.y < 0 || position.y > 380){
        position.y = 0;
        position.x = position.x + 70;
    }

    //Geschwindigkeit des Fallens anpassen an die Framerate
        velocity.scl(1/deltaTime);

    //Setzt das Rechteck um den Spielecharakter zu Kollisionserkennung
    rectangle.setPosition(position.x, position.y);

    }

    /**
     * Lässt den Studenten nach oben springen
     */
    public void jump(){
        velocity.y = 250; //Positive velocity
    }

    /**
     * Löscht das Texture des Spielecharakters, um Speicher zu sparen
     */
    public void dispose(){
        student.dispose();
    }

    //========================================================================//
    //                            Getter/Setter                               //
    //========================================================================//

    /**
     * gib die Position des Studenten
     *
     * @return  position
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * gib die Texture des Studenten
     *
     * @return  student
     */
    public Texture getStudentTexture() {
        return student;
    }

    /**
     * Gibt das Rechteck, dass um den Studenten herum gebaut wurde zurück
     *
     * @return rectangle
     */
    public Rectangle getRectangle(){
        return rectangle;
    }
}
