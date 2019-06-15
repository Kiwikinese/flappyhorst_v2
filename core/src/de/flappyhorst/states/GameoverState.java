package de.flappyhorst.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameoverState extends State {
    /**
     * Konstruktor
     *
     * @param stateManager  stateManager
     *
     */
    private Texture backgroundImage;
    private Texture endImage;
    private BitmapFont font;
    Preferences prefs = Gdx.app.getPreferences("My Preferences");
    private Integer punktzahl;


    public GameoverState(StateManager stateManager){
        super(stateManager);
        backgroundImage = new Texture("background_small.JPG");
        endImage = new Texture("gameoverFont.png");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(5);
        punktzahl = prefs.getInteger("score");
        Gdx.app.log("Test", String.valueOf(punktzahl));
    }
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            stateManager.set(new PlayState(stateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput(); //Checkt den Input des Users
        punktzahl = prefs.getInteger("score");
    }
    @Override
    public void render(SpriteBatch batch) {
        //Repositioniert die Kamera
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(endImage,  50 , Gdx.graphics.getHeight()-750,Gdx.graphics.getWidth()-100, 300 );
        font.draw(batch, "SCORE:" + punktzahl, 10, Gdx.graphics.getHeight()-1000);
        //Beende den Batch
        batch.end();
    }

    /**
     * Wenn zu einem anderen State gewechselt wird, rufen wir diese Methode auf, um die Textures zu entfernen
     */
    @Override
    public void dispose() {
        System.out.println("Gameover State disposed");
    }
}
