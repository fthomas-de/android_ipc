package android.support.v4.view.accessibility;

import android.os.Build$VERSION;
import android.os.Parcelable;
import java.util.List;
import android.view.View;

public class AccessibilityRecordCompat
{
    private static final AccessibilityRecordCompat$AccessibilityRecordImpl IMPL;
    private final Object mRecord;

    static
    {


        if (Build$VERSION.SDK_INT < 16)
        {
            if (Build$VERSION.SDK_INT < 15)
            {
                if (Build$VERSION.SDK_INT < 14)
                {
                    IMPL = new AccessibilityRecordCompat$AccessibilityRecordStubImpl();
                }
                else
                {
                    IMPL = new AccessibilityRecordCompat$AccessibilityRecordIcsImpl();
                }
            }
            else
            {
                IMPL = new AccessibilityRecordCompat$AccessibilityRecordIcsMr1Impl();
            }
        }
        else
        {
            IMPL = new AccessibilityRecordCompat$AccessibilityRecordJellyBeanImpl();
        }
    }

    public AccessibilityRecordCompat(Object  r1)
    {


        this.<init>();
        mRecord = r1;
    }

    public boolean equals(Object  r1)
    {

        boolean z0;
        AccessibilityRecordCompat r5;
        z0 = true;

        if (this != r1)
        {
            if (r1 != null)
            {
                if (this.getClass() == r1.getClass())
                {
                    r5 = (AccessibilityRecordCompat) r1;

                    if (mRecord != null)
                    {
                        if (mRecord.equals(r5.mRecord) == false)
                        {
                            z0 = false;
                        }
                    }
                    else
                    {
                        if (r5.mRecord != null)
                        {
                            z0 = false;
                        }
                    }
                }
                else
                {
                    z0 = false;
                }
            }
            else
            {
                z0 = false;
            }
        }

        return z0;
    }

    public int getAddedCount()
    {


        return IMPL.getAddedCount(mRecord);
    }

    public CharSequence getBeforeText()
    {


        return IMPL.getBeforeText(mRecord);
    }

    public CharSequence getClassName()
    {


        return IMPL.getClassName(mRecord);
    }

    public CharSequence getContentDescription()
    {


        return IMPL.getContentDescription(mRecord);
    }

    public int getCurrentItemIndex()
    {


        return IMPL.getCurrentItemIndex(mRecord);
    }

    public int getFromIndex()
    {


        return IMPL.getFromIndex(mRecord);
    }

    public Object getImpl()
    {


        return mRecord;
    }

    public int getItemCount()
    {


        return IMPL.getItemCount(mRecord);
    }

    public int getMaxScrollX()
    {


        return IMPL.getMaxScrollX(mRecord);
    }

    public int getMaxScrollY()
    {


        return IMPL.getMaxScrollY(mRecord);
    }

    public Parcelable getParcelableData()
    {


        return IMPL.getParcelableData(mRecord);
    }

    public int getRemovedCount()
    {


        return IMPL.getRemovedCount(mRecord);
    }

    public int getScrollX()
    {


        return IMPL.getScrollX(mRecord);
    }

    public int getScrollY()
    {


        return IMPL.getScrollY(mRecord);
    }

    public AccessibilityNodeInfoCompat getSource()
    {


        return IMPL.getSource(mRecord);
    }

    public List getText()
    {


        return IMPL.getText(mRecord);
    }

    public int getToIndex()
    {


        return IMPL.getToIndex(mRecord);
    }

    public int getWindowId()
    {


        return IMPL.getWindowId(mRecord);
    }

    public int hashCode()
    {

        int i0;
        if (mRecord != null)
        {
            i0 = mRecord.hashCode();
        }
        else
        {
            i0 = 0;
        }

        return i0;
    }

    public boolean isChecked()
    {


        return IMPL.isChecked(mRecord);
    }

    public boolean isEnabled()
    {


        return IMPL.isEnabled(mRecord);
    }

    public boolean isFullScreen()
    {


        return IMPL.isFullScreen(mRecord);
    }

    public boolean isPassword()
    {


        return IMPL.isPassword(mRecord);
    }

    public boolean isScrollable()
    {


        return IMPL.isScrollable(mRecord);
    }

    public static AccessibilityRecordCompat obtain()
    {


        return new AccessibilityRecordCompat(IMPL.obtain());
    }

    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat  r0)
    {


        return new AccessibilityRecordCompat(IMPL.obtain(r0.mRecord));
    }

    public void recycle()
    {


        IMPL.recycle(mRecord);
    }

    public void setAddedCount(int  i0)
    {


        IMPL.setAddedCount(mRecord, i0);
    }

    public void setBeforeText(CharSequence  r1)
    {


        IMPL.setBeforeText(mRecord, r1);
    }

    public void setChecked(boolean  z0)
    {


        IMPL.setChecked(mRecord, z0);
    }

    public void setClassName(CharSequence  r1)
    {


        IMPL.setClassName(mRecord, r1);
    }

    public void setContentDescription(CharSequence  r1)
    {


        IMPL.setContentDescription(mRecord, r1);
    }

    public void setCurrentItemIndex(int  i0)
    {


        IMPL.setCurrentItemIndex(mRecord, i0);
    }

    public void setEnabled(boolean  z0)
    {


        IMPL.setEnabled(mRecord, z0);
    }

    public void setFromIndex(int  i0)
    {


        IMPL.setFromIndex(mRecord, i0);
    }

    public void setFullScreen(boolean  z0)
    {


        IMPL.setFullScreen(mRecord, z0);
    }

    public void setItemCount(int  i0)
    {


        IMPL.setItemCount(mRecord, i0);
    }

    public void setMaxScrollX(int  i0)
    {


        IMPL.setMaxScrollX(mRecord, i0);
    }

    public void setMaxScrollY(int  i0)
    {


        IMPL.setMaxScrollY(mRecord, i0);
    }

    public void setParcelableData(Parcelable  r1)
    {


        IMPL.setParcelableData(mRecord, r1);
    }

    public void setPassword(boolean  z0)
    {


        IMPL.setPassword(mRecord, z0);
    }

    public void setRemovedCount(int  i0)
    {


        IMPL.setRemovedCount(mRecord, i0);
    }

    public void setScrollX(int  i0)
    {


        IMPL.setScrollX(mRecord, i0);
    }

    public void setScrollY(int  i0)
    {


        IMPL.setScrollY(mRecord, i0);
    }

    public void setScrollable(boolean  z0)
    {


        IMPL.setScrollable(mRecord, z0);
    }

    public void setSource(View  r1)
    {


        IMPL.setSource(mRecord, r1);
    }

    public void setSource(View  r1, int  i0)
    {


        IMPL.setSource(mRecord, r1, i0);
    }

    public void setToIndex(int  i0)
    {


        IMPL.setToIndex(mRecord, i0);
    }
}
