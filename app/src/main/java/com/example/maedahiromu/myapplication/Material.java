package com.example.maedahiromu.myapplication;

/**
 * Created by Maeda Hiromu on 2016/08/11.
 */
import android.opengl.GLES20;

//マテリアル
public class Material extends GLObject {
    public Texture texture;              //テクスチャ
    public float[] ambient =new float[4];//環境光
    public float[] diffuse =new float[4];//拡散光
    public float[] specular=new float[4];//鏡面光
    public float   shininess;            //鏡面反射角度

    //バインド
    @Override
    public void bind() {
        GLES20.glUniform4fv(GLES.materialAmbientHandle,1,ambient,0);
        GLES20.glUniform4fv(GLES.materialDiffuseHandle,1,diffuse,0);
        GLES20.glUniform4fv(GLES.materialSpecularHandle,1,specular,0);
        GLES20.glUniform1f(GLES.materialShininessHandle,shininess);
        if (texture!=null) texture.bind();
    }

    //アンバインド
    @Override
    public void unbind() {
        if (texture!=null) texture.unbind();
    }

    //破棄
    @Override
    public void dispose() {
        if (texture!=null) {
            texture.dispose();
            texture=null;
        }
    }
}
