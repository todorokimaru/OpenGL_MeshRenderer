package com.example.maedahiromu.myapplication;

/**
 * Created by Maeda Hiromu on 2016/08/11.
 */
import java.util.ArrayList;
import java.util.HashMap;

//フィギュア
public class Figure {
    public HashMap<String,Material> materials;//マテリアル群
    public ArrayList<Mesh>          meshs;    //メッシュ群

    //描画
    public void draw() {
        for (Mesh mesh:meshs) mesh.draw();
    }
}
