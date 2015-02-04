package android.support.v4.view;

import android.view.ViewGroup;
import android.content.Context;
import java.util.ArrayList;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.view.SoundEffectConstants;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.os.SystemClock;
import android.widget.Scroller;
import java.util.Collections;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.graphics.Canvas;
import android.support.v4.widget.EdgeEffectCompat;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.view.ViewConfiguration;
import android.content.res.Resources;
import android.view.View$MeasureSpec;
import android.os.Parcelable;
import android.content.res.Resources$NotFoundException;
import java.lang.reflect.Method;
import java.util.Comparator;
import android.view.animation.Interpolator;

public class ViewPager extends ViewGroup
{
    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator COMPARATOR;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int[] LAYOUT_ATTRS;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private static final Interpolator sInterpolator;
    private static final ViewPager$ViewPositionComparator sPositionComparator;
    private int mActivePointerId;
    private PagerAdapter mAdapter;
    private ViewPager$OnAdapterChangeListener mAdapterChangeListener;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    private int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mIgnoreGutter;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private ViewPager$OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private final ArrayList mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffectCompat mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private ViewPager$PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private ViewPager$OnPageChangeListener mOnPageChangeListener;
    private int mPageMargin;
    private ViewPager$PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffectCompat mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private final ViewPager$ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    static
    {

        int[] r0;
        r0 = new int[1];
        r0[0] = 16842931;
        LAYOUT_ATTRS = r0;
        COMPARATOR = new ViewPager$1();
        sInterpolator = new ViewPager$2();
        sPositionComparator = new ViewPager$ViewPositionComparator();
    }

    public ViewPager(Context  r1)
    {
        super(r1);

        Object n0;
        n0 = null;
        this.<init>(r1);
        mItems = new ArrayList();
        mTempItem = new ViewPager$ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = n0;
        mRestoredClassLoader = n0;
        mFirstOffset = -3.4028235E38F;
        mLastOffset = 3.4028235E38F;
        mOffscreenPageLimit = (int) 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mNeedCalculatePageOffsets = false;
        mEndScrollRunnable = new ViewPager$3(this);
        mScrollState = (int) 0;
        this.initViewPager();
    }

    public ViewPager(Context  r1, AttributeSet  r2)
    {
        super(r1, r2);

        Object n0;
        n0 = null;
        this.<init>(r1, r2);
        mItems = new ArrayList();
        mTempItem = new ViewPager$ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = n0;
        mRestoredClassLoader = n0;
        mFirstOffset = -3.4028235E38F;
        mLastOffset = 3.4028235E38F;
        mOffscreenPageLimit = (int) 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mNeedCalculatePageOffsets = false;
        mEndScrollRunnable = new ViewPager$3(this);
        mScrollState = (int) 0;
        this.initViewPager();
    }

    static void access$000(ViewPager  r0, int  i0)
    {


        r0.setScrollState(i0);
    }

    static PagerAdapter access$200(ViewPager  r0)
    {


        return r0.mAdapter;
    }

    static int access$300(ViewPager  r0)
    {


        return r0.mCurItem;
    }

    static int[] access$400()
    {


        return LAYOUT_ATTRS;
    }

    public void addFocusables(ArrayList  r1, int  i0, int  i1)
    {

        int i2, i3, i5;
        View r2;
        ViewPager$ItemInfo r3;
        i2 = r1.size();
        i3 = this.getDescendantFocusability();

        if (i3 != 393216)
        {
            i5 = 0;

            while (i5 < this.getChildCount())
            {
                r2 = this.getChildAt(i5);

                if (r2.getVisibility() == 0)
                {
                    r3 = this.infoForChild(r2);

                    if (r3 != null)
                    {
                        if (r3.position == mCurItem)
                        {
                            r2.addFocusables(r1, i0, i1);
                        }
                    }
                }

                i5 = i5 + 1;
            }
        }

        label_0:
        {
            if (i3 == 262144)
            {
                if (i2 != r1.size())
                {
                    break label_0;
                }
            }

            if (this.isFocusable() != false)
            {
                if ((i1 & 1) == 1)
                {
                    if (this.isInTouchMode() != false)
                    {
                        if (this.isFocusableInTouchMode() == false)
                        {
                            break label_0;
                        }
                    }
                }

                if (r1 != null)
                {
                    r1.add(this);
                }
            }
        } //end label_0:

    }

    ViewPager$ItemInfo addNewItem(int  i0, int  i1)
    {

        ViewPager$ItemInfo r1;
        r1 = new ViewPager$ItemInfo();
        r1.position = i0;
        r1.object = mAdapter.instantiateItem(this, i0);
        r1.widthFactor = mAdapter.getPageWidth(i0);

        label_1:
        {
            if (i1 >= 0)
            {
                if (i1 < mItems.size())
                {
                    mItems.add(i1, r1);
                    break label_1;
                }
            }

            mItems.add(r1);
        } //end label_1:


        return r1;
    }

    public void addTouchables(ArrayList  r1)
    {

        int i0;
        View r2;
        ViewPager$ItemInfo r3;
        i0 = 0;

        while (i0 < this.getChildCount())
        {
            r2 = this.getChildAt(i0);

            if (r2.getVisibility() == 0)
            {
                r3 = this.infoForChild(r2);

                if (r3 != null)
                {
                    if (r3.position == mCurItem)
                    {
                        r2.addTouchables(r1);
                    }
                }
            }

            i0 = i0 + 1;
        }
    }

    public void addView(View  r1, int  i0, ViewGroup$LayoutParams  r2)
    {

        ViewPager$LayoutParams r4;
        if (this.checkLayoutParams(r2) == false)
        {
            r2 = this.generateLayoutParams(r2);
        }

        r4 = (ViewPager$LayoutParams) r2;
        r4.isDecor = r4.isDecor | r1 instanceof ViewPager$Decor;

        if (mInLayout == false)
        {
            this.addView(r1, i0, r2);
        }
        else
        {
            if (r4 != null)
            {
                if (r4.isDecor != false)
                {
                    throw new IllegalStateException("Cannot add pager decor view during layout");
                }
            }

            r4.needsMeasure = true;
            this.addViewInLayout(r1, i0, r2);
        }
    }

    public boolean arrowScroll(int  i0)
    {

        View r1, r3;
        boolean z0, z1;
        ViewParent r4, r15;
        StringBuilder r5;
        r1 = this.findFocus();

        if (r1 != this)
        {
            if (r1 != null)
            {
                z1 = false;
                r4 = r1.getParent();

                label_2:
                {
                    while (r4 instanceof ViewGroup != false)
                    {
                        if (r4 != this)
                        {
                            r4 = r4.getParent();
                        }
                        else
                        {
                            z1 = true;
                            break label_2;
                        }
                    }
                } //end label_2:


                if (z1 == false)
                {
                    r5 = new StringBuilder();
                    r5.append(r1.getClass().getSimpleName());
                    r15 = r1.getParent();

                    while (r15 instanceof ViewGroup != false)
                    {
                        r5.append(" => ").append(r15.getClass().getSimpleName());
                        r15 = r15.getParent();
                    }

                    Log.e("ViewPager", (new StringBuilder()).append("arrowScroll tried to find focus based on non-child current focused view ").append(r5.toString()).toString());
                    r1 = null;
                }
            }
        }
        else
        {
            r1 = null;
        }

        z0 = false;
        r3 = FocusFinder.getInstance().findNextFocus(this, r1, i0);

        label_3:
        {
            if (r3 != null)
            {
                if (r3 != r1)
                {
                    if (i0 != 17)
                    {
                        if (i0 != 66)
                        {
                            break label_3;
                        }
                        else
                        {
                            if (r1 != null)
                            {
                                if (this.getChildRectInPagerCoordinates(mTempRect, r3).left <= this.getChildRectInPagerCoordinates(mTempRect, r1).left)
                                {
                                    z0 = this.pageRight();
                                    break label_3;
                                }
                            }

                            z0 = r3.requestFocus();
                            break label_3;
                        }
                    }
                    else
                    {
                        if (r1 != null)
                        {
                            if (this.getChildRectInPagerCoordinates(mTempRect, r3).left >= this.getChildRectInPagerCoordinates(mTempRect, r1).left)
                            {
                                z0 = this.pageLeft();
                                break label_3;
                            }
                        }

                        z0 = r3.requestFocus();
                        break label_3;
                    }
                }
            }

            if (i0 != 17)
            {
                if (i0 != 1)
                {
                    if (i0 != 66)
                    {
                        if (i0 != 2)
                        {
                            break label_3;
                        }
                    }

                    z0 = this.pageRight();
                    break label_3;
                }
            }

            z0 = this.pageLeft();
        } //end label_3:


        if (z0 != false)
        {
            this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i0));
        }

        return z0;
    }

    public boolean beginFakeDrag()
    {

        boolean z1;
        long l0;
        MotionEvent r1;
        z1 = false;

        if (mIsBeingDragged == false)
        {
            mFakeDragging = true;
            this.setScrollState((int) 1);
            mLastMotionX = 0.0F;
            mInitialMotionX = 0.0F;

            if (mVelocityTracker != null)
            {
                mVelocityTracker.clear();
            }
            else
            {
                mVelocityTracker = VelocityTracker.obtain();
            }

            l0 = SystemClock.uptimeMillis();
            r1 = MotionEvent.obtain(l0, l0, (int) 0, 0.0F, 0.0F, (int) 0);
            mVelocityTracker.addMovement(r1);
            r1.recycle();
            mFakeDragBeginTime = l0;
            z1 = true;
        }

        return z1;
    }

    private void calculatePageOffsets(ViewPager$ItemInfo  r1, int  i0, ViewPager$ItemInfo  r2)
    {

        int i1, i2, i3, i4, i5, i6, i7, i19, i20, i25, i32, i33;
        float f1, f2, f11, f16, f17, f22, f30;
        ViewPager$ItemInfo r10, r19, r26, r30;
        i1 = mAdapter.getCount();
        i2 = this.getClientWidth();

        if (i2 <= 0)
        {
            f1 = 0.0F;
        }
        else
        {
            f1 = (float) mPageMargin / (float) i2;
        }

        label_4:
        if (r2 != null)
        {
            i3 = r2.position;

            if (i3 >= r1.position)
            {
                if (i3 > r1.position)
                {
                    i19 = mItems.size() + -1;
                    f11 = r2.offset;
                    i20 = i3 + -1;

                    while (i20 >= r1.position)
                    {
                        if (i19 < 0)
                        {
                            break label_4;
                        }
                        else
                        {
                            r19 = (ViewPager$ItemInfo) mItems.get(i19);

                            if (i20 < r19.position)
                            {
                                if (i19 > 0)
                                {
                                    i19 = i19 + -1;
                                    r19 = (ViewPager$ItemInfo) mItems.get(i19);
                                }
                            }

                            if (i20 <= r19.position)
                            {
                                f11 = f11 - (r19.widthFactor + f1);
                                r19.offset = f11;
                                i20 = i20 + -1;
                            }
                            else
                            {
                                f11 = f11 - (mAdapter.getPageWidth(i20) + f1);
                                i20 = i20 + -1;
                            }
                        }
                    }
                }
            }
            else
            {
                i4 = 0;
                f2 = r2.offset + r2.widthFactor + f1;
                i5 = i3 + 1;

                while (i5 <= r1.position)
                {
                    if (i4 >= mItems.size())
                    {
                        break label_4;
                    }
                    else
                    {
                        r10 = (ViewPager$ItemInfo) mItems.get(i4);

                        if (i5 > r10.position)
                        {
                            if (i4 < mItems.size() + -1)
                            {
                                i4 = i4 + 1;
                                r10 = (ViewPager$ItemInfo) mItems.get(i4);
                            }
                        }

                        if (i5 >= r10.position)
                        {
                            r10.offset = f2;
                            f2 = f2 + (r10.widthFactor + f1);
                            i5 = i5 + 1;
                        }
                        else
                        {
                            f2 = f2 + (mAdapter.getPageWidth(i5) + f1);
                            i5 = i5 + 1;
                        }
                    }
                }
            }
        }

        i6 = mItems.size();
        f16 = r1.offset;
        i25 = r1.position + -1;

        if (r1.position != 0)
        {
            f17 = -3.4028235E38F;
        }
        else
        {
            f17 = r1.offset;
        }

        mFirstOffset = f17;

        if (r1.position != i1 + -1)
        {
            f22 = 3.4028235E38F;
        }
        else
        {
            f22 = r1.offset + r1.widthFactor - 1.0F;
        }

        mLastOffset = f22;
        i7 = i0 + -1;

        while (i7 >= 0)
        {
            r26 = (ViewPager$ItemInfo) mItems.get(i7);

            if (i25 <= r26.position)
            {
                f16 = f16 - (r26.widthFactor + f1);
                r26.offset = f16;

                if (r26.position == 0)
                {
                    mFirstOffset = f16;
                }

                i7 = i7 + -1;
                i25 = i25 + -1;
            }
            else
            {
                f16 = f16 - (mAdapter.getPageWidth(i25) + f1);
                i25 = i25 + -1;
            }
        }

        f30 = r1.offset + r1.widthFactor + f1;
        i32 = r1.position + 1;
        i33 = i0 + 1;

        while (i33 < i6)
        {
            r30 = (ViewPager$ItemInfo) mItems.get(i33);

            if (i32 >= r30.position)
            {
                if (r30.position == i1 + -1)
                {
                    mLastOffset = r30.widthFactor + f30 - 1.0F;
                }

                r30.offset = f30;
                f30 = f30 + (r30.widthFactor + f1);
                i33 = i33 + 1;
                i32 = i32 + 1;
            }
            else
            {
                f30 = f30 + (mAdapter.getPageWidth(i32) + f1);
                i32 = i32 + 1;
            }
        }

        mNeedCalculatePageOffsets = false;
    }

    protected boolean canScroll(View  r1, boolean  z0, int  i0, int  i1, int  i2)
    {

        int i3, i4, i6;
        View r3;
        ViewGroup r4;
        boolean z4;
        label_5:
        {
            if (r1 instanceof ViewGroup != false)
            {
                r4 = (ViewGroup) r1;
                i3 = r1.getScrollX();
                i4 = r1.getScrollY();
                i6 = r4.getChildCount() + -1;

                while (i6 >= 0)
                {
                    r3 = r4.getChildAt(i6);

                    if (i1 + i3 >= r3.getLeft())
                    {
                        if (i1 + i3 < r3.getRight())
                        {
                            if (i2 + i4 >= r3.getTop())
                            {
                                if (i2 + i4 < r3.getBottom())
                                {
                                    if (this.canScroll(r3, true, i0, i1 + i3 - r3.getLeft(), i2 + i4 - r3.getTop()) != false)
                                    {
                                        z4 = true;
                                        break label_5;
                                    }
                                }
                            }
                        }
                    }

                    i6 = i6 + -1;
                }
            }

            if (z0 != false)
            {
                if (ViewCompat.canScrollHorizontally(r1, (- (i0))) != false)
                {
                    z4 = true;
                    break label_5;
                }
            }

            z4 = false;
        } //end label_5:


        return z4;
    }

    public boolean canScrollHorizontally(int  i0)
    {

        boolean z0, z1;
        int i1, i2;
        z0 = true;
        z1 = false;

        if (mAdapter != null)
        {
            i1 = this.getClientWidth();
            i2 = this.getScrollX();

            if (i0 >= 0)
            {
                if (i0 > 0)
                {
                    if (i2 >= (int) ((float) i1 * mLastOffset))
                    {
                        z0 = false;
                    }

                    z1 = z0;
                }
            }
            else
            {
                if (i2 <= (int) ((float) i1 * mFirstOffset))
                {
                    z0 = false;
                }

                z1 = z0;
            }
        }

        return z1;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams  r1)
    {

        boolean z2;
        label_6:
        {
            if (r1 instanceof ViewPager$LayoutParams != false)
            {
                if (this.checkLayoutParams(r1) != false)
                {
                    z2 = true;
                    break label_6;
                }
            }

            z2 = false;
        } //end label_6:


        return z2;
    }

    private void completeScroll(boolean  z0)
    {

        boolean z2;
        int i2, i3, i4, i5, i6;
        ViewPager$ItemInfo r7;
        if (mScrollState != 2)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        label_7:
        if (z2 != false)
        {
            this.setScrollingCacheEnabled(false);
            mScroller.abortAnimation();
            i2 = this.getScrollX();
            i3 = this.getScrollY();
            i4 = mScroller.getCurrX();
            i5 = mScroller.getCurrY();

            if (i2 == i4)
            {
                if (i3 == i5)
                {
                    break label_7;
                }
            }

            this.scrollTo(i4, i5);
        }

        mPopulatePending = false;
        i6 = 0;

        while (i6 < mItems.size())
        {
            r7 = (ViewPager$ItemInfo) mItems.get(i6);

            if (r7.scrolling != false)
            {
                z2 = true;
                r7.scrolling = false;
            }

            i6 = i6 + 1;
        }

        if (z2 != false)
        {
            if (z0 == false)
            {
                mEndScrollRunnable.run();
            }
            else
            {
                ViewCompat.postOnAnimation(this, mEndScrollRunnable);
            }
        }
    }

    public void computeScroll()
    {

        int i0, i1, i2, i3;
        label_9:
        {
            if (mScroller.isFinished() == false)
            {
                if (mScroller.computeScrollOffset() != false)
                {
                    i0 = this.getScrollX();
                    i1 = this.getScrollY();
                    i2 = mScroller.getCurrX();
                    i3 = mScroller.getCurrY();

                    label_8:
                    {
                        if (i0 == i2)
                        {
                            if (i1 == i3)
                            {
                                break label_8;
                            }
                        }

                        this.scrollTo(i2, i3);

                        if (this.pageScrolled(i2) == false)
                        {
                            mScroller.abortAnimation();
                            this.scrollTo(0, i3);
                        }
                    } //end label_8:


                    ViewCompat.postInvalidateOnAnimation(this);
                    break label_9;
                }
            }

            this.completeScroll(true);
        } //end label_9:

    }

    void dataSetChanged()
    {

        int i0, i2, i3, i4, i5, i22;
        boolean z2, z3;
        ViewPager$ItemInfo r10;
        ViewPager$LayoutParams r20;
        i0 = mAdapter.getCount();
        mExpectedAdapterCount = i0;

        label_10:
        {
            if (mItems.size() < mOffscreenPageLimit * 2 + 1)
            {
                if (mItems.size() < i0)
                {
                    z2 = true;
                    break label_10;
                }
            }

            z2 = false;
        } //end label_10:


        i2 = mCurItem;
        z3 = false;
        i3 = 0;

        while (i3 < mItems.size())
        {
            r10 = (ViewPager$ItemInfo) mItems.get(i3);
            i4 = mAdapter.getItemPosition(r10.object);

            if (i4 != -1)
            {
                if (i4 != -2)
                {
                    if (r10.position != i4)
                    {
                        if (r10.position == mCurItem)
                        {
                            i2 = i4;
                        }

                        r10.position = i4;
                        z2 = true;
                    }
                }
                else
                {
                    mItems.remove(i3);
                    i3 = i3 + -1;

                    if (z3 == false)
                    {
                        mAdapter.startUpdate(this);
                        z3 = true;
                    }

                    mAdapter.destroyItem(this, r10.position, r10.object);
                    z2 = true;

                    if (mCurItem == r10.position)
                    {
                        i2 = Math.max((int) 0, Math.min(mCurItem, i0 + -1));
                        z2 = true;
                    }
                }
            }

            i3 = i3 + 1;
        }

        if (z3 != false)
        {
            mAdapter.finishUpdate(this);
        }

        Collections.sort(mItems, COMPARATOR);

        if (z2 != false)
        {
            i5 = this.getChildCount();
            i22 = 0;

            while (i22 < i5)
            {
                r20 = (ViewPager$LayoutParams) this.getChildAt(i22).getLayoutParams();

                if (r20.isDecor == false)
                {
                    r20.widthFactor = 0.0F;
                }

                i22 = i22 + 1;
            }

            this.setCurrentItemInternal(i2, false, true);
            this.requestLayout();
        }
    }

    private int determineTargetPage(int  i0, float  f0, int  i1, int  i2)
    {

        int i5;
        float f1;
        ViewPager$ItemInfo r5, r8;
        label_11:
        {
            if (Math.abs(i2) > mFlingDistance)
            {
                if (Math.abs(i1) > mMinimumVelocity)
                {
                    if (i1 <= 0)
                    {
                        i5 = i0 + 1;
                        break label_11;
                    }
                    else
                    {
                        i5 = i0;
                        break label_11;
                    }
                }
            }

            if (i0 < mCurItem)
            {
                f1 = 0.6F;
            }
            else
            {
                f1 = 0.4F;
            }

            i5 = (int) ((float) i0 + f0 + f1);
        } //end label_11:


        if (mItems.size() > 0)
        {
            r5 = (ViewPager$ItemInfo) mItems.get(0);
            r8 = (ViewPager$ItemInfo) mItems.get(mItems.size() + -1);
            i5 = Math.max(r5.position, Math.min(i5, r8.position));
        }

        return i5;
    }

    public boolean dispatchKeyEvent(KeyEvent  r1)
    {

        boolean z2;
        label_12:
        {
            if (this.dispatchKeyEvent(r1) == false)
            {
                if (this.executeKeyEvent(r1) == false)
                {
                    z2 = false;
                    break label_12;
                }
            }

            z2 = true;
        } //end label_12:


        return z2;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent  r1)
    {

        int i2, i3;
        View r2;
        ViewPager$ItemInfo r3;
        boolean z0;
        label_13:
        if (r1.getEventType() != 4096)
        {
            i2 = this.getChildCount();
            i3 = 0;

            while (i3 < i2)
            {
                r2 = this.getChildAt(i3);

                if (r2.getVisibility() == 0)
                {
                    r3 = this.infoForChild(r2);

                    if (r3 != null)
                    {
                        if (r3.position == mCurItem)
                        {
                            if (r2.dispatchPopulateAccessibilityEvent(r1) != false)
                            {
                                z0 = true;
                                break label_13;
                            }
                        }
                    }
                }

                i3 = i3 + 1;
            }

            z0 = false;
        }
        else
        {
            z0 = this.dispatchPopulateAccessibilityEvent(r1);
        }

        return z0;
    }

    float distanceInfluenceForSnapDuration(float  f0)
    {


        return (float) Math.sin((double) (float) ((double) (f0 - 0.5F) * 0.47123891676382));
    }

    public void draw(Canvas  r1)
    {

        boolean z0;
        int i1, i2, i3, i4, i13, i14, i19;
        this.draw(r1);
        z0 = false;
        i1 = ViewCompat.getOverScrollMode(this);

        label_15:
        {
            label_14:
            if (i1 != 0)
            {
                if (i1 == 1)
                {
                    if (mAdapter != null)
                    {
                        if (mAdapter.getCount() > 1)
                        {
                            break label_14;
                        }
                    }
                }

                mLeftEdge.finish();
                mRightEdge.finish();
                break label_15;
            }

            if (mLeftEdge.isFinished() == false)
            {
                i2 = r1.save();
                i3 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                i4 = this.getWidth();
                r1.rotate(270.0F);
                r1.translate((float) ((- (i3)) + this.getPaddingTop()), mFirstOffset * (float) i4);
                mLeftEdge.setSize(i3, i4);
                z0 = 0 | mLeftEdge.draw(r1);
                r1.restoreToCount(i2);
            }

            if (mRightEdge.isFinished() == false)
            {
                i13 = r1.save();
                i14 = this.getWidth();
                i19 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                r1.rotate(90.0F);
                r1.translate((float) (- (this.getPaddingTop())), (- (mLastOffset + 1.0F)) * (float) i14);
                mRightEdge.setSize(i19, i14);
                z0 = z0 | mRightEdge.draw(r1);
                r1.restoreToCount(i13);
            }
        } //end label_15:


        if (z0 != false)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    protected void drawableStateChanged()
    {

        Drawable r1;
        this.drawableStateChanged();
        r1 = mMarginDrawable;

        if (r1 != null)
        {
            if (r1.isStateful() != false)
            {
                r1.setState(this.getDrawableState());
            }
        }
    }

    private void enableLayers(boolean  z0)
    {

        int i0, i1;
        byte b2;
        i0 = this.getChildCount();
        i1 = 0;

        while (i1 < i0)
        {
            if (z0 == false)
            {
                b2 = (byte) (byte) 0;
            }
            else
            {
                b2 = (byte) (byte) 2;
            }

            ViewCompat.setLayerType(this.getChildAt(i1), b2, null);
            i1 = i1 + 1;
        }
    }

    private void endDrag()
    {


        mIsBeingDragged = false;
        mIsUnableToDrag = false;

        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public void endFakeDrag()
    {

        VelocityTracker r2;
        int i0, i1, i2;
        ViewPager$ItemInfo r3;
        if (mFakeDragging != false)
        {
            r2 = mVelocityTracker;
            r2.computeCurrentVelocity(1000, (float) mMaximumVelocity);
            i0 = (int) VelocityTrackerCompat.getXVelocity(r2, mActivePointerId);
            mPopulatePending = true;
            i1 = this.getClientWidth();
            i2 = this.getScrollX();
            r3 = this.infoForCurrentScrollPosition();
            this.setCurrentItemInternal(this.determineTargetPage(r3.position, ((float) i2 / (float) i1 - r3.offset) / r3.widthFactor, i0, (int) (mLastMotionX - mInitialMotionX)), true, true, i0);
            this.endDrag();
            mFakeDragging = false;
            return;
        }
        else
        {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
    }

    public boolean executeKeyEvent(KeyEvent  r1)
    {

        boolean z0;
        z0 = false;

        label_16:
        if (r1.getAction() == 0)
        {
            switch (r1.getKeyCode())
            {
                case 21:
                    z0 = this.arrowScroll(17);
                    break label_16;

                case 22:
                    z0 = this.arrowScroll(66);
                    break label_16;

                case 61:
                    if (Build$VERSION.SDK_INT < 11)
                    {
                        break label_16;
                    }
                    else
                    {
                        if (KeyEventCompat.hasNoModifiers(r1) == false)
                        {
                            if (KeyEventCompat.hasModifiers(r1, 1) == false)
                            {
                                break label_16;
                            }
                            else
                            {
                                z0 = this.arrowScroll(1);
                                break label_16;
                            }
                        }
                        else
                        {
                            z0 = this.arrowScroll(2);
                            break label_16;
                        }
                    }

                default:
                    break label_16;
            }
        }

        return z0;
    }

    public void fakeDragBy(float  f0)
    {

        float f2, f3, f4;
        int i0;
        long l1;
        MotionEvent r5;
        ViewPager$ItemInfo r13, r18;
        if (mFakeDragging != false)
        {
            mLastMotionX = mLastMotionX + f0;
            f2 = (float) this.getScrollX() - f0;
            i0 = this.getClientWidth();
            f3 = (float) i0 * mFirstOffset;
            f4 = (float) i0 * mLastOffset;
            r13 = (ViewPager$ItemInfo) mItems.get(0);
            r18 = (ViewPager$ItemInfo) mItems.get(mItems.size() + -1);

            if (r13.position != 0)
            {
                f3 = r13.offset * (float) i0;
            }

            if (r18.position != mAdapter.getCount() + -1)
            {
                f4 = r18.offset * (float) i0;
            }

            if (f2 - f3 >= 0)
            {
                if (f2 - f4 > 0)
                {
                    f2 = f4;
                }
            }
            else
            {
                f2 = f3;
            }

            mLastMotionX = mLastMotionX + (f2 - (float) (int) f2);
            this.scrollTo((int) f2, this.getScrollY());
            this.pageScrolled((int) f2);
            l1 = SystemClock.uptimeMillis();
            r5 = MotionEvent.obtain(mFakeDragBeginTime, l1, 2, mLastMotionX, 0.0F, 0);
            mVelocityTracker.addMovement(r5);
            r5.recycle();
            return;
        }
        else
        {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams()
    {


        return new ViewPager$LayoutParams();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet  r1)
    {


        return new ViewPager$LayoutParams(this.getContext(), r1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams  r1)
    {


        return this.generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter()
    {


        return mAdapter;
    }

    protected int getChildDrawingOrder(int  i0, int  i1)
    {

        int i4;
        if (mDrawingOrder != 2)
        {
            i4 = i1;
        }
        else
        {
            i4 = i0 + -1 - i1;
        }

        return ((ViewPager$LayoutParams) ((View) mDrawingOrderedChildren.get(i4)).getLayoutParams()).childIndex;
    }

    private Rect getChildRectInPagerCoordinates(Rect  r1, View  r2)
    {

        ViewParent r3;
        ViewGroup r5;
        if (r1 == null)
        {
            r1 = new Rect();
        }

        label_17:
        if (r2 != null)
        {
            r1.left = r2.getLeft();
            r1.right = r2.getRight();
            r1.top = r2.getTop();
            r1.bottom = r2.getBottom();
            r3 = r2.getParent();

            while (r3 instanceof ViewGroup != false)
            {
                if (r3 == this)
                {
                    break label_17;
                }
                else
                {
                    r5 = (ViewGroup) r3;
                    r1.left = r1.left + r5.getLeft();
                    r1.right = r1.right + r5.getRight();
                    r1.top = r1.top + r5.getTop();
                    r1.bottom = r1.bottom + r5.getBottom();
                    r3 = r5.getParent();
                }
            }
        }
        else
        {
            r1.set(0, 0, 0, 0);
        }

        return r1;
    }

    private int getClientWidth()
    {


        return this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
    }

    public int getCurrentItem()
    {


        return mCurItem;
    }

    public int getOffscreenPageLimit()
    {


        return mOffscreenPageLimit;
    }

    public int getPageMargin()
    {


        return mPageMargin;
    }

    ViewPager$ItemInfo infoForAnyChild(View  r1)
    {

        ViewParent r2;
        ViewPager$ItemInfo r3;
        label_18:
        while (true)
        {
            r2 = r1.getParent();

            if (r2 == this)
            {
                r3 = this.infoForChild(r1);
            }
            else
            {
                if (r2 != null)
                {
                    if (r2 instanceof View != false)
                    {
                        r1 = (View) r2;
                        continue label_18;
                    }
                }

                r3 = null;
            }

            return r3;
        }
    }

    ViewPager$ItemInfo infoForChild(View  r1)
    {

        int i0;
        ViewPager$ItemInfo r6;
        i0 = 0;

        label_19:
        {
            while (i0 < mItems.size())
            {
                r6 = (ViewPager$ItemInfo) mItems.get(i0);

                if (mAdapter.isViewFromObject(r1, r6.object) == false)
                {
                    i0 = i0 + 1;
                }
                else
                {
                    break label_19;
                }
            }

            r6 = null;
        } //end label_19:


        return r6;
    }

    private ViewPager$ItemInfo infoForCurrentScrollPosition()
    {

        float f0, f2, f3, f4, f5, f7;
        int i0, i2, i3;
        boolean z0;
        ViewPager$ItemInfo r1, r5;
        f0 = 0.0F;
        i0 = this.getClientWidth();

        if (i0 <= 0)
        {
            f2 = 0.0F;
        }
        else
        {
            f2 = (float) this.getScrollX() / (float) i0;
        }

        if (i0 > 0)
        {
            f0 = (float) mPageMargin / (float) i0;
        }

        i2 = -1;
        f3 = 0.0F;
        f4 = 0.0F;
        z0 = true;
        r1 = null;
        i3 = 0;

        label_21:
        {
            label_20:
            while (i3 < mItems.size())
            {
                r5 = (ViewPager$ItemInfo) mItems.get(i3);

                if (z0 == false)
                {
                    if (r5.position != i2 + 1)
                    {
                        r5 = mTempItem;
                        r5.offset = f3 + f4 + f0;
                        r5.position = i2 + 1;
                        r5.widthFactor = mAdapter.getPageWidth(r5.position);
                        i3 = i3 + -1;
                    }
                }

                f5 = r5.offset;
                f7 = r5.widthFactor + f5 + f0;

                if (z0 == false)
                {
                    if (f2 - f5 < 0)
                    {
                        break label_21;
                    }
                }

                if (f2 - f7 >= 0)
                {
                    if (i3 != mItems.size() + -1)
                    {
                        z0 = false;
                        i2 = r5.position;
                        f3 = f5;
                        f4 = r5.widthFactor;
                        r1 = r5;
                        i3 = i3 + 1;
                        continue label_20;
                    }
                }

                r1 = r5;
                break label_21;
            }
        } //end label_21:


        return r1;
    }

    ViewPager$ItemInfo infoForPosition(int  i0)
    {

        int i1;
        ViewPager$ItemInfo r4;
        i1 = 0;

        label_22:
        {
            while (i1 < mItems.size())
            {
                r4 = (ViewPager$ItemInfo) mItems.get(i1);

                if (r4.position != i0)
                {
                    i1 = i1 + 1;
                }
                else
                {
                    break label_22;
                }
            }

            r4 = null;
        } //end label_22:


        return r4;
    }

    void initViewPager()
    {

        Context r1;
        ViewConfiguration r3;
        float f0;
        this.setWillNotDraw(false);
        this.setDescendantFocusability(262144);
        this.setFocusable(true);
        r1 = this.getContext();
        mScroller = new Scroller(r1, sInterpolator);
        r3 = ViewConfiguration.get(r1);
        f0 = r1.getResources().getDisplayMetrics().density;
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(r3);
        mMinimumVelocity = (int) (400.0F * f0);
        mMaximumVelocity = r3.getScaledMaximumFlingVelocity();
        mLeftEdge = new EdgeEffectCompat(r1);
        mRightEdge = new EdgeEffectCompat(r1);
        mFlingDistance = (int) (25.0F * f0);
        mCloseEnough = (int) (2.0F * f0);
        mDefaultGutterSize = (int) (16.0F * f0);
        ViewCompat.setAccessibilityDelegate(this, new ViewPager$MyAccessibilityDelegate(this));

        if (ViewCompat.getImportantForAccessibility(this) == 0)
        {
            ViewCompat.setImportantForAccessibility(this, (int) 1);
        }
    }

    public boolean isFakeDragging()
    {


        return mFakeDragging;
    }

    private boolean isGutterDrag(float  f0, float  f1)
    {

        boolean z0;
        label_25:
        {
            label_24:
            {
                label_23:
                {
                    if (f0 - (float) mGutterSize < 0)
                    {
                        if (f1 - 0.0F > 0)
                        {
                            break label_23;
                        }
                    }

                    if (f0 - (float) (this.getWidth() - mGutterSize) <= 0)
                    {
                        break label_24;
                    }
                    else
                    {
                        if (f1 - 0.0F >= 0)
                        {
                            break label_24;
                        }
                    }
                } //end label_23:


                z0 = true;
                break label_25;
            } //end label_24:


            z0 = false;
        } //end label_25:


        return z0;
    }

    protected void onAttachedToWindow()
    {


        this.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {


        this.removeCallbacks(mEndScrollRunnable);
        this.onDetachedFromWindow();
    }

    protected void onDraw(Canvas  r1)
    {

        int i1, i2, i3, i4, i5, i6, i7;
        float f1, f2, f3, f5;
        ViewPager$ItemInfo r13;
        this.onDraw(r1);

        label_26:
        if (mPageMargin > 0)
        {
            if (mMarginDrawable != null)
            {
                if (mItems.size() > 0)
                {
                    if (mAdapter != null)
                    {
                        i1 = this.getScrollX();
                        i2 = this.getWidth();
                        f1 = (float) mPageMargin / (float) i2;
                        i3 = 0;
                        r13 = (ViewPager$ItemInfo) mItems.get(0);
                        f2 = r13.offset;
                        i4 = mItems.size();
                        i5 = r13.position;
                        i6 = ((ViewPager$ItemInfo) mItems.get(i4 + -1)).position;
                        i7 = i5;

                        while (i7 < i6)
                        {
                            if (i7 > r13.position)
                            {
                                if (i3 < i4)
                                {
                                    i3 = i3 + 1;
                                    r13 = (ViewPager$ItemInfo) mItems.get(i3);
                                }
                            }

                            if (i7 != r13.position)
                            {
                                f5 = mAdapter.getPageWidth(i7);
                                f3 = (f2 + f5) * (float) i2;
                                f2 = f2 + (f5 + f1);
                            }
                            else
                            {
                                f3 = (r13.offset + r13.widthFactor) * (float) i2;
                                f2 = r13.offset + r13.widthFactor + f1;
                            }

                            if ((float) mPageMargin + f3 - (float) i1 > 0)
                            {
                                mMarginDrawable.setBounds((int) f3, mTopPageBounds, (int) ((float) mPageMargin + f3 + 0.5F), mBottomPageBounds);
                                mMarginDrawable.draw(r1);
                            }

                            if (f3 - (float) (i1 + i2) <= 0)
                            {
                                i7 = i7 + 1;
                            }
                            else
                            {
                                break label_26;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent  r1)
    {

        int i1, i2, i3;
        float f0, f1, f2, f3, f4, f16, f20, f21;
        boolean z3;
        i1 = r1.getAction() + 255;

        label_29:
        {
            if (i1 != 3)
            {
                if (i1 != 1)
                {
                    if (i1 != 0)
                    {
                        if (mIsBeingDragged == false)
                        {
                            if (mIsUnableToDrag != false)
                            {
                                z3 = false;
                                break label_29;
                            }
                        }
                        else
                        {
                            z3 = true;
                            break label_29;
                        }
                    }

                    label_28:
                    switch (i1)
                    {
                        case 0:
                            f20 = r1.getX();
                            mInitialMotionX = f20;
                            mLastMotionX = f20;
                            f21 = r1.getY();
                            mInitialMotionY = f21;
                            mLastMotionY = f21;
                            mActivePointerId = MotionEventCompat.getPointerId(r1, 0);
                            mIsUnableToDrag = false;
                            mScroller.computeScrollOffset();

                            if (mScrollState == 2)
                            {
                                if (Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough)
                                {
                                    mScroller.abortAnimation();
                                    mPopulatePending = false;
                                    this.populate();
                                    mIsBeingDragged = true;
                                    this.requestParentDisallowInterceptTouchEvent(true);
                                    this.setScrollState(1);
                                    break label_28;
                                }
                            }

                            this.completeScroll(false);
                            mIsBeingDragged = false;
                            break label_28;

                        case 2:
                            i2 = mActivePointerId;

                            if (i2 == -1)
                            {
                                break label_28;
                            }
                            else
                            {
                                i3 = MotionEventCompat.findPointerIndex(r1, i2);
                                f0 = MotionEventCompat.getX(r1, i3);
                                f1 = f0 - mLastMotionX;
                                f2 = Math.abs(f1);
                                f3 = MotionEventCompat.getY(r1, i3);
                                f4 = Math.abs(f3 - mInitialMotionY);

                                if (f1 - 0.0F != 0)
                                {
                                    if (this.isGutterDrag(mLastMotionX, f1) == false)
                                    {
                                        if (this.canScroll(this, false, (int) f1, (int) f0, (int) f3) != false)
                                        {
                                            mLastMotionX = f0;
                                            mLastMotionY = f3;
                                            mIsUnableToDrag = true;
                                            z3 = false;
                                            break label_29;
                                        }
                                    }
                                }

                                label_27:
                                {
                                    if (f2 - (float) mTouchSlop > 0)
                                    {
                                        if (0.5F * f2 - f4 > 0)
                                        {
                                            mIsBeingDragged = true;
                                            this.requestParentDisallowInterceptTouchEvent(true);
                                            this.setScrollState(1);

                                            if (f1 - 0.0F <= 0)
                                            {
                                                f16 = mInitialMotionX - (float) mTouchSlop;
                                            }
                                            else
                                            {
                                                f16 = mInitialMotionX + (float) mTouchSlop;
                                            }

                                            mLastMotionX = f16;
                                            mLastMotionY = f3;
                                            this.setScrollingCacheEnabled(true);
                                            break label_27;
                                        }
                                    }

                                    if (f4 - (float) mTouchSlop > 0)
                                    {
                                        mIsUnableToDrag = true;
                                    }
                                } //end label_27:


                                if (mIsBeingDragged == false)
                                {
                                    break label_28;
                                }
                                else
                                {
                                    if (this.performDrag(f0) == false)
                                    {
                                        break label_28;
                                    }
                                    else
                                    {
                                        ViewCompat.postInvalidateOnAnimation(this);
                                        break label_28;
                                    }
                                }
                            }

                        case 6:
                            this.onSecondaryPointerUp(r1);
                            break label_28;

                        default:
                            break label_28;
                    }

                    if (mVelocityTracker == null)
                    {
                        mVelocityTracker = VelocityTracker.obtain();
                    }

                    mVelocityTracker.addMovement(r1);
                    z3 = mIsBeingDragged;
                    break label_29;
                }
            }

            mIsBeingDragged = false;
            mIsUnableToDrag = false;
            mActivePointerId = -1;

            if (mVelocityTracker != null)
            {
                mVelocityTracker.recycle();
                mVelocityTracker = null;
            }

            z3 = false;
        } //end label_29:


        return z3;
    }

    protected void onLayout(boolean  z0, int  i0, int  i1, int  i2, int  i3)
    {

        int i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i18, i19, i30, i31, i32, i58, i64, i65;
        View r2, r10;
        ViewPager$ItemInfo r4;
        ViewPager$LayoutParams r5, r12;
        i4 = this.getChildCount();
        i5 = i2 - i0;
        i6 = i3 - i1;
        i7 = this.getPaddingLeft();
        i8 = this.getPaddingTop();
        i9 = this.getPaddingRight();
        i10 = this.getPaddingBottom();
        i11 = this.getScrollX();
        i12 = 0;
        i13 = 0;

        while (i13 < i4)
        {
            r2 = this.getChildAt(i13);

            if (r2.getVisibility() != 8)
            {
                r5 = (ViewPager$LayoutParams) r2.getLayoutParams();

                if (r5.isDecor != false)
                {
                    i18 = r5.gravity & 112;

                    label_30:
                    switch (r5.gravity & 7)
                    {
                        case 1:
                            i30 = Math.max((i5 - r2.getMeasuredWidth()) / 2, i7);
                            break label_30;

                        case 3:
                            i30 = i7;
                            i7 = i7 + r2.getMeasuredWidth();
                            break label_30;

                        case 5:
                            i30 = i5 - i9 - r2.getMeasuredWidth();
                            i9 = i9 + r2.getMeasuredWidth();
                            break label_30;

                        case 2:
                        case 4:
                        default:
                            i30 = i7;
                            break label_30;
                    }

                    label_31:
                    switch (i18)
                    {
                        case 16:
                            i31 = Math.max((i6 - r2.getMeasuredHeight()) / 2, i8);
                            break label_31;

                        case 48:
                            i31 = i8;
                            i8 = i8 + r2.getMeasuredHeight();
                            break label_31;

                        case 80:
                            i31 = i6 - i10 - r2.getMeasuredHeight();
                            i10 = i10 + r2.getMeasuredHeight();
                            break label_31;

                        default:
                            i31 = i8;
                            break label_31;
                    }

                    i32 = i30 + i11;
                    r2.layout(i32, i31, r2.getMeasuredWidth() + i32, r2.getMeasuredHeight() + i31);
                    i12 = i12 + 1;
                }
            }

            i13 = i13 + 1;
        }

        i19 = i5 - i7 - i9;
        i58 = 0;

        while (i58 < i4)
        {
            r10 = this.getChildAt(i58);

            if (r10.getVisibility() != 8)
            {
                r12 = (ViewPager$LayoutParams) r10.getLayoutParams();

                if (r12.isDecor == false)
                {
                    r4 = this.infoForChild(r10);

                    if (r4 != null)
                    {
                        i64 = i7 + (int) ((float) i19 * r4.offset);
                        i65 = i8;

                        if (r12.needsMeasure != false)
                        {
                            r12.needsMeasure = false;
                            r10.measure(View$MeasureSpec.makeMeasureSpec((int) ((float) i19 * r12.widthFactor), 1073741824), View$MeasureSpec.makeMeasureSpec(i6 - i8 - i10, 1073741824));
                        }

                        r10.layout(i64, i65, r10.getMeasuredWidth() + i64, r10.getMeasuredHeight() + i65);
                    }
                }
            }

            i58 = i58 + 1;
        }

        mTopPageBounds = i8;
        mBottomPageBounds = i6 - i10;
        mDecorChildCount = i12;

        if (mFirstLayout != false)
        {
            this.scrollToItem(mCurItem, false, 0, false);
        }

        mFirstLayout = false;
    }

    protected void onMeasure(int  i0, int  i1)
    {

        int i7, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i91, i92;
        View r1, r13;
        boolean z0, z1;
        ViewPager$LayoutParams r7, r15;
        this.setMeasuredDimension(ViewPager.getDefaultSize(0, i0), ViewPager.getDefaultSize(0, i1));
        i7 = this.getMeasuredWidth();
        mGutterSize = Math.min(i7 / 10, mDefaultGutterSize);
        i9 = i7 - this.getPaddingLeft() - this.getPaddingRight();
        i10 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
        i11 = this.getChildCount();
        i12 = 0;

        while (i12 < i11)
        {
            r1 = this.getChildAt(i12);

            if (r1.getVisibility() != 8)
            {
                r7 = (ViewPager$LayoutParams) r1.getLayoutParams();

                if (r7 != null)
                {
                    if (r7.isDecor != false)
                    {
                        i13 = r7.gravity & 7;
                        i14 = r7.gravity & 112;
                        i15 = -2147483648;
                        i16 = -2147483648;

                        label_32:
                        {
                            if (i14 != 48)
                            {
                                if (i14 != 80)
                                {
                                    z0 = false;
                                    break label_32;
                                }
                            }

                            z0 = true;
                        } //end label_32:


                        label_33:
                        {
                            if (i13 != 3)
                            {
                                if (i13 != 5)
                                {
                                    z1 = false;
                                    break label_33;
                                }
                            }

                            z1 = true;
                        } //end label_33:


                        if (z0 == false)
                        {
                            if (z1 != false)
                            {
                                i16 = 1073741824;
                            }
                        }
                        else
                        {
                            i15 = 1073741824;
                        }

                        i17 = i9;
                        i18 = i10;

                        if (r7.width != -2)
                        {
                            i15 = 1073741824;

                            if (r7.width != -1)
                            {
                                i17 = r7.width;
                            }
                        }

                        if (r7.height != -2)
                        {
                            i16 = 1073741824;

                            if (r7.height != -1)
                            {
                                i18 = r7.height;
                            }
                        }

                        r1.measure(View$MeasureSpec.makeMeasureSpec(i17, i15), View$MeasureSpec.makeMeasureSpec(i18, i16));

                        if (z0 == false)
                        {
                            if (z1 != false)
                            {
                                i9 = i9 - r1.getMeasuredWidth();
                            }
                        }
                        else
                        {
                            i10 = i10 - r1.getMeasuredHeight();
                        }
                    }
                }
            }

            i12 = i12 + 1;
        }

        mChildWidthMeasureSpec = View$MeasureSpec.makeMeasureSpec(i9, 1073741824);
        mChildHeightMeasureSpec = View$MeasureSpec.makeMeasureSpec(i10, 1073741824);
        mInLayout = true;
        this.populate();
        mInLayout = false;
        i91 = this.getChildCount();
        i92 = 0;

        while (i92 < i91)
        {
            r13 = this.getChildAt(i92);

            label_34:
            if (r13.getVisibility() != 8)
            {
                r15 = (ViewPager$LayoutParams) r13.getLayoutParams();

                if (r15 != null)
                {
                    if (r15.isDecor != false)
                    {
                        break label_34;
                    }
                }

                r13.measure(View$MeasureSpec.makeMeasureSpec((int) ((float) i9 * r15.widthFactor), 1073741824), mChildHeightMeasureSpec);
            }

            i92 = i92 + 1;
        }
    }

    protected void onPageScrolled(int  i0, float  f0, int  i1)
    {

        int i3, i4, i5, i6, i7, i8, i10, i14, i26, i27, i28;
        View r2, r17;
        float f2;
        ViewPager$LayoutParams r5;
        if (mDecorChildCount > 0)
        {
            i3 = this.getScrollX();
            i4 = this.getPaddingLeft();
            i5 = this.getPaddingRight();
            i6 = this.getWidth();
            i7 = this.getChildCount();
            i8 = 0;

            while (i8 < i7)
            {
                r2 = this.getChildAt(i8);
                r5 = (ViewPager$LayoutParams) r2.getLayoutParams();

                if (r5.isDecor != false)
                {
                    label_35:
                    switch (r5.gravity & 7)
                    {
                        case 1:
                            i14 = Math.max((i6 - r2.getMeasuredWidth()) / 2, i4);
                            break label_35;

                        case 3:
                            i14 = i4;
                            i4 = i4 + r2.getWidth();
                            break label_35;

                        case 5:
                            i14 = i6 - i5 - r2.getMeasuredWidth();
                            i5 = i5 + r2.getMeasuredWidth();
                            break label_35;

                        case 2:
                        case 4:
                        default:
                            i14 = i4;
                            break label_35;
                    }

                    i10 = i14 + i3 - r2.getLeft();

                    if (i10 != 0)
                    {
                        r2.offsetLeftAndRight(i10);
                    }
                }

                i8 = i8 + 1;
            }
        }

        if (mOnPageChangeListener != null)
        {
            mOnPageChangeListener.onPageScrolled(i0, f0, i1);
        }

        if (mInternalPageChangeListener != null)
        {
            mInternalPageChangeListener.onPageScrolled(i0, f0, i1);
        }

        if (mPageTransformer != null)
        {
            i26 = this.getScrollX();
            i27 = this.getChildCount();
            i28 = 0;

            while (i28 < i27)
            {
                r17 = this.getChildAt(i28);

                if (((ViewPager$LayoutParams) r17.getLayoutParams()).isDecor == false)
                {
                    f2 = (float) (r17.getLeft() - i26) / (float) this.getClientWidth();
                    mPageTransformer.transformPage(r17, f2);
                }

                i28 = i28 + 1;
            }
        }

        mCalledSuper = true;
    }

    protected boolean onRequestFocusInDescendants(int  i0, Rect  r1)
    {

        int i1, i3, i5, i6;
        byte b4;
        View r2;
        ViewPager$ItemInfo r3;
        boolean z1;
        i1 = this.getChildCount();

        if ((i0 & 2) == 0)
        {
            i3 = i1 + -1;
            b4 = (byte) (byte) -1;
            i5 = -1;
        }
        else
        {
            i3 = 0;
            b4 = (byte) (byte) 1;
            i5 = i1;
        }

        i6 = i3;

        label_36:
        {
            while (i6 != i5)
            {
                r2 = this.getChildAt(i6);

                if (r2.getVisibility() == 0)
                {
                    r3 = this.infoForChild(r2);

                    if (r3 != null)
                    {
                        if (r3.position == mCurItem)
                        {
                            if (r2.requestFocus(i0, r1) != false)
                            {
                                z1 = true;
                                break label_36;
                            }
                        }
                    }
                }

                i6 = i6 + b4;
            }

            z1 = false;
        } //end label_36:


        return z1;
    }

    public void onRestoreInstanceState(Parcelable  r1)
    {

        ViewPager$SavedState r5;
        if (r1 instanceof ViewPager$SavedState != false)
        {
            r5 = (ViewPager$SavedState) r1;
            this.onRestoreInstanceState(r5.getSuperState());

            if (mAdapter == null)
            {
                mRestoredCurItem = r5.position;
                mRestoredAdapterState = r5.adapterState;
                mRestoredClassLoader = r5.loader;
            }
            else
            {
                mAdapter.restoreState(r5.adapterState, r5.loader);
                this.setCurrentItemInternal(r5.position, false, true);
            }
        }
        else
        {
            this.onRestoreInstanceState(r1);
        }
    }

    public Parcelable onSaveInstanceState()
    {

        ViewPager$SavedState r2;
        r2 = new ViewPager$SavedState(this.onSaveInstanceState());
        r2.position = mCurItem;

        if (mAdapter != null)
        {
            r2.adapterState = mAdapter.saveState();
        }

        return r2;
    }

    private void onSecondaryPointerUp(MotionEvent  r1)
    {

        int i0;
        byte b3;
        i0 = MotionEventCompat.getActionIndex(r1);

        if (MotionEventCompat.getPointerId(r1, i0) == mActivePointerId)
        {
            if (i0 != 0)
            {
                b3 = (byte) (byte) 0;
            }
            else
            {
                b3 = (byte) (byte) 1;
            }

            mLastMotionX = MotionEventCompat.getX(r1, b3);
            mActivePointerId = MotionEventCompat.getPointerId(r1, b3);

            if (mVelocityTracker != null)
            {
                mVelocityTracker.clear();
            }
        }
    }

    protected void onSizeChanged(int  i0, int  i1, int  i2, int  i3)
    {


        this.onSizeChanged(i0, i1, i2, i3);

        if (i0 != i2)
        {
            this.recomputeScrollPosition(i0, i2, mPageMargin, mPageMargin);
        }
    }

    public boolean onTouchEvent(MotionEvent  r1)
    {

        boolean z1, z5;
        int i1, i3, i4, i5, i11;
        float f0, f1, f2, f3, f7, f10, f28;
        ViewParent r4;
        ViewPager$ItemInfo r6;
        VelocityTracker r59;
        label_38:
        if (mFakeDragging == false)
        {
            if (r1.getAction() == 0)
            {
                if (r1.getEdgeFlags() != 0)
                {
                    z5 = false;
                    break label_38;
                }
            }

            if (mAdapter != null)
            {
                if (mAdapter.getCount() != 0)
                {
                    if (mVelocityTracker == null)
                    {
                        mVelocityTracker = VelocityTracker.obtain();
                    }

                    mVelocityTracker.addMovement(r1);
                    z1 = false;

                    label_37:
                    switch (r1.getAction() + 255)
                    {
                        case 0:
                            mScroller.abortAnimation();
                            mPopulatePending = false;
                            this.populate();
                            f7 = r1.getX();
                            mInitialMotionX = f7;
                            mLastMotionX = f7;
                            f10 = r1.getY();
                            mInitialMotionY = f10;
                            mLastMotionY = f10;
                            mActivePointerId = MotionEventCompat.getPointerId(r1, 0);
                            break label_37;

                        case 1:
                            if (mIsBeingDragged == false)
                            {
                                break label_37;
                            }
                            else
                            {
                                r59 = mVelocityTracker;
                                r59.computeCurrentVelocity(1000, (float) mMaximumVelocity);
                                i3 = (int) VelocityTrackerCompat.getXVelocity(r59, mActivePointerId);
                                mPopulatePending = true;
                                i4 = this.getClientWidth();
                                i5 = this.getScrollX();
                                r6 = this.infoForCurrentScrollPosition();
                                this.setCurrentItemInternal(this.determineTargetPage(r6.position, ((float) i5 / (float) i4 - r6.offset) / r6.widthFactor, i3, (int) (MotionEventCompat.getX(r1, MotionEventCompat.findPointerIndex(r1, mActivePointerId)) - mInitialMotionX)), true, true, i3);
                                mActivePointerId = -1;
                                this.endDrag();
                                z1 = mLeftEdge.onRelease() | mRightEdge.onRelease();
                                break label_37;
                            }

                        case 2:
                            if (mIsBeingDragged == false)
                            {
                                i1 = MotionEventCompat.findPointerIndex(r1, mActivePointerId);
                                f0 = MotionEventCompat.getX(r1, i1);
                                f1 = Math.abs(f0 - mLastMotionX);
                                f2 = MotionEventCompat.getY(r1, i1);
                                f3 = Math.abs(f2 - mLastMotionY);

                                if (f1 - (float) mTouchSlop > 0)
                                {
                                    if (f1 - f3 > 0)
                                    {
                                        mIsBeingDragged = true;
                                        this.requestParentDisallowInterceptTouchEvent(true);

                                        if (f0 - mInitialMotionX - 0.0F <= 0)
                                        {
                                            f28 = mInitialMotionX - (float) mTouchSlop;
                                        }
                                        else
                                        {
                                            f28 = mInitialMotionX + (float) mTouchSlop;
                                        }

                                        mLastMotionX = f28;
                                        mLastMotionY = f2;
                                        this.setScrollState(1);
                                        this.setScrollingCacheEnabled(true);
                                        r4 = this.getParent();

                                        if (r4 != null)
                                        {
                                            r4.requestDisallowInterceptTouchEvent(true);
                                        }
                                    }
                                }
                            }

                            if (mIsBeingDragged == false)
                            {
                                break label_37;
                            }
                            else
                            {
                                z1 = 0 | this.performDrag(MotionEventCompat.getX(r1, MotionEventCompat.findPointerIndex(r1, mActivePointerId)));
                                break label_37;
                            }

                        case 3:
                            if (mIsBeingDragged == false)
                            {
                                break label_37;
                            }
                            else
                            {
                                this.scrollToItem(mCurItem, true, 0, false);
                                mActivePointerId = -1;
                                this.endDrag();
                                z1 = mLeftEdge.onRelease() | mRightEdge.onRelease();
                                break label_37;
                            }

                        case 5:
                            i11 = MotionEventCompat.getActionIndex(r1);
                            mLastMotionX = MotionEventCompat.getX(r1, i11);
                            mActivePointerId = MotionEventCompat.getPointerId(r1, i11);
                            break label_37;

                        case 6:
                            this.onSecondaryPointerUp(r1);
                            mLastMotionX = MotionEventCompat.getX(r1, MotionEventCompat.findPointerIndex(r1, mActivePointerId));
                            break label_37;

                        case 4:
                        default:
                            break label_37;
                    }

                    if (z1 != false)
                    {
                        ViewCompat.postInvalidateOnAnimation(this);
                    }

                    z5 = true;
                    break label_38;
                }
            }

            z5 = false;
        }
        else
        {
            z5 = true;
        }

        return z5;
    }

    boolean pageLeft()
    {

        boolean z0;
        z0 = true;

        if (mCurItem <= 0)
        {
            z0 = false;
        }
        else
        {
            this.setCurrentItem(mCurItem + -1, true);
        }

        return z0;
    }

    boolean pageRight()
    {

        boolean z0;
        z0 = true;

        label_39:
        {
            if (mAdapter != null)
            {
                if (mCurItem < mAdapter.getCount() + -1)
                {
                    this.setCurrentItem(mCurItem + 1, true);
                    break label_39;
                }
            }

            z0 = false;
        } //end label_39:


        return z0;
    }

    private boolean pageScrolled(int  i0)
    {

        boolean z0;
        ViewPager$ItemInfo r2;
        int i1;
        float f2;
        z0 = false;

        if (mItems.size() != 0)
        {
            r2 = this.infoForCurrentScrollPosition();
            i1 = this.getClientWidth();
            f2 = ((float) i0 / (float) i1 - r2.offset) / (r2.widthFactor + (float) mPageMargin / (float) i1);
            mCalledSuper = false;
            this.onPageScrolled(r2.position, f2, (int) ((float) (i1 + mPageMargin) * f2));

            if (mCalledSuper != false)
            {
                z0 = true;
            }
            else
            {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
        }
        else
        {
            mCalledSuper = false;
            this.onPageScrolled((int) 0, 0.0F, (int) 0);

            if (mCalledSuper == false)
            {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
        }

        return z0;
    }

    private boolean performDrag(float  f0)
    {

        boolean z0, z1, z2;
        float f2, f4, f6, f7;
        int i0;
        ViewPager$ItemInfo r9, r14;
        z0 = false;
        f2 = mLastMotionX - f0;
        mLastMotionX = f0;
        f4 = (float) this.getScrollX() + f2;
        i0 = this.getClientWidth();
        f6 = (float) i0 * mFirstOffset;
        f7 = (float) i0 * mLastOffset;
        z1 = true;
        z2 = true;
        r9 = (ViewPager$ItemInfo) mItems.get(0);
        r14 = (ViewPager$ItemInfo) mItems.get(mItems.size() + -1);

        if (r9.position != 0)
        {
            z1 = false;
            f6 = r9.offset * (float) i0;
        }

        if (r14.position != mAdapter.getCount() + -1)
        {
            z2 = false;
            f7 = r14.offset * (float) i0;
        }

        if (f4 - f6 >= 0)
        {
            if (f4 - f7 > 0)
            {
                if (z2 != false)
                {
                    z0 = mRightEdge.onPull(Math.abs(f4 - f7) / (float) i0);
                }

                f4 = f7;
            }
        }
        else
        {
            if (z1 != false)
            {
                z0 = mLeftEdge.onPull(Math.abs(f6 - f4) / (float) i0);
            }

            f4 = f6;
        }

        mLastMotionX = mLastMotionX + (f4 - (float) (int) f4);
        this.scrollTo((int) f4, this.getScrollY());
        this.pageScrolled((int) f4);
        return z0;
    }

    void populate()
    {


        this.populate(mCurItem);
    }

    void populate(int  i0)
    {

        ViewPager$ItemInfo r1, r6, r70, r83, r94, r117, r198, r210, r216;
        byte b1;
        int i6, i7, i8, i10, i11, i12, i14, i15, i26, $i37, i42, i66, i74, i78, i137;
        String r3, $r35;
        float f0, f1, f2, f3;
        View r9, r11, r214;
        Resources $r33;
        PagerAdapter r99;
        Object r103;
        ViewPager$LayoutParams r112;
        r1 = null;
        b1 = (byte) (byte) 2;

        if (mCurItem != i0)
        {
            if (mCurItem >= i0)
            {
                b1 = (byte) (byte) 17;
            }
            else
            {
                b1 = (byte) (byte) 66;
            }

            r1 = this.infoForPosition(mCurItem);
            mCurItem = i0;
        }

        label_48:
        if (mAdapter != null)
        {
            if (mPopulatePending == false)
            {
                if (this.getWindowToken() != null)
                {
                    mAdapter.startUpdate(this);
                    i26 = mOffscreenPageLimit;
                    i6 = Math.max(0, mCurItem - i26);
                    i7 = mAdapter.getCount();
                    i8 = Math.min(i7 + -1, mCurItem + i26);

                    if (i7 == mExpectedAdapterCount)
                    {
                        r6 = null;
                        i42 = 0;

                        label_46:
                        {
                            while (i42 < mItems.size())
                            {
                                r70 = (ViewPager$ItemInfo) mItems.get(i42);

                                if (r70.position < mCurItem)
                                {
                                    i42 = i42 + 1;
                                }
                                else
                                {
                                    if (r70.position != mCurItem)
                                    {
                                        break label_46;
                                    }
                                    else
                                    {
                                        r6 = r70;
                                        break label_46;
                                    }
                                }
                            }
                        } //end label_46:


                        if (r6 == null)
                        {
                            if (i7 > 0)
                            {
                                r6 = this.addNewItem(mCurItem, i42);
                            }
                        }

                        if (r6 != null)
                        {
                            f0 = 0.0F;
                            i10 = i42 + -1;

                            if (i10 < 0)
                            {
                                r83 = null;
                            }
                            else
                            {
                                r83 = (ViewPager$ItemInfo) mItems.get(i10);
                            }

                            i11 = this.getClientWidth();

                            if (i11 > 0)
                            {
                                f1 = 2.0F - r6.widthFactor + (float) this.getPaddingLeft() / (float) i11;
                            }
                            else
                            {
                                f1 = 0.0F;
                            }

                            i12 = mCurItem + -1;

                            label_42:
                            {
                                while (i12 >= 0)
                                {
                                    label_40:
                                    {
                                        if (f0 - f1 >= 0)
                                        {
                                            if (i12 < i6)
                                            {
                                                if (r83 != null)
                                                {
                                                    if (i12 != r83.position)
                                                    {
                                                        break label_40;
                                                    }
                                                    else
                                                    {
                                                        if (r83.scrolling != false)
                                                        {
                                                            break label_40;
                                                        }
                                                        else
                                                        {
                                                            mItems.remove(i10);
                                                            mAdapter.destroyItem(this, i12, r83.object);
                                                            i10 = i10 + -1;
                                                            i42 = i42 + -1;

                                                            if (i10 < 0)
                                                            {
                                                                r83 = null;
                                                                break label_40;
                                                            }
                                                            else
                                                            {
                                                                r83 = (ViewPager$ItemInfo) mItems.get(i10);
                                                                break label_40;
                                                            }
                                                        }
                                                    }
                                                }
                                                else
                                                {
                                                    break label_42;
                                                }
                                            }
                                        }

                                        if (r83 != null)
                                        {
                                            if (i12 == r83.position)
                                            {
                                                f0 = f0 + r83.widthFactor;
                                                i10 = i10 + -1;

                                                if (i10 < 0)
                                                {
                                                    r83 = null;
                                                    break label_40;
                                                }
                                                else
                                                {
                                                    r83 = (ViewPager$ItemInfo) mItems.get(i10);
                                                    break label_40;
                                                }
                                            }
                                        }

                                        f0 = f0 + this.addNewItem(i12, i10 + 1).widthFactor;
                                        i42 = i42 + 1;

                                        if (i10 < 0)
                                        {
                                            r83 = null;
                                        }
                                        else
                                        {
                                            r83 = (ViewPager$ItemInfo) mItems.get(i10);
                                        }
                                    } //end label_40:


                                    i12 = i12 + -1;
                                }
                            } //end label_42:


                            f2 = r6.widthFactor;
                            i66 = i42 + 1;

                            label_43:
                            if (f2 - 2.0F < 0)
                            {
                                if (i66 >= mItems.size())
                                {
                                    r94 = null;
                                }
                                else
                                {
                                    r94 = (ViewPager$ItemInfo) mItems.get(i66);
                                }

                                if (i11 > 0)
                                {
                                    f3 = (float) this.getPaddingRight() / (float) i11 + 2.0F;
                                }
                                else
                                {
                                    f3 = 0.0F;
                                }

                                i74 = mCurItem + 1;

                                while (i74 < i7)
                                {
                                    label_41:
                                    {
                                        if (f2 - f3 >= 0)
                                        {
                                            if (i74 > i8)
                                            {
                                                if (r94 != null)
                                                {
                                                    if (i74 != r94.position)
                                                    {
                                                        break label_41;
                                                    }
                                                    else
                                                    {
                                                        if (r94.scrolling != false)
                                                        {
                                                            break label_41;
                                                        }
                                                        else
                                                        {
                                                            mItems.remove(i66);
                                                            mAdapter.destroyItem(this, i74, r94.object);

                                                            if (i66 >= mItems.size())
                                                            {
                                                                r94 = null;
                                                                break label_41;
                                                            }
                                                            else
                                                            {
                                                                r94 = (ViewPager$ItemInfo) mItems.get(i66);
                                                                break label_41;
                                                            }
                                                        }
                                                    }
                                                }
                                                else
                                                {
                                                    break label_43;
                                                }
                                            }
                                        }

                                        if (r94 != null)
                                        {
                                            if (i74 == r94.position)
                                            {
                                                f2 = f2 + r94.widthFactor;
                                                i66 = i66 + 1;

                                                if (i66 >= mItems.size())
                                                {
                                                    r94 = null;
                                                    break label_41;
                                                }
                                                else
                                                {
                                                    r94 = (ViewPager$ItemInfo) mItems.get(i66);
                                                    break label_41;
                                                }
                                            }
                                        }

                                        r198 = this.addNewItem(i74, i66);
                                        i66 = i66 + 1;
                                        f2 = f2 + r198.widthFactor;

                                        if (i66 >= mItems.size())
                                        {
                                            r94 = null;
                                        }
                                        else
                                        {
                                            r94 = (ViewPager$ItemInfo) mItems.get(i66);
                                        }
                                    } //end label_41:


                                    i74 = i74 + 1;
                                }
                            }

                            this.calculatePageOffsets(r6, i42, r1);
                        }

                        r99 = mAdapter;
                        i78 = mCurItem;

                        if (r6 == null)
                        {
                            r103 = null;
                        }
                        else
                        {
                            r103 = r6.object;
                        }

                        r99.setPrimaryItem(this, i78, r103);
                        mAdapter.finishUpdate(this);
                        i14 = this.getChildCount();
                        i15 = 0;

                        while (i15 < i14)
                        {
                            r9 = this.getChildAt(i15);
                            r112 = (ViewPager$LayoutParams) r9.getLayoutParams();
                            r112.childIndex = i15;

                            if (r112.isDecor == false)
                            {
                                if (r112.widthFactor - 0.0F == 0)
                                {
                                    r117 = this.infoForChild(r9);

                                    if (r117 != null)
                                    {
                                        r112.widthFactor = r117.widthFactor;
                                        r112.position = r117.position;
                                    }
                                }
                            }

                            i15 = i15 + 1;
                        }

                        this.sortChildDrawingOrder();

                        if (this.hasFocus() != false)
                        {
                            r11 = this.findFocus();

                            if (r11 == null)
                            {
                                r210 = null;
                            }
                            else
                            {
                                r210 = this.infoForAnyChild(r11);
                            }

                            if (r210 != null)
                            {
                                if (r210.position == mCurItem)
                                {
                                    break label_48;
                                }
                            }

                            i137 = 0;

                            while (i137 < this.getChildCount())
                            {
                                r214 = this.getChildAt(i137);
                                r216 = this.infoForChild(r214);

                                if (r216 != null)
                                {
                                    if (r216.position == mCurItem)
                                    {
                                        if (r214.requestFocus(b1) != false)
                                        {
                                            break label_48;
                                        }
                                    }
                                }

                                i137 = i137 + 1;
                            }
                        }
                    }
                    else
                    {
                        label_47:
                        {
                            label_45:
                            {
                                label_44:
                                {
                                    try
                                    {
                                        $r33 = this.getResources();
                                    }
                                    catch (Resources$NotFoundException $r62)
                                    {
                                        break label_44;
                                    }

                                    try
                                    {
                                        $i37 = this.getId();
                                    }
                                    catch (Resources$NotFoundException $r62)
                                    {
                                        break label_44;
                                    }

                                    try
                                    {
                                        $r35 = $r33.getResourceName($i37);
                                        break label_45;
                                    }
                                    catch (Resources$NotFoundException $r62)
                                    {
                                    }
                                } //end label_44:


                                r3 = Integer.toHexString(this.getId());
                                break label_47;
                            } //end label_45:


                            r3 = $r35;
                        } //end label_47:


                        throw new IllegalStateException((new StringBuilder()).append("The application\'s PagerAdapter changed the adapter\'s contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ").append(mExpectedAdapterCount).append(", found: ").append(i7).append(" Pager id: ").append(r3).append(" Pager class: ").append(this.getClass()).append(" Problematic adapter: ").append(mAdapter.getClass()).toString());
                    }
                }
            }
            else
            {
                this.sortChildDrawingOrder();
            }
        }
        else
        {
            this.sortChildDrawingOrder();
        }
    }

    private void recomputeScrollPosition(int  i0, int  i1, int  i2, int  i3)
    {

        int i5, i6, i8, i9, i10;
        ViewPager$ItemInfo r2, r3;
        float f3;
        label_49:
        {
            if (i1 > 0)
            {
                if (mItems.isEmpty() == false)
                {
                    i5 = i0 - this.getPaddingLeft() - this.getPaddingRight() + i2;
                    i6 = i1 - this.getPaddingLeft() - this.getPaddingRight() + i3;
                    i8 = (int) ((float) i5 * ((float) this.getScrollX() / (float) i6));
                    this.scrollTo(i8, this.getScrollY());

                    if (mScroller.isFinished() != false)
                    {
                        break label_49;
                    }
                    else
                    {
                        i9 = mScroller.getDuration() - mScroller.timePassed();
                        r2 = this.infoForPosition(mCurItem);
                        mScroller.startScroll(i8, 0, (int) (r2.offset * (float) i0), 0, i9);
                        break label_49;
                    }
                }
            }

            r3 = this.infoForPosition(mCurItem);

            if (r3 == null)
            {
                f3 = 0.0F;
            }
            else
            {
                f3 = Math.min(r3.offset, mLastOffset);
            }

            i10 = (int) ((float) (i0 - this.getPaddingLeft() - this.getPaddingRight()) * f3);

            if (i10 != this.getScrollX())
            {
                this.completeScroll(false);
                this.scrollTo(i10, this.getScrollY());
            }
        } //end label_49:

    }

    private void removeNonDecorViews()
    {

        int i0;
        i0 = 0;

        while (i0 < this.getChildCount())
        {
            if (((ViewPager$LayoutParams) this.getChildAt(i0).getLayoutParams()).isDecor == false)
            {
                this.removeViewAt(i0);
                i0 = i0 + -1;
            }

            i0 = i0 + 1;
        }
    }

    public void removeView(View  r1)
    {


        if (mInLayout == false)
        {
            this.removeView(r1);
        }
        else
        {
            this.removeViewInLayout(r1);
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean  z0)
    {

        ViewParent r1;
        r1 = this.getParent();

        if (r1 != null)
        {
            r1.requestDisallowInterceptTouchEvent(z0);
        }
    }

    private void scrollToItem(int  i0, boolean  z0, int  i1, boolean  z1)
    {

        ViewPager$ItemInfo r1;
        int i2;
        r1 = this.infoForPosition(i0);
        i2 = 0;

        if (r1 != null)
        {
            i2 = (int) ((float) this.getClientWidth() * Math.max(mFirstOffset, Math.min(r1.offset, mLastOffset)));
        }

        if (z0 == false)
        {
            if (z1 != false)
            {
                if (mOnPageChangeListener != null)
                {
                    mOnPageChangeListener.onPageSelected(i0);
                }
            }

            if (z1 != false)
            {
                if (mInternalPageChangeListener != null)
                {
                    mInternalPageChangeListener.onPageSelected(i0);
                }
            }

            this.completeScroll(false);
            this.scrollTo(i2, (int) 0);
            this.pageScrolled(i2);
        }
        else
        {
            this.smoothScrollTo(i2, (int) 0, i1);

            if (z1 != false)
            {
                if (mOnPageChangeListener != null)
                {
                    mOnPageChangeListener.onPageSelected(i0);
                }
            }

            if (z1 != false)
            {
                if (mInternalPageChangeListener != null)
                {
                    mInternalPageChangeListener.onPageSelected(i0);
                }
            }
        }
    }

    public void setAdapter(PagerAdapter  r1)
    {

        Object n0;
        int i0;
        PagerAdapter r6;
        boolean z2;
        ViewPager$ItemInfo r11;
        n0 = null;

        if (mAdapter != null)
        {
            mAdapter.unregisterDataSetObserver(mObserver);
            mAdapter.startUpdate(this);
            i0 = 0;

            while (i0 < mItems.size())
            {
                r11 = (ViewPager$ItemInfo) mItems.get(i0);
                mAdapter.destroyItem(this, r11.position, r11.object);
                i0 = i0 + 1;
            }

            mAdapter.finishUpdate(this);
            mItems.clear();
            this.removeNonDecorViews();
            mCurItem = (int) 0;
            this.scrollTo((int) 0, (int) 0);
        }

        r6 = mAdapter;
        mAdapter = r1;
        mExpectedAdapterCount = (int) 0;

        if (mAdapter != null)
        {
            if (mObserver == null)
            {
                mObserver = new ViewPager$PagerObserver(this, n0);
            }

            mAdapter.registerDataSetObserver(mObserver);
            mPopulatePending = false;
            z2 = mFirstLayout;
            mFirstLayout = true;
            mExpectedAdapterCount = mAdapter.getCount();

            if (mRestoredCurItem < 0)
            {
                if (z2 != false)
                {
                    this.requestLayout();
                }
                else
                {
                    this.populate();
                }
            }
            else
            {
                mAdapter.restoreState(mRestoredAdapterState, mRestoredClassLoader);
                this.setCurrentItemInternal(mRestoredCurItem, false, true);
                mRestoredCurItem = -1;
                mRestoredAdapterState = n0;
                mRestoredClassLoader = n0;
            }
        }

        if (mAdapterChangeListener != null)
        {
            if (r6 != r1)
            {
                mAdapterChangeListener.onAdapterChanged(r6, r1);
            }
        }
    }

    void setChildrenDrawingOrderEnabledCompat(boolean  z0)
    {

        Class r1, r4;
        String r5;
        Class[] r6;
        Method $r7, r9;
        Object[] r10;
        Boolean $r11;
        label_54:
        if (Build$VERSION.SDK_INT >= 7)
        {
            label_52:
            if (mSetChildrenDrawingOrderEnabled == null)
            {
                label_50:
                {
                    r4 = class "android/view/ViewGroup";

                    r5 = "setChildrenDrawingOrderEnabled";

                    r6 = new Class[1];

                    r1 = Boolean.TYPE;

                    r6[0] = r1;

                    try
                    {
                        $r7 = r4.getDeclaredMethod(r5, r6);
                    }
                    catch (NoSuchMethodException $r14)
                    {
                        break label_50;
                    }

                    mSetChildrenDrawingOrderEnabled = $r7;
                    break label_52;
                } //end label_50:


                Log.e("ViewPager", "Can\'t find setChildrenDrawingOrderEnabled", $r14);
            }

            label_53:
            {
                label_51:
                {
                    try
                    {
                        r9 = mSetChildrenDrawingOrderEnabled;
                    }
                    catch (Exception $r17)
                    {
                        break label_51;
                    }

                    try
                    {
                        r10 = new Object[1];
                    }
                    catch (Exception $r17)
                    {
                        break label_51;
                    }

                    try
                    {
                        $r11 = Boolean.valueOf(z0);
                    }
                    catch (Exception $r17)
                    {
                        break label_51;
                    }

                    try
                    {
                        r10[0] = $r11;
                        break label_53;
                    }
                    catch (Exception $r17)
                    {
                    }
                } //end label_51:


                Log.e("ViewPager", "Error changing children drawing order", $r17);
                break label_54;
            } //end label_53:


            r9.invoke(this, r10);
        }
    }

    public void setCurrentItem(int  i0)
    {

        boolean z2;
        mPopulatePending = false;

        if (mFirstLayout != false)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        this.setCurrentItemInternal(i0, z2, false);
    }

    public void setCurrentItem(int  i0, boolean  z0)
    {


        mPopulatePending = false;
        this.setCurrentItemInternal(i0, z0, false);
    }

    void setCurrentItemInternal(int  i0, boolean  z0, boolean  z1)
    {


        this.setCurrentItemInternal(i0, z0, z1, 0);
    }

    void setCurrentItemInternal(int  i0, boolean  z0, boolean  z1, int  i1)
    {

        boolean z2;
        int i2, i3;
        z2 = true;

        label_56:
        {
            if (mAdapter != null)
            {
                if (mAdapter.getCount() > 0)
                {
                    if (z1 == false)
                    {
                        if (mCurItem == i0)
                        {
                            if (mItems.size() != 0)
                            {
                                this.setScrollingCacheEnabled(false);
                                break label_56;
                            }
                        }
                    }

                    if (i0 >= 0)
                    {
                        if (i0 >= mAdapter.getCount())
                        {
                            i0 = mAdapter.getCount() + -1;
                        }
                    }
                    else
                    {
                        i0 = 0;
                    }

                    i2 = mOffscreenPageLimit;

                    label_55:
                    {
                        if (i0 <= mCurItem + i2)
                        {
                            if (i0 >= mCurItem - i2)
                            {
                                break label_55;
                            }
                        }

                        i3 = 0;

                        while (i3 < mItems.size())
                        {
                            ((ViewPager$ItemInfo) mItems.get(i3)).scrolling = true;
                            i3 = i3 + 1;
                        }
                    } //end label_55:


                    if (mCurItem == i0)
                    {
                        z2 = false;
                    }

                    if (mFirstLayout == false)
                    {
                        this.populate(i0);
                        this.scrollToItem(i0, z0, i1, z2);
                        break label_56;
                    }
                    else
                    {
                        mCurItem = i0;

                        if (z2 != false)
                        {
                            if (mOnPageChangeListener != null)
                            {
                                mOnPageChangeListener.onPageSelected(i0);
                            }
                        }

                        if (z2 != false)
                        {
                            if (mInternalPageChangeListener != null)
                            {
                                mInternalPageChangeListener.onPageSelected(i0);
                            }
                        }

                        this.requestLayout();
                        break label_56;
                    }
                }
            }

            this.setScrollingCacheEnabled(false);
        } //end label_56:

    }

    ViewPager$OnPageChangeListener setInternalPageChangeListener(ViewPager$OnPageChangeListener  r1)
    {

        ViewPager$OnPageChangeListener r2;
        r2 = mInternalPageChangeListener;
        mInternalPageChangeListener = r1;
        return r2;
    }

    public void setOffscreenPageLimit(int  i0)
    {


        if (i0 < 1)
        {
            Log.w("ViewPager", (new StringBuilder()).append("Requested offscreen page limit ").append(i0).append(" too small; defaulting to ").append(1).toString());
            i0 = 1;
        }

        if (i0 != mOffscreenPageLimit)
        {
            mOffscreenPageLimit = i0;
            this.populate();
        }
    }

    void setOnAdapterChangeListener(ViewPager$OnAdapterChangeListener  r1)
    {


        mAdapterChangeListener = r1;
    }

    public void setOnPageChangeListener(ViewPager$OnPageChangeListener  r1)
    {


        mOnPageChangeListener = r1;
    }

    public void setPageMargin(int  i0)
    {

        int i1, i2;
        i1 = mPageMargin;
        mPageMargin = i0;
        i2 = this.getWidth();
        this.recomputeScrollPosition(i2, i2, i0, i1);
        this.requestLayout();
    }

    public void setPageMarginDrawable(int  i0)
    {


        this.setPageMarginDrawable(this.getContext().getResources().getDrawable(i0));
    }

    public void setPageMarginDrawable(Drawable  r1)
    {

        boolean z0;
        mMarginDrawable = r1;

        if (r1 != null)
        {
            this.refreshDrawableState();
        }

        if (r1 != null)
        {
            z0 = false;
        }
        else
        {
            z0 = true;
        }

        this.setWillNotDraw(z0);
        this.invalidate();
    }

    public void setPageTransformer(boolean  z0, ViewPager$PageTransformer  r1)
    {

        boolean z1, z3, z4, z5;
        z1 = true;

        if (Build$VERSION.SDK_INT >= 11)
        {
            if (r1 == null)
            {
                z3 = false;
            }
            else
            {
                z3 = true;
            }

            if (mPageTransformer == null)
            {
                z5 = false;
            }
            else
            {
                z5 = true;
            }

            if (z3 == z5)
            {
                z4 = false;
            }
            else
            {
                z4 = true;
            }

            mPageTransformer = r1;
            this.setChildrenDrawingOrderEnabledCompat(z3);

            if (z3 == false)
            {
                mDrawingOrder = (int) 0;
            }
            else
            {
                if (z0 != false)
                {
                    z1 = (boolean) 2;
                }

                mDrawingOrder = z1 ? 1 : 0;
            }

            if (z4 != false)
            {
                this.populate();
            }
        }
    }

    private void setScrollState(int  i0)
    {

        boolean z0;
        if (mScrollState != i0)
        {
            mScrollState = i0;

            if (mPageTransformer != null)
            {
                if (i0 == 0)
                {
                    z0 = false;
                }
                else
                {
                    z0 = true;
                }

                this.enableLayers(z0);
            }

            if (mOnPageChangeListener != null)
            {
                mOnPageChangeListener.onPageScrollStateChanged(i0);
            }
        }
    }

    private void setScrollingCacheEnabled(boolean  z0)
    {


        if (mScrollingCacheEnabled != z0)
        {
            mScrollingCacheEnabled = z0;
        }
    }

    void smoothScrollTo(int  i0, int  i1)
    {


        this.smoothScrollTo(i0, i1, 0);
    }

    void smoothScrollTo(int  i0, int  i1, int  i2)
    {

        int i4, i5, i6, i7, i8, i9, i14, i16, i18;
        float f2, f3;
        label_57:
        if (this.getChildCount() != 0)
        {
            i4 = this.getScrollX();
            i5 = this.getScrollY();
            i6 = i0 - i4;
            i7 = i1 - i5;

            if (i6 == 0)
            {
                if (i7 == 0)
                {
                    this.completeScroll(false);
                    this.populate();
                    this.setScrollState(0);
                    break label_57;
                }
            }

            this.setScrollingCacheEnabled(true);
            this.setScrollState(2);
            i8 = this.getClientWidth();
            i9 = i8 / 2;
            f2 = (float) i9 + (float) i9 * this.distanceInfluenceForSnapDuration(Math.min(1.0F, 1.0F * (float) Math.abs(i6) / (float) i8));
            i14 = Math.abs(i2);

            if (i14 <= 0)
            {
                f3 = (float) i8 * mAdapter.getPageWidth(mCurItem);
                i16 = (int) ((1.0F + (float) Math.abs(i6) / ((float) mPageMargin + f3)) * 100.0F);
            }
            else
            {
                i16 = Math.round(1000.0F * Math.abs(f2 / (float) i14)) * 4;
            }

            i18 = Math.min(i16, 600);
            mScroller.startScroll(i4, i5, i6, i7, i18);
            ViewCompat.postInvalidateOnAnimation(this);
        }
        else
        {
            this.setScrollingCacheEnabled(false);
        }
    }

    private void sortChildDrawingOrder()
    {

        int i1, i2;
        View r1;
        if (mDrawingOrder != 0)
        {
            if (mDrawingOrderedChildren != null)
            {
                mDrawingOrderedChildren.clear();
            }
            else
            {
                mDrawingOrderedChildren = new ArrayList();
            }

            i1 = this.getChildCount();
            i2 = 0;

            while (i2 < i1)
            {
                r1 = this.getChildAt(i2);
                mDrawingOrderedChildren.add(r1);
                i2 = i2 + 1;
            }

            Collections.sort(mDrawingOrderedChildren, sPositionComparator);
        }
    }

    protected boolean verifyDrawable(Drawable  r1)
    {

        boolean z1;
        label_58:
        {
            if (this.verifyDrawable(r1) == false)
            {
                if (r1 != mMarginDrawable)
                {
                    z1 = false;
                    break label_58;
                }
            }

            z1 = true;
        } //end label_58:


        return z1;
    }
}
