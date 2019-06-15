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
    private BitmapFont font;
    Preferences prefs = Gdx.app.getPreferences("My Preferences");
    private String punktzahl;


    public GameoverState(StateManager stateManager){
        super(stateManager);
        backgroundImage = new Texture("background_small.JPG");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);
        punktzahl = prefs.getString("score");
        Gdx.app.log("Test", punktzahl);
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
    }
    @Override
    public void render(SpriteBatch batch) {
        //Repositioniert die Kamera
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, punktzahl, 10, Gdx.graphics.getHeight()-100);
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
