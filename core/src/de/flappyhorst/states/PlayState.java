package de.flappyhorst.states;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
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
     * Spacing zwischen Bücherstapeln
     */
    private static final int BOOKSTACK_SPACING = 150;

    /**
     * Anzahl der Bücherstapel, die gleichzeitig im Spiel vorhanden sein sollen
     */
    private static final int BOOKSTACK_COUNT = 4;

    /**
     * Objekt des Vogels beziehungsweise des Studenten, der als Spielecharakter dient
     */
    private Student student;

    /**
     * Hintergrundbild
     */
    private Texture backgroundImage;

    /**
     * Array von Bücherstapeln
     */
    private Array<BookStack> bookStacks;


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
        BookStack bookStack = new BookStack(0);
        camera.setToOrtho(false, FlappyHorstMain.WIDTH / 2, FlappyHorstMain.HEIGHT/2);

        //Initialisiere das Array von Bücherstapeln und füge die Bücherstapel dem Array hinzu
        bookStacks = new Array<BookStack>();

        for (int i = 1; i < BOOKSTACK_COUNT; i++){
            bookStacks.add(new BookStack(i * (BOOKSTACK_SPACING + bookStack.getBookstackWidth())));
        }
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

        camera.position.x = student.getPosition().x + 80;

        // Wenn ein Bücherstapel auserhalb des linken Bildschirmrandes ist, positioniere ihn neu an das Ende
        for(BookStack bookStack : bookStacks){
            if(camera.position.x - (camera.viewportWidth / 2) > bookStack.getPositionTopBookStack().x + bookStack.getTopBookStack().getWidth()){
                bookStack.reposition(
                        bookStack.getPositionTopBookStack().x + ((bookStack.getBookstackWidth() +
                                BOOKSTACK_SPACING * BOOKSTACK_COUNT)));
            }
        }

        camera.update();
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
        batch.draw(backgroundImage, camera.position.x - (camera.viewportWidth/2),camera.position.y - (camera.viewportHeight/2));

        //Zeichne das Texture des Studenten
        batch.draw(student.getStudentTexture(), student.getPosition().x, student.getPosition().y, 40, 40);

        //Zeichne die Bücherstapel
        for(BookStack bookStack : bookStacks){
            batch.draw(bookStack.getTopBookStack(), bookStack.getPositionTopBookStack().x, bookStack.getPositionTopBookStack().y);
            batch.draw(bookStack.getBottomBookStack(), bookStack.getPositionBottomBookStack().x, bookStack.getPositionBottomBookStack().y);
        }


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
