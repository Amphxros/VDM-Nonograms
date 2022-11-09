package vdm.p1.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.HashMap;

import vdm.p1.engine.Color;
import vdm.p1.engine.Dimension;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;

public class AndroidGraphics implements IGraphics {
    private final SurfaceView mView;
    private final SurfaceHolder mHolder;
    private final Paint mPaint;
    private final HashMap<String, AndroidImage> mImages_;
    private final HashMap<String, IFont> mFonts_;
    private final AssetManager mMngr_;
    private Canvas mCanvas;

    public AndroidGraphics(SurfaceView view, Context context, Canvas canvas, Paint paint) {
        this.mView = new SurfaceView(context);
        this.mHolder = this.mView.getHolder();
        this.mPaint = paint;
        this.mCanvas = canvas;
        this.mMngr_ = context.getAssets();

        this.mImages_ = new HashMap<>();
        this.mFonts_ = new HashMap<>();

        setColor(Color.WHITE);
    }

    public SurfaceView getView() {
        return mView;
    }

    public Canvas getCanvas() {
        return mCanvas;
    }

    public void setCanvas(Canvas mCanvas) {
        this.mCanvas = mCanvas;
    }

    @Override
    public void clear(Color color) {
        clear(color.getARGB());
    }

    @Override
    public void clear(int color) {
        this.mCanvas = this.mHolder.lockCanvas();
        this.mCanvas.drawColor(color); // ARGB
    }

    public void present() {
        this.mHolder.unlockCanvasAndPost(mCanvas);
    }

    public boolean surfaceValid() {
        return this.mHolder.getSurface().isValid();
    }

    @Override
    public AndroidImage newImage(String name) {
        AndroidImage aImage = new AndroidImage(name, mMngr_);
        mImages_.put(name, aImage);
        return aImage;
    }

    @Override
    public AndroidFont newFont(String name, int size, boolean isBold) {
        AndroidFont aFont = new AndroidFont(this, name, mMngr_, size, isBold);
        mFonts_.put(name, aFont);
        return aFont;
    }

    @Override
    public Dimension<Integer> getTextDimensions(IFont font, String string) {
        Typeface tf = ((AndroidFont) font).getUnderlyingFont();
        Paint paint = new Paint();
        paint.setTextSize(font.getSize());
        paint.setTypeface(tf);

        Rect result = new Rect();
        paint.getTextBounds(string, 0, string.length(), result);
        return new Dimension<>(result.width(), result.height());
    }

    @Override
    public void drawImage(IImage image, int x, int y) {
        AndroidImage and = (AndroidImage) image;
        mCanvas.drawBitmap(and.getImage(), x, y, mPaint);
    }

    @Override
    public void drawImage(IImage image, int x, int y, int width, int height) {

    }


    public void drawImage(AndroidImage image, int x, int y, int width, int height) {

    }

    @Override
    public void drawText(String text, int x, int y) {
        mCanvas.drawText(text, x, y, mPaint);
    }

    @Override
    public void fillRectangle(int x, int y, int side) {
        fillRectangle(x, y, side, side);
    }

    @Override
    public void fillRectangle(int x, int y, int width, int height) {
        mCanvas.drawRect(x, y, x + width, y + height, mPaint);
    }

    @Override
    public void drawRectangle(int x, int y, int side) {
        drawRectangle(x, y, side, side);
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawRect(x, y, x + width, y + height, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {
        mCanvas.drawLine(initX, initY, endX, endY, mPaint);
    }

    @Override
    public void setResolution(int width, int height) {

    }

    @Override
    public void setColor(int color) {
        mPaint.setColor(Color.rgbaToARGB(color));
    }

    @Override
    public void setColor(Color color) {
        mPaint.setColor(color.getARGB());
    }

    @Override
    public void setFont(IFont font) {
        AndroidFont aFont = (AndroidFont) font;
        mPaint.setTypeface(aFont.getFont());
        mPaint.setTextSize(aFont.getSize());
    }


    @Override
    public void translate(int x, int y) {

    }

    @Override
    public void scale(double x, double y) {

    }

    @Override
    public void save() {
        mCanvas.save();
    }

    @Override
    public void restore() {
        mCanvas.restore();
    }

    @Override
    public int getWidth() {
        return this.mView.getWidth();
    }

    @Override
    public int getHeight() {
        return this.mView.getHeight();
    }
}


/**
 * //Clase interna encargada de obtener el SurfaceHolder y pintar con el canvas
 * class MyRenderClass implements Runnable, IGraphics {
 * <p>
 * private SurfaceView myView;
 * private SurfaceHolder holder;
 * private Canvas canvas;
 * <p>
 * private Thread renderThread;
 * <p>
 * private boolean running;
 * <p>
 * private Paint paint;
 * <p>
 * private Scene scene;
 * <p>
 * public MyRenderClass(SurfaceView myView){
 * this.myView = myView;
 * this.holder = this.myView.getHolder();
 * this.paint = new Paint();
 * this.paint.setColor(0xFFFFFFFF);
 * }
 * <p>
 * public int getWidth(){
 * return this.myView.getWidth();
 * }
 *
 * @Override public void run() {
 * if (renderThread != Thread.currentThread()) {
 * // Evita que cualquiera que no sea esta clase llame a este Runnable en un Thread
 * // Programación defensiva
 * throw new RuntimeException("run() should not be called directly");
 * }
 * <p>
 * // Si el Thread se pone en marcha
 * // muy rápido, la vista podría todavía no estar inicializada.
 * while(this.running && this.myView.getWidth() == 0);
 * // Espera activa. Sería más elegante al menos dormir un poco.
 * <p>
 * long lastFrameTime = System.nanoTime();
 * <p>
 * long informePrevio = lastFrameTime; // Informes de FPS
 * int frames = 0;
 * <p>
 * // Bucle de juego principal.
 * while(running) {
 * long currentTime = System.nanoTime();
 * long nanoElapsedTime = currentTime - lastFrameTime;
 * lastFrameTime = currentTime;
 * <p>
 * // Informe de FPS
 * double elapsedTime = (double) nanoElapsedTime / 1.0E9;
 * this.update(elapsedTime);
 * if (currentTime - informePrevio > 1000000000l) {
 * long fps = frames * 1000000000l / (currentTime - informePrevio);
 * System.out.println("" + fps + " fps");
 * frames = 0;
 * informePrevio = currentTime;
 * }
 * ++frames;
 * <p>
 * // Pintamos el frame
 * while (!this.holder.getSurface().isValid());
 * this.canvas = this.holder.lockCanvas();
 * this.render();
 * this.holder.unlockCanvasAndPost(canvas);
 * <p>
 * /*
 * // Posibilidad: cedemos algo de tiempo. Es una medida conflictiva...
 * try { Thread.sleep(1); } catch(Exception e) {}
 * <p>
 * }
 * }
 * <p>
 * protected void update(double deltaTime) {
 * scene.update(deltaTime);
 * }
 * <p>
 * public void setScene(Scene scene) {
 * this.scene = scene;
 * }
 * <p>
 * protected void renderCircle(float x, float y, float r){
 * canvas.drawCircle(x, y, r, this.paint);
 * }
 * <p>
 * protected void render() {
 * // "Borramos" el fondo.
 * this.canvas.drawColor(0xFFBBAABB); // ARGB
 * scene.render();
 * }
 * <p>
 * public void resume() {
 * if (!this.running) {
 * // Solo hacemos algo si no nos estábamos ejecutando ya
 * // (programación defensiva)
 * this.running = true;
 * // Lanzamos la ejecución de nuestro método run() en un nuevo Thread.
 * this.renderThread = new Thread(this);
 * this.renderThread.start();
 * }
 * }
 * <p>
 * public void pause() {
 * if (this.running) {
 * this.running = false;
 * while (true) {
 * try {
 * this.renderThread.join();
 * this.renderThread = null;
 * break;
 * } catch (InterruptedException ie) {
 * // Esto no debería ocurrir nunca...
 * }
 * }
 * }
 * }
 */