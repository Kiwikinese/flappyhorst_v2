package de.flappyhorst.states;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import de.flappyhorst.models.BookStack;
import de.flappyhorst.FlappyHorstMain;
import de.flappyhorst.models.Student;

//=========================================================================================//
//                                       PlayState                                         //
//=========================================================================================//

/**
 * Die Klasse PlayState repräsentiert das eigentliche Spiel. Hier werden sowohl der
 * Spielecharakter{@link Student}, als auch die Bücherstapel {@link BookStack} initialisiert.
 * Sobald der Spielecharakter mit dem Boden, dem oberen Bildschirmrand oder einem Bücherstapel
 * kollidiert öffnet sich der GameOver-Screen {@link GameoverState}.
 * Für jeden überwundenen Bücherstapel bekommt der Spieler einen Punkt, der in der unteren linken Ecke
 * des Spielfelds angezeigt wird.
 */
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
    private static final int BOOKSTACK_COUNT = 100;

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

    /**
     * Bücherstapel-Objekt
     */
    private BookStack bookStack;

    /**
     * Score
     */
    private int score;

    /**
     * Highscore
     */
    private int highscore;

    /**
     * Schriftart für die Ausgabe des Scores
     */
    private BitmapFont font;

    /**
     * Preferences für den Score und den Highscore
     */
    public Preferences prefs;

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

        student = new Student(50,200);
        backgroundImage = new Texture("background_small.JPG");
        bookStack = new BookStack(0);

        //Anzeigen des Scores auf dem Bildschirm über ein Bitmap
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(1);

        //Initialisiere die Kamera
        camera.setToOrtho(false, FlappyHorstMain.WIDTH/2, FlappyHorstMain.HEIGHT/2);

        //Initialisiere das Array von Bücherstapeln und füge die Bücherstapel dem Array hinzu
        bookStacks = new Array<BookStack>();

        for (int i = 1; i <= BOOKSTACK_COUNT; i++){
            bookStacks.add(new BookStack(i * (BOOKSTACK_SPACING + bookStack.getBookStackWidth())));
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
     * Kollisionserkennung (sobald der Student mit einem Bücherstapel, der oberen Grenze des
     * Bildschirms oder dem Boden in Berührung kommt, landet man im GameOverState).
     * Des Weiteren werden die Bücherstapel, die den linken Bildschirmrand verlassen, am rechten Bildschirmrand
     * wieder neu positioniert.
     * Auch der erreichte Score bzw. Highscore wird gespeichert und an den GameOverState übergeben.
     *
     * @param deltaTime deltaTime
     */
    @Override
    public void update(float deltaTime) {
        Preferences prefs = Gdx.app.getPreferences("My Preferences");
        handleInput();
        student.update(deltaTime);
        camera.position.x = student.getPosition().x + 80;

        for(BookStack bookStack : bookStacks){
            if(camera.position.x - (camera.viewportWidth / 2) > bookStack.getPositionTopBookStack().x + bookStack.getTopBookStack().getWidth()){
                bookStack.reposition(
                        bookStack.getPositionTopBookStack().x
                                + ((bookStack.getBookStackWidth()
                                + BOOKSTACK_SPACING * BOOKSTACK_COUNT)));

                score++;
                Gdx.app.log("Score", String.valueOf(score));
            }

            if(Intersector.overlaps(
                    student.getRectangle(), bookStack.getRectangleTopBookStack())
                    || Intersector.overlaps(student.getRectangle(), bookStack.getRectangleBottomBookStack())
                    || student.getPosition().y < 0){

                stateManager.set(new GameoverState(stateManager));
                Gdx.app.log("Kollision", "Kollision!");

                prefs.putInteger("currentscore", score);
                prefs.flush();

                if(score > prefs.getInteger("newHS", highscore)){
                    highscore = score;
                    prefs.putInteger("highscore", highscore);
                    prefs.flush();
                }

                score = 0;
            }
        }

        //Kamera folgt dem Spielecharakter
       camera.update();
    }

    /**
     * Batch initialisieren und die Textures zeichnen
     *
     * @param batch batch
     */
    @Override
    public void render(SpriteBatch batch) {
        //Fokussiere Kamera auf einen Ausschnitt des Screens
        batch.setProjectionMatrix(camera.combined);

        //Initialisiere den Batch
        batch.begin();

        //Zeichne das Hintergrundbild und den Spielecharakter
        batch.draw(backgroundImage, camera.position.x - (camera.viewportWidth/2),camera.position.y - (camera.viewportHeight/2));
        batch.draw(student.getStudentTexture(), student.getPosition().x, student.getPosition().y, 40, 40);

        //Zeichne die Bücherstapel
        for(BookStack bookStack : bookStacks){
            batch.draw(bookStack.getTopBookStack(), bookStack.getPositionTopBookStack().x, bookStack.getPositionTopBookStack().y);
            batch.draw(bookStack.getBottomBookStack(), bookStack.getPositionBottomBookStack().x, bookStack.getPositionBottomBookStack().y);
        }

        //Zeichne den Score auf den Screen
        font.draw(batch, String.valueOf(score), camera.position.x - 100, camera.position.y - 150);

        //Beende den Batch
        batch.end();
    }

    /**
     * Wenn zu einem anderen State gewechselt wird, rufen wir diese Methode auf, um dem State zu
     * clearen um somit Speicherplatz zu sparen
     *
     */
    @Override
    public void dispose() {
        backgroundImage.dispose();
        student.dispose();
        font.dispose();

        for(BookStack bookStack : bookStacks){
            bookStack.dispose();

            Gdx.app.log("PlayState","PlayState disposed");
        }
    }

}
