package android.support.v4.widget;

import android.view.ViewGroup;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.view.View;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.view.ViewGroup$LayoutParams;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.KeyEvent;
import android.support.v4.view.KeyEventCompat;
import android.view.View$MeasureSpec;
import android.os.Parcelable;

public class DrawerLayout extends ViewGroup
{
    private static final boolean ALLOW_EDGE_LOCK = false;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;
    private static final int[] LAYOUT_ATTRS;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private final DrawerLayout$ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    private DrawerLayout$DrawerListener mListener;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mMinDrawerMargin;
    private final DrawerLayout$ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowLeft;
    private Drawable mShadowRight;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;

    static
    {

        int[] r0;
        r0 = new int[1];
        r0[0] = 16842931;
        LAYOUT_ATTRS = r0;
    }

    public DrawerLayout(Context  r1)
    {
        this(r1, null);


        this.<init>(r1, null);
    }

    public DrawerLayout(Context  r1, AttributeSet  r2)
    {
        this(r1, r2, 0);


        this.<init>(r1, r2, 0);
    }

    public DrawerLayout(Context  r1, AttributeSet  r2, int  i0)
    {
        super(r1, r2, i0);

        float f1, f3;
        this.<init>(r1, r2, i0);
        mScrimColor = -1728053248;
        mScrimPaint = new Paint();
        mFirstLayout = true;
        f1 = this.getResources().getDisplayMetrics().density;
        mMinDrawerMargin = (int) (64.0F * f1 + 0.5F);
        f3 = 400.0F * f1;
        mLeftCallback = new DrawerLayout$ViewDragCallback(this, 3);
        mRightCallback = new DrawerLayout$ViewDragCallback(this, 5);
        mLeftDragger = ViewDragHelper.create(this, 1.0F, mLeftCallback);
        mLeftDragger.setEdgeTrackingEnabled((int) 1);
        mLeftDragger.setMinVelocity(f3);
        mLeftCallback.setDragger(mLeftDragger);
        mRightDragger = ViewDragHelper.create(this, 1.0F, mRightCallback);
        mRightDragger.setEdgeTrackingEnabled(2);
        mRightDragger.setMinVelocity(f3);
        mRightCallback.setDragger(mRightDragger);
        this.setFocusableInTouchMode(true);
        ViewCompat.setAccessibilityDelegate(this, new DrawerLayout$AccessibilityDelegate(this));
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
    }

    static int[] access$100()
    {


        return LAYOUT_ATTRS;
    }

    static View access$200(DrawerLayout  r0)
    {


        return r0.findVisibleDrawer();
    }

    void cancelChildViewTouch()
    {

        long l0;
        MotionEvent r1;
        int i3, i4;
        if (mChildrenCanceledTouch == false)
        {
            l0 = SystemClock.uptimeMillis();
            r1 = MotionEvent.obtain(l0, l0, 3, 0.0F, 0.0F, 0);
            i3 = this.getChildCount();
            i4 = 0;

            while (i4 < i3)
            {
                this.getChildAt(i4).dispatchTouchEvent(r1);
                i4 = i4 + 1;
            }

            r1.recycle();
            mChildrenCanceledTouch = true;
        }
    }

    boolean checkDrawerViewAbsoluteGravity(View  r1, int  i0)
    {

        boolean z0;
        if ((this.getDrawerViewAbsoluteGravity(r1) & i0) != i0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams  r1)
    {

        boolean z2;
        label_0:
        {
            if (r1 instanceof DrawerLayout$LayoutParams != false)
            {
                if (this.checkLayoutParams(r1) != false)
                {
                    z2 = true;
                    break label_0;
                }
            }

            z2 = false;
        } //end label_0:


        return z2;
    }

    public void closeDrawer(int  i0)
    {

        View r1;
        r1 = this.findDrawerWithGravity(i0);

        if (r1 != null)
        {
            this.closeDrawer(r1);
            return;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No drawer view found with gravity ").append(DrawerLayout.gravityToString(i0)).toString());
        }
    }

    public void closeDrawer(View  r1)
    {

        DrawerLayout$LayoutParams r11;
        if (this.isDrawerView(r1) != false)
        {
            if (mFirstLayout == false)
            {
                if (this.checkDrawerViewAbsoluteGravity(r1, 3) == false)
                {
                    mRightDragger.smoothSlideViewTo(r1, this.getWidth(), r1.getTop());
                }
                else
                {
                    mLeftDragger.smoothSlideViewTo(r1, (- (r1.getWidth())), r1.getTop());
                }
            }
            else
            {
                r11 = (DrawerLayout$LayoutParams) r1.getLayoutParams();
                r11.onScreen = 0.0F;
                r11.knownOpen = false;
            }

            this.invalidate();
            return;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(r1).append(" is not a sliding drawer").toString());
        }
    }

    public void closeDrawers()
    {


        this.closeDrawers(false);
    }

    void closeDrawers(boolean  z0)
    {

        boolean z1;
        int i0, i1, i2;
        View r1;
        DrawerLayout$LayoutParams r3;
        z1 = false;
        i0 = this.getChildCount();
        i1 = 0;

        while (i1 < i0)
        {
            r1 = this.getChildAt(i1);
            r3 = (DrawerLayout$LayoutParams) r1.getLayoutParams();

            label_1:
            if (this.isDrawerView(r1) != false)
            {
                if (z0 != false)
                {
                    if (r3.isPeeking == false)
                    {
                        break label_1;
                    }
                }

                i2 = r1.getWidth();

                if (this.checkDrawerViewAbsoluteGravity(r1, 3) == false)
                {
                    z1 = z1 | mRightDragger.smoothSlideViewTo(r1, this.getWidth(), r1.getTop());
                }
                else
                {
                    z1 = z1 | mLeftDragger.smoothSlideViewTo(r1, (- (i2)), r1.getTop());
                }

                r3.isPeeking = false;
            }

            i1 = i1 + 1;
        }

        mLeftCallback.removeCallbacks();
        mRightCallback.removeCallbacks();

        if (z1 != false)
        {
            this.invalidate();
        }
    }

    public void computeScroll()
    {

        int i0, i1;
        float f0;
        i0 = this.getChildCount();
        f0 = 0.0F;
        i1 = 0;

        while (i1 < i0)
        {
            f0 = Math.max(f0, ((DrawerLayout$LayoutParams) this.getChildAt(i1).getLayoutParams()).onScreen);
            i1 = i1 + 1;
        }

        mScrimOpacity = f0;

        if ((mLeftDragger.continueSettling(true) | mRightDragger.continueSettling(true)) != 0)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void dispatchOnDrawerClosed(View  r1)
    {

        View r3;
        DrawerLayout$LayoutParams r4;
        r4 = (DrawerLayout$LayoutParams) r1.getLayoutParams();

        if (r4.knownOpen != false)
        {
            r4.knownOpen = false;

            if (mListener != null)
            {
                mListener.onDrawerClosed(r1);
            }

            if (this.hasWindowFocus() != false)
            {
                r3 = this.getRootView();

                if (r3 != null)
                {
                    r3.sendAccessibilityEvent(32);
                }
            }
        }
    }

    void dispatchOnDrawerOpened(View  r1)
    {

        DrawerLayout$LayoutParams r3;
        r3 = (DrawerLayout$LayoutParams) r1.getLayoutParams();

        if (r3.knownOpen == false)
        {
            r3.knownOpen = true;

            if (mListener != null)
            {
                mListener.onDrawerOpened(r1);
            }

            this.sendAccessibilityEvent(32);
        }
    }

    void dispatchOnDrawerSlide(View  r1, float  f0)
    {


        if (mListener != null)
        {
            mListener.onDrawerSlide(r1, f0);
        }
    }

    protected boolean drawChild(Canvas  r1, View  r2, long  l0)
    {

        int i1, i2, i3, i4, i5, i6, i8, i9, i15, i16, i18, i45;
        boolean z0, z1;
        View r5;
        float f2, f24;
        i1 = this.getHeight();
        z0 = this.isContentView(r2);
        i2 = 0;
        i3 = this.getWidth();
        i4 = r1.save();

        if (z0 != false)
        {
            i5 = this.getChildCount();
            i6 = 0;

            while (i6 < i5)
            {
                r5 = this.getChildAt(i6);

                if (r5 != r2)
                {
                    if (r5.getVisibility() == 0)
                    {
                        if (DrawerLayout.hasOpaqueBackground(r5) != false)
                        {
                            if (this.isDrawerView(r5) != false)
                            {
                                if (r5.getHeight() >= i1)
                                {
                                    if (this.checkDrawerViewAbsoluteGravity(r5, 3) == false)
                                    {
                                        i9 = r5.getLeft();

                                        if (i9 < i3)
                                        {
                                            i3 = i9;
                                        }
                                    }
                                    else
                                    {
                                        i8 = r5.getRight();

                                        if (i8 > i2)
                                        {
                                            i2 = i8;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                i6 = i6 + 1;
            }

            r1.clipRect(i2, 0, i3, this.getHeight());
        }

        z1 = this.drawChild(r1, r2, l0);
        r1.restoreToCount(i4);

        label_2:
        {
            if (mScrimOpacity - 0.0F > 0)
            {
                if (z0 != false)
                {
                    mScrimPaint.setColor((int) ((float) ((mScrimColor & -16777216) >>> 24) * mScrimOpacity) << 24 | mScrimColor & 16777215);
                    r1.drawRect((float) i2, 0.0F, (float) i3, (float) this.getHeight(), mScrimPaint);
                    break label_2;
                }
            }

            if (mShadowLeft != null)
            {
                if (this.checkDrawerViewAbsoluteGravity(r2, 3) != false)
                {
                    i15 = mShadowLeft.getIntrinsicWidth();
                    i16 = r2.getRight();
                    f2 = Math.max(0.0F, Math.min((float) i16 / (float) mLeftDragger.getEdgeSize(), 1.0F));
                    mShadowLeft.setBounds(i16, r2.getTop(), i16 + i15, r2.getBottom());
                    mShadowLeft.setAlpha((int) (255.0F * f2));
                    mShadowLeft.draw(r1);
                    break label_2;
                }
            }

            if (mShadowRight != null)
            {
                if (this.checkDrawerViewAbsoluteGravity(r2, 5) != false)
                {
                    i45 = mShadowRight.getIntrinsicWidth();
                    i18 = r2.getLeft();
                    f24 = Math.max(0.0F, Math.min((float) (this.getWidth() - i18) / (float) mRightDragger.getEdgeSize(), 1.0F));
                    mShadowRight.setBounds(i18 - i45, r2.getTop(), i18, r2.getBottom());
                    mShadowRight.setAlpha((int) (255.0F * f24));
                    mShadowRight.draw(r1);
                }
            }
        } //end label_2:


        return z1;
    }

    View findDrawerWithGravity(int  i0)
    {

        int i2, i3, i4;
        View r1;
        i2 = GravityCompat.getAbsoluteGravity(i0, ViewCompat.getLayoutDirection(this)) & 7;
        i3 = this.getChildCount();
        i4 = 0;

        label_3:
        {
            while (i4 < i3)
            {
                r1 = this.getChildAt(i4);

                if ((this.getDrawerViewAbsoluteGravity(r1) & 7) != i2)
                {
                    i4 = i4 + 1;
                }
                else
                {
                    break label_3;
                }
            }

            r1 = null;
        } //end label_3:


        return r1;
    }

    View findOpenDrawer()
    {

        int i0, i1;
        View r1;
        i0 = this.getChildCount();
        i1 = 0;

        label_4:
        {
            while (i1 < i0)
            {
                r1 = this.getChildAt(i1);

                if (((DrawerLayout$LayoutParams) r1.getLayoutParams()).knownOpen == false)
                {
                    i1 = i1 + 1;
                }
                else
                {
                    break label_4;
                }
            }

            r1 = null;
        } //end label_4:


        return r1;
    }

    private View findVisibleDrawer()
    {

        int i0, i1;
        View r1;
        i0 = this.getChildCount();
        i1 = 0;

        label_5:
        {
            while (i1 < i0)
            {
                r1 = this.getChildAt(i1);

                if (this.isDrawerView(r1) != false)
                {
                    if (this.isDrawerVisible(r1) != false)
                    {
                        break label_5;
                    }
                }

                i1 = i1 + 1;
            }

            r1 = null;
        } //end label_5:


        return r1;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams()
    {


        return new DrawerLayout$LayoutParams(-1, -1);
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet  r1)
    {


        return new DrawerLayout$LayoutParams(this.getContext(), r1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams  r1)
    {

        DrawerLayout$LayoutParams r2;
        if (r1 instanceof DrawerLayout$LayoutParams == false)
        {
            if (r1 instanceof ViewGroup$MarginLayoutParams == false)
            {
                r2 = new DrawerLayout$LayoutParams(r1);
            }
            else
            {
                r2 = new DrawerLayout$LayoutParams((ViewGroup$MarginLayoutParams) r1);
            }
        }
        else
        {
            r2 = new DrawerLayout$LayoutParams((DrawerLayout$LayoutParams) r1);
        }

        return r2;
    }

    public int getDrawerLockMode(int  i0)
    {

        int i2, i4;
        i2 = GravityCompat.getAbsoluteGravity(i0, ViewCompat.getLayoutDirection(this));

        if (i2 != 3)
        {
            if (i2 != 5)
            {
                i4 = 0;
            }
            else
            {
                i4 = mLockModeRight;
            }
        }
        else
        {
            i4 = mLockModeLeft;
        }

        return i4;
    }

    public int getDrawerLockMode(View  r1)
    {

        int i0, i2;
        i0 = this.getDrawerViewAbsoluteGravity(r1);

        if (i0 != 3)
        {
            if (i0 != 5)
            {
                i2 = 0;
            }
            else
            {
                i2 = mLockModeRight;
            }
        }
        else
        {
            i2 = mLockModeLeft;
        }

        return i2;
    }

    public CharSequence getDrawerTitle(int  i0)
    {

        int i2;
        CharSequence r1;
        i2 = GravityCompat.getAbsoluteGravity(i0, ViewCompat.getLayoutDirection(this));

        if (i2 != 3)
        {
            if (i2 != 5)
            {
                r1 = null;
            }
            else
            {
                r1 = mTitleRight;
            }
        }
        else
        {
            r1 = mTitleLeft;
        }

        return r1;
    }

    int getDrawerViewAbsoluteGravity(View  r1)
    {


        return GravityCompat.getAbsoluteGravity(((DrawerLayout$LayoutParams) r1.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    float getDrawerViewOffset(View  r1)
    {


        return ((DrawerLayout$LayoutParams) r1.getLayoutParams()).onScreen;
    }

    static String gravityToString(int  i0)
    {

        String r0;
        if ((i0 & 3) != 3)
        {
            if ((i0 & 5) != 5)
            {
                r0 = Integer.toHexString(i0);
            }
            else
            {
                r0 = "RIGHT";
            }
        }
        else
        {
            r0 = "LEFT";
        }

        return r0;
    }

    private static boolean hasOpaqueBackground(View  r0)
    {

        boolean z0;
        Drawable r1;
        z0 = false;
        r1 = r0.getBackground();

        if (r1 != null)
        {
            if (r1.getOpacity() == -1)
            {
                z0 = true;
            }
        }

        return z0;
    }

    private boolean hasPeekingDrawer()
    {

        int i0, i1;
        boolean z1;
        i0 = this.getChildCount();
        i1 = 0;

        label_6:
        {
            while (i1 < i0)
            {
                if (((DrawerLayout$LayoutParams) this.getChildAt(i1).getLayoutParams()).isPeeking == false)
                {
                    i1 = i1 + 1;
                }
                else
                {
                    z1 = true;
                    break label_6;
                }
            }

            z1 = false;
        } //end label_6:


        return z1;
    }

    private boolean hasVisibleDrawer()
    {

        boolean z0;
        if (this.findVisibleDrawer() == null)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    boolean isContentView(View  r1)
    {

        boolean z0;
        if (((DrawerLayout$LayoutParams) r1.getLayoutParams()).gravity != 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public boolean isDrawerOpen(int  i0)
    {

        View r1;
        boolean z0;
        r1 = this.findDrawerWithGravity(i0);

        if (r1 == null)
        {
            z0 = false;
        }
        else
        {
            z0 = this.isDrawerOpen(r1);
        }

        return z0;
    }

    public boolean isDrawerOpen(View  r1)
    {


        if (this.isDrawerView(r1) != false)
        {
            return ((DrawerLayout$LayoutParams) r1.getLayoutParams()).knownOpen;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(r1).append(" is not a drawer").toString());
        }
    }

    boolean isDrawerView(View  r1)
    {

        boolean z0;
        if ((GravityCompat.getAbsoluteGravity(((DrawerLayout$LayoutParams) r1.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(r1)) & 7) == 0)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        return z0;
    }

    public boolean isDrawerVisible(int  i0)
    {

        View r1;
        boolean z0;
        r1 = this.findDrawerWithGravity(i0);

        if (r1 == null)
        {
            z0 = false;
        }
        else
        {
            z0 = this.isDrawerVisible(r1);
        }

        return z0;
    }

    public boolean isDrawerVisible(View  r1)
    {

        boolean z1;
        if (this.isDrawerView(r1) != false)
        {
            if (((DrawerLayout$LayoutParams) r1.getLayoutParams()).onScreen - 0.0F <= 0)
            {
                z1 = false;
            }
            else
            {
                z1 = true;
            }

            return z1;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(r1).append(" is not a drawer").toString());
        }
    }

    void moveDrawerToOffset(View  r1, float  f0)
    {

        float f1;
        int i0, i3;
        f1 = this.getDrawerViewOffset(r1);
        i0 = r1.getWidth();
        i3 = (int) ((float) i0 * f0) - (int) ((float) i0 * f1);

        if (this.checkDrawerViewAbsoluteGravity(r1, 3) == false)
        {
            i3 = (- (i3));
        }

        r1.offsetLeftAndRight(i3);
        this.setDrawerViewOffset(r1, f0);
    }

    protected void onAttachedToWindow()
    {


        this.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {


        this.onDetachedFromWindow();
        mFirstLayout = true;
    }

    public boolean onInterceptTouchEvent(MotionEvent  r1)
    {

        boolean z1, z2, z3;
        int i0;
        float f0, f1;
        z1 = false;
        i0 = MotionEventCompat.getActionMasked(r1);
        z2 = mLeftDragger.shouldInterceptTouchEvent(r1) | mRightDragger.shouldInterceptTouchEvent(r1);
        z3 = false;

        label_7:
        switch (i0)
        {
            case 0:
                f0 = r1.getX();
                f1 = r1.getY();
                mInitialMotionX = f0;
                mInitialMotionY = f1;

                if (mScrimOpacity - 0.0F > 0)
                {
                    if (this.isContentView(mLeftDragger.findTopChildUnder((int) f0, (int) f1)) != false)
                    {
                        z3 = true;
                    }
                }

                mDisallowInterceptRequested = false;
                mChildrenCanceledTouch = false;
                break label_7;

            case 2:
                if (mLeftDragger.checkTouchSlop(3) == false)
                {
                    break label_7;
                }
                else
                {
                    mLeftCallback.removeCallbacks();
                    mRightCallback.removeCallbacks();
                    break label_7;
                }

            case 1:
            case 3:
                this.closeDrawers(true);
                mDisallowInterceptRequested = false;
                mChildrenCanceledTouch = false;
                break label_7;

            default:
                break label_7;
        }

        label_8:
        {
            if (z2 == false)
            {
                if (z3 == false)
                {
                    if (this.hasPeekingDrawer() == false)
                    {
                        if (mChildrenCanceledTouch == false)
                        {
                            break label_8;
                        }
                    }
                }
            }

            z1 = true;
        } //end label_8:


        return z1;
    }

    public boolean onKeyDown(int  i0, KeyEvent  r1)
    {

        boolean z1;
        label_9:
        {
            if (i0 == 4)
            {
                if (this.hasVisibleDrawer() != false)
                {
                    KeyEventCompat.startTracking(r1);
                    z1 = true;
                    break label_9;
                }
            }

            z1 = this.onKeyDown(i0, r1);
        } //end label_9:


        return z1;
    }

    public boolean onKeyUp(int  i0, KeyEvent  r1)
    {

        View r2;
        boolean z0;
        if (i0 != 4)
        {
            z0 = this.onKeyUp(i0, r1);
        }
        else
        {
            r2 = this.findVisibleDrawer();

            if (r2 != null)
            {
                if (this.getDrawerLockMode(r2) == 0)
                {
                    this.closeDrawers();
                }
            }

            if (r2 == null)
            {
                z0 = false;
            }
            else
            {
                z0 = true;
            }
        }

        return z0;
    }

    protected void onLayout(boolean  z0, int  i0, int  i1, int  i2, int  i3)
    {

        int i4, i5, i6, i13, i14, i15, i18, i19, i73;
        View r2;
        float f0;
        boolean z3;
        byte b17;
        DrawerLayout$LayoutParams r5;
        mInLayout = true;
        i4 = i2 - i0;
        i5 = this.getChildCount();
        i6 = 0;

        while (i6 < i5)
        {
            r2 = this.getChildAt(i6);

            if (r2.getVisibility() != 8)
            {
                r5 = (DrawerLayout$LayoutParams) r2.getLayoutParams();

                if (this.isContentView(r2) == false)
                {
                    i13 = r2.getMeasuredWidth();
                    i14 = r2.getMeasuredHeight();

                    if (this.checkDrawerViewAbsoluteGravity(r2, 3) == false)
                    {
                        i15 = i4 - (int) ((float) i13 * r5.onScreen);
                        f0 = (float) (i4 - i15) / (float) i13;
                    }
                    else
                    {
                        i15 = (- (i13)) + (int) ((float) i13 * r5.onScreen);
                        f0 = (float) (i13 + i15) / (float) i13;
                    }

                    if (f0 - r5.onScreen == 0)
                    {
                        z3 = false;
                    }
                    else
                    {
                        z3 = true;
                    }

                    label_10:
                    switch (r5.gravity & 112)
                    {
                        case 16:
                            i73 = i3 - i1;
                            i19 = (i73 - i14) / 2;

                            if (i19 >= r5.topMargin)
                            {
                                if (i19 + i14 > i73 - r5.bottomMargin)
                                {
                                    i19 = i73 - r5.bottomMargin - i14;
                                }
                            }
                            else
                            {
                                i19 = r5.topMargin;
                            }

                            r2.layout(i15, i19, i15 + i13, i19 + i14);
                            break label_10;

                        case 80:
                            i18 = i3 - i1;
                            r2.layout(i15, i18 - r5.bottomMargin - r2.getMeasuredHeight(), i15 + i13, i18 - r5.bottomMargin);
                            break label_10;

                        default:
                            r2.layout(i15, r5.topMargin, i15 + i13, r5.topMargin + i14);
                            break label_10;
                    }

                    if (z3 != false)
                    {
                        this.setDrawerViewOffset(r2, f0);
                    }

                    if (r5.onScreen - 0.0F <= 0)
                    {
                        b17 = (byte) (byte) 4;
                    }
                    else
                    {
                        b17 = (byte) (byte) 0;
                    }

                    if (r2.getVisibility() != b17)
                    {
                        r2.setVisibility(b17);
                    }
                }
                else
                {
                    r2.layout(r5.leftMargin, r5.topMargin, r5.leftMargin + r2.getMeasuredWidth(), r5.topMargin + r2.getMeasuredHeight());
                }
            }

            i6 = i6 + 1;
        }

        mInLayout = false;
        mFirstLayout = false;
    }

    protected void onMeasure(int  i0, int  i1)
    {

        int i2, i3, i4, i5, i10, i11, i15;
        View r1;
        DrawerLayout$LayoutParams r8;
        i2 = View$MeasureSpec.getMode(i0);
        i3 = View$MeasureSpec.getMode(i1);
        i4 = View$MeasureSpec.getSize(i0);
        i5 = View$MeasureSpec.getSize(i1);

        label_11:
        {
            if (i2 == 1073741824)
            {
                if (i3 == 1073741824)
                {
                    break label_11;
                }
            }

            if (this.isInEditMode() == false)
            {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            else
            {
                if (i2 != -2147483648)
                {
                    if (i2 == 0)
                    {
                        i4 = 300;
                    }
                }

                if (i3 != -2147483648)
                {
                    if (i3 == 0)
                    {
                        i5 = 300;
                    }
                }
            }
        } //end label_11:


        this.setMeasuredDimension(i4, i5);
        i10 = this.getChildCount();
        i11 = 0;

        while (i11 < i10)
        {
            r1 = this.getChildAt(i11);

            if (r1.getVisibility() != 8)
            {
                r8 = (DrawerLayout$LayoutParams) r1.getLayoutParams();

                if (this.isContentView(r1) == false)
                {
                    if (this.isDrawerView(r1) == false)
                    {
                        throw new IllegalStateException((new StringBuilder()).append("Child ").append(r1).append(" at index ").append(i11).append(" does not have a valid layout_gravity - must be Gravity.LEFT, ").append("Gravity.RIGHT or Gravity.NO_GRAVITY").toString());
                    }
                    else
                    {
                        i15 = this.getDrawerViewAbsoluteGravity(r1) & 7;

                        if ((0 & i15) == 0)
                        {
                            r1.measure(DrawerLayout.getChildMeasureSpec(i0, mMinDrawerMargin + r8.leftMargin + r8.rightMargin, r8.width), DrawerLayout.getChildMeasureSpec(i1, r8.topMargin + r8.bottomMargin, r8.height));
                        }
                        else
                        {
                            throw new IllegalStateException((new StringBuilder()).append("Child drawer has absolute gravity ").append(DrawerLayout.gravityToString(i15)).append(" but this ").append("DrawerLayout").append(" already has a ").append("drawer view along that edge").toString());
                        }
                    }
                }
                else
                {
                    r1.measure(View$MeasureSpec.makeMeasureSpec(i4 - r8.leftMargin - r8.rightMargin, 1073741824), View$MeasureSpec.makeMeasureSpec(i5 - r8.topMargin - r8.bottomMargin, 1073741824));
                }
            }

            i11 = i11 + 1;
        }
    }

    protected void onRestoreInstanceState(Parcelable  r1)
    {

        View r4;
        DrawerLayout$SavedState r5;
        r5 = (DrawerLayout$SavedState) r1;
        this.onRestoreInstanceState(r5.getSuperState());

        if (r5.openDrawerGravity != 0)
        {
            r4 = this.findDrawerWithGravity(r5.openDrawerGravity);

            if (r4 != null)
            {
                this.openDrawer(r4);
            }
        }

        this.setDrawerLockMode(r5.lockModeLeft, 3);
        this.setDrawerLockMode(r5.lockModeRight, 5);
    }

    protected Parcelable onSaveInstanceState()
    {

        DrawerLayout$SavedState r2;
        int i0, i1;
        View r3;
        DrawerLayout$LayoutParams r5;
        r2 = new DrawerLayout$SavedState(this.onSaveInstanceState());
        i0 = this.getChildCount();
        i1 = 0;

        label_12:
        {
            while (i1 < i0)
            {
                r3 = this.getChildAt(i1);

                if (this.isDrawerView(r3) != false)
                {
                    r5 = (DrawerLayout$LayoutParams) r3.getLayoutParams();

                    if (r5.knownOpen != false)
                    {
                        r2.openDrawerGravity = r5.gravity;
                        break label_12;
                    }
                }

                i1 = i1 + 1;
            }
        } //end label_12:


        r2.lockModeLeft = mLockModeLeft;
        r2.lockModeRight = mLockModeRight;
        return r2;
    }

    public boolean onTouchEvent(MotionEvent  r1)
    {

        float f0, f1, f2, f3, f4, f5;
        boolean z1;
        View r3, r4;
        int i3;
        mLeftDragger.processTouchEvent(r1);
        mRightDragger.processTouchEvent(r1);

        label_13:
        switch (r1.getAction() + 255)
        {
            case 0:
                f0 = r1.getX();
                f1 = r1.getY();
                mInitialMotionX = f0;
                mInitialMotionY = f1;
                mDisallowInterceptRequested = false;
                mChildrenCanceledTouch = false;
                break label_13;

            case 1:
                f4 = r1.getX();
                f5 = r1.getY();
                z1 = true;
                r3 = mLeftDragger.findTopChildUnder((int) f4, (int) f5);

                if (r3 != null)
                {
                    if (this.isContentView(r3) != false)
                    {
                        f2 = f4 - mInitialMotionX;
                        f3 = f5 - mInitialMotionY;
                        i3 = mLeftDragger.getTouchSlop();

                        if (f2 * f2 + f3 * f3 - (float) (i3 * i3) < 0)
                        {
                            r4 = this.findOpenDrawer();

                            if (r4 != null)
                            {
                                if (this.getDrawerLockMode(r4) != 2)
                                {
                                    z1 = false;
                                }
                                else
                                {
                                    z1 = true;
                                }
                            }
                        }
                    }
                }

                this.closeDrawers(z1);
                mDisallowInterceptRequested = false;
                break label_13;

            case 3:
                this.closeDrawers(true);
                mDisallowInterceptRequested = false;
                mChildrenCanceledTouch = false;
                break label_13;

            case 2:
            default:
                break label_13;
        }

        return true;
    }

    public void openDrawer(int  i0)
    {

        View r1;
        r1 = this.findDrawerWithGravity(i0);

        if (r1 != null)
        {
            this.openDrawer(r1);
            return;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No drawer view found with gravity ").append(DrawerLayout.gravityToString(i0)).toString());
        }
    }

    public void openDrawer(View  r1)
    {

        DrawerLayout$LayoutParams r11;
        if (this.isDrawerView(r1) != false)
        {
            if (mFirstLayout == false)
            {
                if (this.checkDrawerViewAbsoluteGravity(r1, 3) == false)
                {
                    mRightDragger.smoothSlideViewTo(r1, this.getWidth() - r1.getWidth(), r1.getTop());
                }
                else
                {
                    mLeftDragger.smoothSlideViewTo(r1, 0, r1.getTop());
                }
            }
            else
            {
                r11 = (DrawerLayout$LayoutParams) r1.getLayoutParams();
                r11.onScreen = 1.0F;
                r11.knownOpen = true;
            }

            this.invalidate();
            return;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(r1).append(" is not a sliding drawer").toString());
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean  z0)
    {


        this.requestDisallowInterceptTouchEvent(z0);
        mDisallowInterceptRequested = z0;

        if (z0 != false)
        {
            this.closeDrawers(true);
        }
    }

    public void requestLayout()
    {


        if (mInLayout == false)
        {
            this.requestLayout();
        }
    }

    public void setDrawerListener(DrawerLayout$DrawerListener  r1)
    {


        mListener = r1;
    }

    public void setDrawerLockMode(int  i0)
    {


        this.setDrawerLockMode(i0, 3);
        this.setDrawerLockMode(i0, 5);
    }

    public void setDrawerLockMode(int  i0, int  i1)
    {

        int i4;
        ViewDragHelper r1;
        View r2, r3;
        i4 = GravityCompat.getAbsoluteGravity(i1, ViewCompat.getLayoutDirection(this));

        if (i4 != 3)
        {
            if (i4 == 5)
            {
                mLockModeRight = i0;
            }
        }
        else
        {
            mLockModeLeft = i0;
        }

        if (i0 != 0)
        {
            if (i4 != 3)
            {
                r1 = mRightDragger;
            }
            else
            {
                r1 = mLeftDragger;
            }

            r1.cancel();
        }

        label_14:
        switch (i0)
        {
            case 1:
                r3 = this.findDrawerWithGravity(i4);

                if (r3 == null)
                {
                    break label_14;
                }
                else
                {
                    this.closeDrawer(r3);
                    break label_14;
                }

            case 2:
                r2 = this.findDrawerWithGravity(i4);

                if (r2 == null)
                {
                    break label_14;
                }
                else
                {
                    this.openDrawer(r2);
                    break label_14;
                }

            default:
                break label_14;
        }
    }

    public void setDrawerLockMode(int  i0, View  r1)
    {


        if (this.isDrawerView(r1) != false)
        {
            this.setDrawerLockMode(i0, ((DrawerLayout$LayoutParams) r1.getLayoutParams()).gravity);
            return;
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(r1).append(" is not a ").append("drawer with appropriate layout_gravity").toString());
        }
    }

    public void setDrawerShadow(int  i0, int  i1)
    {


        this.setDrawerShadow(this.getResources().getDrawable(i0), i1);
    }

    public void setDrawerShadow(Drawable  r1, int  i0)
    {

        int i2;
        i2 = GravityCompat.getAbsoluteGravity(i0, ViewCompat.getLayoutDirection(this));

        if ((i2 & 3) == 3)
        {
            mShadowLeft = r1;
            this.invalidate();
        }

        if ((i2 & 5) == 5)
        {
            mShadowRight = r1;
            this.invalidate();
        }
    }

    public void setDrawerTitle(int  i0, CharSequence  r1)
    {

        int i2;
        i2 = GravityCompat.getAbsoluteGravity(i0, ViewCompat.getLayoutDirection(this));

        if (i2 != 3)
        {
            if (i2 == 5)
            {
                mTitleRight = r1;
            }
        }
        else
        {
            mTitleLeft = r1;
        }
    }

    void setDrawerViewOffset(View  r1, float  f0)
    {

        DrawerLayout$LayoutParams r3;
        r3 = (DrawerLayout$LayoutParams) r1.getLayoutParams();

        if (f0 - r3.onScreen != 0)
        {
            r3.onScreen = f0;
            this.dispatchOnDrawerSlide(r1, f0);
        }
    }

    public void setScrimColor(int  i0)
    {


        mScrimColor = i0;
        this.invalidate();
    }

    void updateDrawerState(int  i0, int  i1, View  r1)
    {

        int i4, i5, i6;
        DrawerLayout$LayoutParams r5;
        i4 = mLeftDragger.getViewDragState();
        i5 = mRightDragger.getViewDragState();

        label_15:
        {
            if (i4 != 1)
            {
                if (i5 != 1)
                {
                    if (i4 != 2)
                    {
                        if (i5 != 2)
                        {
                            i6 = 0;
                            break label_15;
                        }
                    }

                    i6 = 2;
                    break label_15;
                }
            }

            i6 = 1;
        } //end label_15:


        if (r1 != null)
        {
            if (i1 == 0)
            {
                r5 = (DrawerLayout$LayoutParams) r1.getLayoutParams();

                if (r5.onScreen - 0.0F != 0)
                {
                    if (r5.onScreen - 1.0F == 0)
                    {
                        this.dispatchOnDrawerOpened(r1);
                    }
                }
                else
                {
                    this.dispatchOnDrawerClosed(r1);
                }
            }
        }

        if (i6 != mDrawerState)
        {
            mDrawerState = i6;

            if (mListener != null)
            {
                mListener.onDrawerStateChanged(i6);
            }
        }
    }
}
