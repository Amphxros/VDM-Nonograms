package es.ucm.vdm.engine;

public class Color {
    float r_,g_,b_,a_;
    public Color(float r, float g, float b, float a){
        this.r_=r;
        this.g_=g;
        this.b_=b;
        this.a_=a;
    }
    public float getRed(){return r_;}
    public float getGreen(){return g_;}
    public float getBlue(){return b_;}
    public float getAlpha(){return a_;}



}
