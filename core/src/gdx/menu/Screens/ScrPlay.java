package gdx.menu.Screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import gdx.menus.GamMenu;
import gdx.menus.TbMenu;
import gdx.menus.TbsMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ScrPlay implements Screen, InputProcessor {

    GamMenu gamMenu;
    TbsMenu tbsMenu;
    TbMenu tbMenu, tbGameover;
    SpriteBatch batch;
    BitmapFont screenName;
    Sprite sprVlad, sprPic;
    Texture txSheet, txSheet2, txTemp, txOne;
    Texture txtFront, txtBack, txtRight, txtLeft;
    Animation araniVlad[], aranPic[];
    TextureRegion trTemp, trTemp2; // a single temporary texture region
    int fW, fH, fSx, fSy; // height and width of SpriteSheet image - and the starting upper coordinates on the Sprite Sheet
    int nFrame, nPos;
    float spriteSpeed = 10.0f; // 10 pixels per second.
    float spriteX = 400;
    float spriteY = 200;
    float spriteX2 = 100;
    float spriteY2 = 100;
    int nDx, nDy, nDir;
    int nPixRenderTimer = 0;
    int nDirPixX, nDirPixY;       // Direction Pix wants to walk in.
    double nDeltaPixX, nDeltaPixY;
    double dPixAngle;
    int nPixXi, nPixYi;   // Pix X and Y increment
    Texture BackGround;

    public ScrPlay(GamMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
        Gdx.input.setInputProcessor((this));
        nFrame = 0;
        nPos = 0; // the position in the SpriteSheet - 0 to 7
        araniVlad = new Animation[4];
        aranPic = new Animation[4];
        batch = new SpriteBatch();
        txSheet = new Texture("bob3.png");
        txtFront = new Texture("front2.png");
        txtBack = new Texture("back2.png");
        txtRight = new Texture("right2.png");
        txtLeft = new Texture("left2.png");
        txSheet2 = new Texture("pic4.png");
        BackGround = new Texture(Gdx.files.internal("town.png"));
        fW = txSheet.getWidth() / 4;
        fH = txSheet.getHeight() / 4;
        System.out.println(fW + " " + fH);
        for (int i = 0; i < 4; i++) {
            Sprite[] arSprVlad = new Sprite[4];
            for (int j = 0; j < 4; j++) {
                fSx = j * fW;
                fSy = i * fH;
                sprVlad = new Sprite(txSheet, fSx, fSy, fW, fH);
                arSprVlad[j] = new Sprite(sprVlad);
            }
            araniVlad[i] = new Animation(7f, arSprVlad);

        }
        fW = txSheet2.getWidth() / 4;
        fH = txSheet2.getHeight() / 4;
        for (int i = 0; i < 4; i++) {
            Sprite[] arSprPic = new Sprite[4];
            for (int j = 0; j < 4; j++) {
                fSx = j * fW;
                fSy = i * fH;
                sprPic = new Sprite(txSheet2, fSx, fSy, fW, fH);
                arSprPic[j] = new Sprite(sprPic);
            }
            aranPic[i] = new Animation(7f, arSprPic);

        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nFrame++;
        if (nFrame > 28) {
            nFrame = 0;
        }
        trTemp = araniVlad[nPos].getKeyFrame(nFrame, true);
        trTemp2 = aranPic[nPos].getKeyFrame(nFrame, true);
        batch.begin();
        nDx = 0;
        nDy = 0;
        nDir = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            batch.draw(BackGround, 0, 0, 800, 500);
            nDy = -3;
            nPos = 0;
            batch.draw(trTemp, spriteX, spriteY, 40, 70);
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            batch.draw(BackGround, 0, 0, 800, 500);
            nDy = 3;
            nPos = 1;
            batch.draw(trTemp, spriteX, spriteY, 40, 70);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            batch.draw(BackGround, 0, 0, 800, 500);
            nDx = -3;
            nPos = 2;
            batch.draw(trTemp, spriteX, spriteY, 40, 70);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            batch.draw(BackGround, 0, 0, 800, 500);
            nDx = 3;
            nPos = 3;
            batch.draw(trTemp, spriteX, spriteY, 40, 70);
        } else {
            batch.draw(BackGround, 0, 0, 800, 500);
            if (nPos == 0) {
                batch.draw(txtFront, spriteX, spriteY, 40, 60);
            }
            if (nPos == 1) {
                batch.draw(txtBack, spriteX, spriteY, 40, 60);
            }
            if (nPos == 2) {
                batch.draw(txtLeft, spriteX, spriteY, 40, 55);
            }
            if (nPos == 3) {
                batch.draw(txtRight, spriteX, spriteY, 40, 55);
            }

        }
        
        nPixRenderTimer++;
        if (nPixRenderTimer == 6) {
            nPixRenderTimer = 0;
            
            nDirPixX = 1;
            if (spriteX < spriteX2) nDirPixX = -1;
            nDirPixY = 1;
            if (spriteY < spriteY2) nDirPixY = -1;
            
            nDeltaPixX = abs(spriteX2 - spriteX);
            nDeltaPixY = abs(spriteY2 - spriteY);
            
            dPixAngle = atan(nDeltaPixX / nDeltaPixY);
            
            nPixXi = (int) (5 * sin(dPixAngle) * nDirPixX);
            nPixYi = (int) (5 * cos(dPixAngle) * nDirPixY);
            
            spriteX2 += nPixXi;
            spriteY2 += nPixYi;
            
        }
        
        batch.draw(trTemp2, spriteX2, spriteY2, 40, 40);

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            batch.draw(BackGround, 0, 0, 800, 500);
            batch.draw(trTemp, spriteX, spriteY, 40, 70);
            nDx = (nDx * 2);
            nDy = (nDy * 2);
        }
        spriteX += nDx;
        spriteY += nDy;
        batch.end();

    }

    @Override
    public void show() {
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
