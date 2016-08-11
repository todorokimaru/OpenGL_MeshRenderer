package com.example.maedahiromu.myapplication;

/**
 * Created by Maeda Hiromu on 2016/08/11.
 */
//メッシュ
public class Mesh {
    public VertexBuffer vertexBuffer;//頂点バッファ
    public IndexBuffer  indexBuffer; //インデックスバッファ
    public Material     material;    //マテリアル

    //描画
    public void draw() {
        material.bind();
        vertexBuffer.bind();
        indexBuffer.draw();
        vertexBuffer.unbind();
        material.unbind();
    }
}
