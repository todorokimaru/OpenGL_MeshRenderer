package com.example.maedahiromu.myapplication;

/**
 * Created by Maeda Hiromu on 2016/08/11.
 */
//GLオブジェクト(VertexBuffer/IndexBuffer/Material/Textureの親)
public abstract class GLObject {

    //破棄時に呼ばれる
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        dispose();
    }

    //バインド
    public abstract void bind();

    //アンバインド
    public abstract void unbind();

    //解放
    public abstract void dispose();
}
