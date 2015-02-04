package android.support.v4.app;

import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.view.Window;
import android.app.Activity;

class ActionBarDrawerToggle$SlideDrawable extends InsetDrawable implements android.graphics.drawable.Drawable$Callback
{
    private final boolean mHasMirroring;
    private float mOffset;
    private float mPosition;
    private final Rect mTmpRect;
    final ActionBarDrawerToggle this$0;

    private ActionBarDrawerToggle$SlideDrawable(ActionBarDrawerToggle  r1, Drawable  r2)
    {
        super(r2, (int) 0);

        boolean z0;
        z0 = false;
        this$0 = r1;
        this.<init>(r2, (int) 0);

        if (Build$VERSION.SDK_INT > 18)
        {
            z0 = true;
        }

        mHasMirroring = z0;
        mTmpRect = new Rect();
    }

    ActionBarDrawerToggle$SlideDrawable(ActionBarDrawerToggle  r1, Drawable  r2, ActionBarDrawerToggle$1  r3)
    {
        this(r1, r2);


        this.<init>(r1, r2);
    }

    public void draw(Canvas  r1)
    {

        byte b0, b1;
        int i2;
        b0 = (byte) (byte) 1;
        this.copyBounds(mTmpRect);
        r1.save();

        if (ViewCompat.getLayoutDirection(ActionBarDrawerToggle.access$400(this$0).getWindow().getDecorView()) != 1)
        {
            b1 = (byte) (byte) 0;
        }
        else
        {
            b1 = (byte) (byte) 1;
        }

        if (b1 != (byte) 0)
        {
            b0 = (byte) (byte) -1;
        }

        i2 = mTmpRect.width();
        r1.translate((- (mOffset)) * (float) i2 * mPosition * (float) b0, 0.0F);

        if (b1 != (byte) 0)
        {
            if (mHasMirroring == false)
            {
                r1.translate((float) i2, 0.0F);
                r1.scale(-1.0F, 1.0F);
            }
        }

        this.draw(r1);
        r1.restore();
    }

    public float getPosition()
    {


        return mPosition;
    }

    public void setOffset(float  f0)
    {


        mOffset = f0;
        this.invalidateSelf();
    }

    public void setPosition(float  f0)
    {


        mPosition = f0;
        this.invalidateSelf();
    }
}
