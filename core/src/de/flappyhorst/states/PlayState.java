package de.flappyhorst.states;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.flappyhorst.BookStack;
import de.flappyhorst.FlappyHorstMain;
import de.flappyhorst.Student;

//=========================================================================================//
//                                       PlayState                                         //
//=========================================================================================//

public class PlayState extends State{

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Objekt des Vogels beziehungsweise des Studenten, der als Spielecharakter dient
     */
    private Student student;

    /**
     * Hintergrundbild
     */
    private Texture backgroundImage;

    /**
     * Objekt von BookStack
     */
    private BookStack bookStack;


    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor
     *
     * @param stateManager  stateManager
     */
    public PlayState(StateManager stateManager) {
        super(stateManager);
        student = new Student(50,300);
        backgroundImage = new Texture("flappy_horst_background.png");
        bookStack = new BookStack(100);
        camera.setToOrtho(false, FlappyHorstMain.WIDTH/2, FlappyHorstMain.HEIGHT/2);
    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * handleInput()
     */
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            student.jump();
        }
    }

    /**
     * Update
     *
     * @param deltaTime deltaTime
     */
    @Override
    public void update(float deltaTime) {
        handleInput();
        student.update(deltaTime);
    }

    /**
     * Batch initsialisieren und die Textures zeichnen
     *
     * @param batch batch
     */
    @Override
    public void render(SpriteBatch batch) {

        //Fokussiere Kamera auf einen Ausschnitt des Screens
        batch.setProjectionMatrix(camera.combined);

        //Initialisiere den Batch
        batch.begin();

        //Zeichne das Hintergrundbild
        batch.draw(backgroundImage, camera.position.x - (camera.viewportWidth/2),camera.position.y - (camera.viewportHeight/4));

        //Zeichne das Texture des Studenten
        batch.draw(student.getStudentTexture(), student.getPosition().x, student.getPosition().y, 50, 50);

        //Zeichne die Bücherstapel
        batch.draw(bookStack.getTopBookStack(), bookStack.getPositionTopBookStack().x, bookStack.getPositionTopBookStack().y);
        batch.draw(bookStack.getBottomBookStack(), bookStack.getPositionBottomBookStack().x, bookStack.getPositionBottomBookStack().y);

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
