package de.flappyhorst.states;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

//=========================================================================================//
//                                     InitialState                                        //
//=========================================================================================//

/**
 * Hauptmenü des Spiels. Von hier aus kann über den Play-Button das Spiel gestartet werden. Über den
 * Score-Button können sich die aktuellen Scores angezeigt werden lassen.
 * Auch die Musik kann über die Sound-Buttons eingeschaltet oder ausgeschaltet werden.
 *
 */
public class InitialState extends State{

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Hintergrundbild
     */
    private Texture backgroundImage;

    /**
     * Play-Button, um das Spiel zu starten
     */
    private Texture playBtnTexture;

    /**
     * Button, um auf dem Highscore-Screen zu landen
     */
    private Texture highscoreBtn;

    /**
     * Logo des Spiels
     */
    private Texture logo;

    /**
     * Texture für den On- und Off Button zum Abspielen/Stoppen der Musik
     */
    private Texture volumeOnTexture, volumeOffTexture;

    /**
     * Musik des Spiels
     */
    private Music song;

    /**
     * Stage
     */
    private Stage stage;

    /**
     * TextureRegion für die Buttons
     */
    private TextureRegion volumeOnBtnRegion, volumeOffBtnRegion;

    /**
     * TextureRegionDrawable für die Buttons
     */
    private TextureRegionDrawable volumeOnBtnRegionDrawable, volumeOffBtnRegionDrawable;

    /**
     * ImageButton für die Buttons
     */
    private ImageButton volumeOnBtn, volumeOffBtn;

    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor der Klasse InitialState. Hier werden die Textures und die Buttons initialisiert.
     * Auch der OnClick-Listener bzw. Eventlistener für Buttons wird hier aufgerufen.
     *
     * @param stateManager  stateManager
     */
    public InitialState(StateManager stateManager){
        super(stateManager);

        //Textures initialisieren
        this.backgroundImage = new Texture("flappy_horst_background.png");
        this.playBtnTexture = new Texture("initial_screen_playbtn.png");
        this.highscoreBtn = new Texture("initial_screen_highscorebtn.png");
        this.logo = new Texture("logo.png");
        this.volumeOnTexture = new Texture("volume_on.png");
        this.volumeOffTexture = new Texture("volume_off.png");

        //Initialisiere den VolumeOn-Button
        this.volumeOnBtnRegion = new TextureRegion(volumeOnTexture);
        this.volumeOnBtnRegionDrawable = new TextureRegionDrawable(volumeOnBtnRegion);
        this.volumeOnBtn = new ImageButton(volumeOnBtnRegionDrawable);
        this.volumeOnBtn.setPosition(900, 1600);
        this.volumeOnBtn.setTransform(true);
        this.volumeOnBtn.setScale(0.2f);

        //Initialisiere den VolumeOff-Button
        this.volumeOffBtnRegion = new TextureRegion(volumeOffTexture);
        this.volumeOffBtnRegionDrawable = new TextureRegionDrawable(volumeOffBtnRegion);
        this.volumeOffBtn = new ImageButton(volumeOffBtnRegionDrawable);
        this.volumeOffBtn.setPosition(100, 1600);
        this.volumeOffBtn.setTransform(true);
        this.volumeOffBtn.setScale(0.2f);

        //Initialisiere die Stage und füge sämtliche Buttons der Klasse InitialState hinzu
        this.stage = new Stage(new ScreenViewport());
        this.stage.addActor(volumeOnBtn);
        this.stage.addActor(volumeOffBtn);
        Gdx.input.setInputProcessor(stage);

        //Song initialisieren
        initializeSong();

        //EventListener für die verschiedenen Buttons
        onClickVolumeOnButton();
        onClickVolumeOffButton();
    }

    //========================================================================//
    //                             Methoden                                   //
    //========================================================================//

    /**
     * Checkt den Input des Users
     */
    @Override
    public void handleInput() {
       if(Gdx.input.getX() < 490 && Gdx.input.getY() > 1450){
           this.stateManager.set(new PlayState(stateManager));
        }else if(Gdx.input.getX() > 560 && Gdx.input.getY() > 1450){
           this.stateManager.set(new GameoverState(stateManager));
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
    }

    /**
     * Batch initsialisieren und die Textures bzw. Buttons zeichnen
     *
     * @param batch batch
     */
    @Override
    public void render(SpriteBatch batch) {
        //Initialisiere den Batch
        batch.begin();

        //Zeichne das Hintergrundbild an die Position 0, 0 und setze die Breite je nach Größe des Bildschirms
        batch.draw(this.backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Zeichne sämtliche Textures
        batch.draw(this.playBtnTexture, Gdx.graphics.getWidth() / 2 -460, Gdx.graphics.getHeight() / 2 - 800, 400, 250);
        batch.draw(this.highscoreBtn, Gdx.graphics.getWidth() / 2 + 60 , Gdx.graphics.getHeight() / 20, 400, 250);
        batch.draw(this.logo, 50, Gdx.graphics.getHeight() - 700,  Gdx.graphics.getWidth()-50, 300);

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
        this.backgroundImage.dispose();
        this.playBtnTexture.dispose();
        this.volumeOffTexture.dispose();
        this.volumeOnTexture.dispose();
        this.stage.dispose();
        this.song.dispose();

        Gdx.app.log("InitialState","InitialState disposed");
    }

    /**
     * Initialisiere die Musik des Spiels
     */
    public void initializeSong(){
        this.song = Gdx.audio.newMusic(Gdx.files.internal("_pokemon_theme.mp3"));
        this.song.setLooping(true);
        this.song.setVolume(0.1f);	//10% von max. 100% Lautstärke
    }

    /**
     * EventListener für den VolumeOn-Button um die Musik abzuspielen
     */
    private void onClickVolumeOnButton(){
        this.volumeOnBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                song.play();
                Gdx.app.log("VolumeOnButton", "VolumeOnButton wurde gedrückt!");
                return true;
            }
        });
    }

    /**
     * EventListener für den VolumeOff-Button um die Musik zu stoppen
     */
    private void onClickVolumeOffButton(){
        this.volumeOffBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                song.stop();
                Gdx.app.log("VolumeOffButton", "VolumeOffButton wurde gedrückt!");
                return true;
            }
        });
    }
}
