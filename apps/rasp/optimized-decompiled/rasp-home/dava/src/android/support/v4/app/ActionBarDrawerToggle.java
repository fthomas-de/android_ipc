package android.support.v4.app;

import android.support.v4.widget.DrawerLayout$DrawerListener;
import android.os.Build$VERSION;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.content.res.Configuration;
import android.view.View;
import android.view.MenuItem;

public class ActionBarDrawerToggle implements android.support.v4.widget.DrawerLayout$DrawerListener
{
    private static final int ID_HOME = 16908332;
    private static final ActionBarDrawerToggle$ActionBarDrawerToggleImpl IMPL;
    private static final float TOGGLE_DRAWABLE_OFFSET = 0.333333f;
    private final Activity mActivity;
    private final ActionBarDrawerToggle$Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private final int mOpenDrawerContentDescRes;
    private Object mSetIndicatorInfo;
    private ActionBarDrawerToggle$SlideDrawable mSlider;
    private Drawable mThemeImage;

    static
    {

        Object n0;
        int i0;
        n0 = null;
        i0 = Build$VERSION.SDK_INT;

        if (i0 < 18)
        {
            if (i0 < 11)
            {
                IMPL = new ActionBarDrawerToggle$ActionBarDrawerToggleImplBase(n0);
            }
            else
            {
                IMPL = new ActionBarDrawerToggle$ActionBarDrawerToggleImplHC(n0);
            }
        }
        else
        {
            IMPL = new ActionBarDrawerToggle$ActionBarDrawerToggleImplJellybeanMR2(n0);
        }
    }

    public ActionBarDrawerToggle(Activity  r1, DrawerLayout  r2, int  i0, int  i1, int  i2)
    {

        Object n0;
        n0 = null;
        this.<init>();
        mDrawerIndicatorEnabled = true;
        mActivity = r1;

        if (r1 instanceof ActionBarDrawerToggle$DelegateProvider == false)
        {
            mActivityImpl = n0;
        }
        else
        {
            mActivityImpl = ((ActionBarDrawerToggle$DelegateProvider) r1).getDrawerToggleDelegate();
        }

        mDrawerLayout = r2;
        mDrawerImageResource = i0;
        mOpenDrawerContentDescRes = i1;
        mCloseDrawerContentDescRes = i2;
        mThemeImage = this.getThemeUpIndicator();
        mDrawerImage = r1.getResources().getDrawable(i0);
        mSlider = new ActionBarDrawerToggle$SlideDrawable(this, mDrawerImage, n0);
        mSlider.setOffset(0.333333F);
    }

    static Activity access$400(ActionBarDrawerToggle  r0)
    {


        return r0.mActivity;
    }

    Drawable getThemeUpIndicator()
    {

        Drawable r4;
        if (mActivityImpl == null)
        {
            r4 = IMPL.getThemeUpIndicator(mActivity);
        }
        else
        {
            r4 = mActivityImpl.getThemeUpIndicator();
        }

        return r4;
    }

    public boolean isDrawerIndicatorEnabled()
    {


        return mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration  r1)
    {


        mThemeImage = this.getThemeUpIndicator();
        mDrawerImage = mActivity.getResources().getDrawable(mDrawerImageResource);
        this.syncState();
    }

    public void onDrawerClosed(View  r1)
    {


        mSlider.setPosition(0.0F);

        if (mDrawerIndicatorEnabled != false)
        {
            this.setActionBarDescription(mOpenDrawerContentDescRes);
        }
    }

    public void onDrawerOpened(View  r1)
    {


        mSlider.setPosition(1.0F);

        if (mDrawerIndicatorEnabled != false)
        {
            this.setActionBarDescription(mCloseDrawerContentDescRes);
        }
    }

    public void onDrawerSlide(View  r1, float  f0)
    {

        float f3, f8;
        f3 = mSlider.getPosition();

        if (f0 - 0.5F <= 0)
        {
            f8 = Math.min(f3, f0 * 2.0F);
        }
        else
        {
            f8 = Math.max(f3, Math.max(0.0F, f0 - 0.5F) * 2.0F);
        }

        mSlider.setPosition(f8);
    }

    public void onDrawerStateChanged(int  i0)
    {

    }

    public boolean onOptionsItemSelected(MenuItem  r1)
    {

        boolean z2;
        label_0:
        {
            if (r1 != null)
            {
                if (r1.getItemId() == 16908332)
                {
                    if (mDrawerIndicatorEnabled != false)
                    {
                        if (mDrawerLayout.isDrawerVisible(8388611) == false)
                        {
                            mDrawerLayout.openDrawer(8388611);
                        }
                        else
                        {
                            mDrawerLayout.closeDrawer(8388611);
                        }

                        z2 = true;
                        break label_0;
                    }
                }
            }

            z2 = false;
        } //end label_0:


        return z2;
    }

    void setActionBarDescription(int  i0)
    {


        if (mActivityImpl == null)
        {
            mSetIndicatorInfo = IMPL.setActionBarDescription(mSetIndicatorInfo, mActivity, i0);
        }
        else
        {
            mActivityImpl.setActionBarDescription(i0);
        }
    }

    void setActionBarUpIndicator(Drawable  r1, int  i0)
    {


        if (mActivityImpl == null)
        {
            mSetIndicatorInfo = IMPL.setActionBarUpIndicator(mSetIndicatorInfo, mActivity, r1, i0);
        }
        else
        {
            mActivityImpl.setActionBarUpIndicator(r1, i0);
        }
    }

    public void setDrawerIndicatorEnabled(boolean  z0)
    {

        ActionBarDrawerToggle$SlideDrawable r1;
        int i1;
        if (z0 != mDrawerIndicatorEnabled)
        {
            if (z0 == false)
            {
                this.setActionBarUpIndicator(mThemeImage, 0);
            }
            else
            {
                r1 = mSlider;

                if (mDrawerLayout.isDrawerOpen(8388611) == false)
                {
                    i1 = mOpenDrawerContentDescRes;
                }
                else
                {
                    i1 = mCloseDrawerContentDescRes;
                }

                this.setActionBarUpIndicator(r1, i1);
            }

            mDrawerIndicatorEnabled = z0;
        }
    }

    public void syncState()
    {

        ActionBarDrawerToggle$SlideDrawable r3;
        int i1;
        if (mDrawerLayout.isDrawerOpen(8388611) == false)
        {
            mSlider.setPosition(0.0F);
        }
        else
        {
            mSlider.setPosition(1.0F);
        }

        if (mDrawerIndicatorEnabled != false)
        {
            r3 = mSlider;

            if (mDrawerLayout.isDrawerOpen(8388611) == false)
            {
                i1 = mOpenDrawerContentDescRes;
            }
            else
            {
                i1 = mCloseDrawerContentDescRes;
            }

            this.setActionBarUpIndicator(r3, i1);
        }
    }
}
