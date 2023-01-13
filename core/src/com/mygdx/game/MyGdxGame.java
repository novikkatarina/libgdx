package com.mygdx.game;

import Main.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import org.w3c.dom.ls.LSOutput;


import java.io.IOException;
import java.util.*;


public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Map<String, MyAtlasAnim> animMap = new HashMap<>();
    TextureAtlas atlas;
    ArrayList<Base> leftSide = new ArrayList<>();
    ArrayList<Base> rightSide = new ArrayList<>();
    static final float SCALE = (float) 1f;
    BitmapFont font;
    float time;
    float time1;

    int previousIndex = 0;

    ArrayList<Base> sortedWhiteTeam;
    ArrayList<Base> sortedBlackTeam;


    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(Color.ROYAL);
        font.getData().setScale(1, 2);

        batch = new SpriteBatch();
        img = new Texture("background.png");
        atlas = new TextureAtlas("atlas/moving.atlas");
        int fps = 4;
        animMap.put("stay1", new MyAtlasAnim(atlas, "sitting1", fps, Animation.PlayMode.LOOP));
        animMap.put("stay2", new MyAtlasAnim(atlas, "walking", fps, Animation.PlayMode.LOOP));
        animMap.put("attack1", new MyAtlasAnim(atlas, "attack", fps, Animation.PlayMode.LOOP));
        animMap.put("attack2", new MyAtlasAnim(atlas, "attack2", fps, Animation.PlayMode.LOOP));
        animMap.put("dead1", new MyAtlasAnim(atlas, "Dead", fps, Animation.PlayMode.LOOP));
        animMap.put("dead2", new MyAtlasAnim(atlas, "dead2", fps, Animation.PlayMode.LOOP));

        leftSide.add(new Peasant(1, 1, 4));
        leftSide.add(new Robber(1, 2, 3));
        leftSide.add(new Sniper(1, 3, 2));
        leftSide.add(new Wizard(1, 4, 1));

        rightSide.add(new Peasant(10, 1, 4)); // 600 100
        rightSide.add(new Monk(10, 2, 3)); // 600
        rightSide.add(new Spearman(10, 3, 2));
        rightSide.add(new Xbowman(10, 4, 1));

        sortedWhiteTeam = Base.sortedWhiteTeam(leftSide);
        sortedBlackTeam = Base.sortedBlackTeam(rightSide);
        System.out.println(sortedBlackTeam);
    }


    @Override
    public void render() {

        ScreenUtils.clear(1, 0, 0, 1);

        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();

        animMap.get("stay1").setTime(Gdx.graphics.getDeltaTime());
        animMap.get("attack1").setTime(Gdx.graphics.getDeltaTime());
        animMap.get("stay2").setTime(Gdx.graphics.getDeltaTime());
        animMap.get("attack2").setTime(Gdx.graphics.getDeltaTime());

        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        time1 += Gdx.graphics.getDeltaTime();
        int currentIndex = Math.round(time1) % rightSide.size();
        if (currentIndex != previousIndex) {
            previousIndex = currentIndex;
            System.out.println("=====================================================================================");
            System.out.println("=====================================================================================");
            for (int k = 0; k < 4; k++) {
                System.out.println(sortedWhiteTeam.get(k).toString());
            }
            System.out.println("=====================================================================================");

            for (int k = 0; k < 4; k++) {
                System.out.println(sortedBlackTeam.get(k).toString());
            }
            System.out.println("=====================================================================================");
            System.out.println("=====================================================================================");

            sortedBlackTeam.get(currentIndex).step(leftSide, rightSide);
            sortedWhiteTeam.get(currentIndex).step(rightSide, leftSide);

        }

        for (int i = 0; i < rightSide.size(); i++) {
//            Base currentRight = sortedBlackTeam.get(i);
//            Base currentLeft = sortedWhiteTeam.get(i);
//
//            int rightX = currentRight.position.x;
//            int rightY = currentRight.position.y;
//
//            int leftX = currentLeft.position.x;
//            int leftY = currentLeft.position.y;
            if (sortedWhiteTeam.get(i).getStatus().equals("DEAD")){
                batch.draw(animMap.get("dead1").getFrame(), sortedWhiteTeam.get(i).position.x * 100, (sortedWhiteTeam.get(i).position.y - 1) * 180, 180, 180);
            }
            else {
                if (i == currentIndex) {
                    batch.draw(animMap.get("attack1").getFrame(), sortedWhiteTeam.get(i).position.x * 100, (sortedWhiteTeam.get(i).position.y - 1) * 180, 180, 180);
                } else {
                    batch.draw(animMap.get("stay1").getFrame(), sortedWhiteTeam.get(i).position.x * SCALE * 100, (sortedWhiteTeam.get(i).position.y - 1) * SCALE * 180, 80, 160);
                }
            }


            if (sortedBlackTeam.get(i).getStatus().equals("DEAD")){
                batch.draw(animMap.get("dead1").getFrame(), sortedBlackTeam.get(i).position.x * 100, (sortedBlackTeam.get(i).position.y - 1) * 180, 180, 180);
            }
            else {
                if (i == currentIndex) {
                    batch.draw(animMap.get("attack2").getFrame(), sortedBlackTeam.get(i).position.x * 100, (sortedBlackTeam.get(i).position.y - 1) * 180, -180, 180);
                }
                else {
                    batch.draw(animMap.get("stay2").getFrame(), sortedBlackTeam.get(i).position.x * SCALE * 100, (sortedBlackTeam.get(i).position.y-1) * SCALE * 180, -80, 160);
                }
            }

//            if (i == currentIndex) {
//                batch.draw(animMap.get("attack1").getFrame(), sortedWhiteTeam.get(i).position.x * 100, (sortedWhiteTeam.get(i).position.y - 1) * 180, 180, 180);
//                batch.draw(animMap.get("attack2").getFrame(), sortedBlackTeam.get(i).position.x * 100, (sortedBlackTeam.get(i).position.y - 1) * 180, -180, 180);
//            } else {
//                batch.draw(animMap.get("stay1").getFrame(), sortedWhiteTeam.get(i).position.x * SCALE * 100, (sortedWhiteTeam.get(i).position.y-1) * SCALE * 180, 80, 160);
//                batch.draw(animMap.get("stay2").getFrame(), sortedBlackTeam.get(i).position.x * SCALE * 100, (sortedBlackTeam.get(i).position.y-1) * SCALE * 180, -80, 160);
//            }


        }



//        for (int k = 0; k < 4; k++) {
//            System.out.println(sortedWhiteTeam.get(k).toString());
////            font.draw(batch, sortedWhiteTeam.get(k).toString(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
//        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
