package de.flappyhorst;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

//=========================================================================================//
//                                       Student                                           //
//=========================================================================================//

public class Student {

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Schwerkraft des Studenten
     */
    private static final int GRAVITY = -15;

    /**
     * Position des Studenten auf dem Screen
     */
    private Vector3 position;

    /**
     * Geschwindigkeit
     */
    private Vector3 velocity;

    /**
     * Texture des Studenten
     */
    private Texture student;



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
        student = new Texture("flappy_horst_icon.png");
    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

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
    position.add(0, velocity.y, 0);

    //Fallen ins "Unendliche" verhindern
    if(position.y < 0){
        position.y = 0;
    }

    //Geschwindigkeit des Fallens anpassen an die Framerate
        velocity.scl(1/deltaTime);
    }

    /**
     * Lässt den Studenten nach oben springen
     */
    public void jump(){
        velocity.y = 350; //Positive velocity
    }

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
}
