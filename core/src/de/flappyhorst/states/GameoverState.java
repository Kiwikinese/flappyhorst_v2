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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

//=========================================================================================//
//                                     GameoverState                                       //
//=========================================================================================//

/**
 * GameOver-Screen des Spiels. Sobald der Spieler mit dem Spielecharakter mit dem Boden, der oberen Grenze des
 * Bildschirms oder den Bücherstapeln kollidiert, wird der GameOver-Screen aufgerufen. Hier werden dem Spieler
 * sein zuletzt erreichter Score und sein Highscore angezeigt.
 *
 */
public class GameoverState extends State {

    /**
     * Texture des Logos
     */
    private Texture endImageTexture;

    /**
     * Texture des Close-Buttons
     */
    private Texture closeBtnTexture;

    /**
     * Bitmap für die Schriftart
     */
    private BitmapFont font;

    /**
     * Preferences für die Scores
     */
    private Preferences prefs = Gdx.app.getPreferences("My Preferences");

    /**
     * Hishscore
     */
    private int highscore;

    /**
     * Aktueller Score
     */
    private int currentScore;

    /**
     * Stage
     */
    private Stage stage;

    /**
     * TextureRegion für den Close-Button und das Game-Over Logo
     */
    private TextureRegion closeBtnRegion, logoRegion;

    /**
     * TextureRegionDrawable für Close-Button und das Game-Over Logo
     */
    private TextureRegionDrawable closeBtnRegionDrawable, logoRegionDrawable;

    /**
     * ImageButton für den Close-Button und das Logo
     */
    private ImageButton closeBtn, logo;

    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor der Klasse GameoverState. Hier werden die Textures, die Schriftart,
     * die Buttons und die Stage initialisiert. Auch der OnClick-Listener bzw. Eventlistener für die
     * Buttons wird hier aufgerufen. Über den Close-Button lander der Spieler dann wieder im Hauptmenü (InitialState)
     *
     * @param stateManager stateManager
     */
    public GameoverState(StateManager stateManager){
        super(stateManager);

        //Initialisiere die Textures
        this.endImageTexture = new Texture("gameoverFont.png");
        this.closeBtnTexture = new Texture("close.png");

        //Initialisiere das Bitmap
        this.font = new BitmapFont();
        this.font.setColor(Color.ORANGE);
        this.font.getData().setScale(5);

        //Initialisiere den Close Button
        this.closeBtnRegion = new TextureRegion(closeBtnTexture);
        this.closeBtnRegionDrawable = new TextureRegionDrawable(closeBtnRegion);
        this.closeBtn = new ImageButton(closeBtnRegionDrawable);
        this.closeBtn.setPosition(900 , Gdx.graphics.getHeight()-200);
        this.closeBtn.setTransform(true);
        this.closeBtn.setScale(3f);

        //Initialisiere das Logo
        this.logoRegion = new TextureRegion(endImageTexture);
        this.logoRegionDrawable = new TextureRegionDrawable(logoRegion);
        this.logo = new ImageButton(logoRegionDrawable);
        this.logo.setPosition( 50 , Gdx.graphics.getHeight()-500);
        this.logo.setTransform(true);
        this.logo.setScale(3.5f);

        //Initialisiere die Stage und füge das Logo und den Close Button hinzu
        this.stage = new Stage(new ScreenViewport());
        this.stage.addActor(closeBtn);
        this.stage.addActor(logo);
        Gdx.input.setInputProcessor(stage);

        //EventListener für den Close Button
        onClickCloseButton();

        Gdx.app.log("Highscore", String.valueOf(highscore));
    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * Checke den Input des Users
     */
    @Override
    public void handleInput() {

    }

    /**
     * update()
     *
     * @param deltaTime deltaTime
     */
    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    /**
     * Render-Methode. Hier werden die Textures und die Stage gezeichnet, sowie die Scores aus dem PlayState
     * geholt und angezeigt.
     *
     * @param batch batch
     */
    @Override
    public void render(SpriteBatch batch) {
        //Repositioniert die Kamera
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());

        //Initialisiere den Batch
        batch.begin();

        //Hole die Scores aus dem PlayState und speichere sie in den Preferences
        this.highscore = prefs.getInteger("highscore");
        this.currentScore = prefs.getInteger("currentscore");
        this.prefs.flush();

        this.font.draw(batch, "Du hast folgende Punktzahl\nerreicht: " + currentScore, 50, Gdx.graphics.getHeight()-600);
        this.font.draw(batch, "Dein aktueller Highscore\nliegt bei: " + highscore, 50, Gdx.graphics.getHeight()-900);

        //Schreibe den neuen Highscore in die Preferecenes
        prefs.putInteger("newHS", highscore);
        this.prefs.flush();


        //Beende den Batch
        batch.end();

        //Initialisiere Stage mit neuem Batch
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();
    }

    /**
     * Wenn zu einem anderen State gewechselt wird, rufen wir diese Methode auf, um die Textures zu entfernen
     */
    @Override
    public void dispose() {
        Gdx.app.log("GameOverState","GameOverState disposed");
    }

    /**
     * EventListener für den Close-Button, um wieder auf den Hauptscreen (InitialState) zu gelangen
     */
    private void onClickCloseButton(){
       closeBtn.addListener(new EventListener() {
           @Override
           public boolean handle(Event event) {
               stateManager.set(new InitialState(stateManager));
               return false;
           }
       });
    }
}
