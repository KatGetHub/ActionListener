package gdx.ani1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GdxAni1 extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    Sprite sprVlad;
    Texture txSheet, txTemp, txOne;
    Texture txtFront, txtBack, txtRight, txtLeft;
    Animation araniVlad[];
    TextureRegion trTemp; // a single temporary texture region
    int fW, fH, fSx, fSy; // height and width of SpriteSheet image - and the starting upper coordinates on the Sprite Sheet
    int nFrame, nPos;
    float spriteSpeed = 10.0f; // 10 pixels per second.
    float spriteX;
    float spriteY;
    int nDx, nDy, nDir;
    Texture BackGround, BackGround2;
   

    @Override
    public void create() {
        Gdx.input.setInputProcessor((this));
        nFrame = 0;
        nPos = 0; // the position in the SpriteSheet - 0 to 7
        araniVlad = new Animation[4];
        batch = new SpriteBatch();
        txSheet = new Texture("bob3.png");
        txtFront = new Texture("front2.png");
        txtBack = new Texture("back2.png");
        txtRight = new Texture("right2.png");
        txtLeft = new Texture("left2.png");
 //      BackGround = new Texture("pic2.png");
 //       BackGround2 = new Texture("town.png");
        BackGround = new Texture(Gdx.files.internal("pic2.png"));
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
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nFrame++;
        if (nFrame > 28) {
            nFrame = 0;
        }
//        System.out.println(nPos + " " + nFrame);
        trTemp = araniVlad[nPos].getKeyFrame(nFrame, true);
        batch.begin();
        nDx = 0;
        nDy = 0;
        nDir = 0;


        if (Gdx.input.isKeyPressed(Keys.S)) {
            batch.draw(BackGround, 0, 0, 800, 500);
            nDy = -3;
            nPos = 0;
            batch.draw(trTemp, spriteX, spriteY, 40, 70);
        } else if (Gdx.input.isKeyPressed(Keys.W)) {
            batch.draw(BackGround, 0, 0, 800, 500);
            nDy = 3;
            nPos = 1;
            batch.draw(trTemp, spriteX, spriteY, 40, 70);
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            batch.draw(BackGround, 0, 0, 800, 500);
            nDx = -3;
            nPos = 2;
            batch.draw(trTemp, spriteX, spriteY, 40, 70);
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
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

        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
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
