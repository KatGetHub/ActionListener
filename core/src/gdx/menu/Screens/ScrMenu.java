package gdx.menu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import gdx.menus.GamMenu;
import gdx.menus.TbMenu;
import gdx.menus.TbsMenu;

public class ScrMenu implements Screen, InputProcessor {

    GamMenu gamMenu;
    TbsMenu tbsMenu;
    TbMenu tbPlay, tbRules;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    Texture BackGround;

    public ScrMenu(GamMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbPlay = new TbMenu("PLAY", tbsMenu);
        tbRules = new TbMenu("CONTROLS", tbsMenu);
        tbPlay.setY(0);
        tbPlay.setX(440);
        tbRules.setY(0);
        tbRules.setX(0);
        stage.addActor(tbPlay);
        stage.addActor(tbRules);
        Gdx.input.setInputProcessor(stage);
        btnPlayListener();
        btnRulesListener();
        BackGround = new Texture(Gdx.files.internal("menu.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1); //Green background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(BackGround, 0, 0, 650, 500);
        batch.end();
        stage.act();
        stage.draw();
    }

    public void btnPlayListener() {
        tbPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gamMenu.updateState(1); // switch to Play screen.
            }
        });
    }

    public void btnRulesListener() {
        tbRules.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gamMenu.updateState(2); // switch to rules screen.
            }
        });
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
