package android.support.v4.widget;

import android.widget.ListView;
import android.view.View;

public class ListViewAutoScrollHelper extends AutoScrollHelper
{
    private final ListView mTarget;

    public ListViewAutoScrollHelper(ListView  r1)
    {
        super(r1);


        this.<init>(r1);
        mTarget = r1;
    }

    public boolean canTargetScrollHorizontally(int  i0)
    {


        return false;
    }

    public boolean canTargetScrollVertically(int  i0)
    {

        boolean z0;
        ListView r1;
        int i1, i2, i3;
        z0 = false;
        r1 = mTarget;
        i1 = r1.getCount();
        i2 = r1.getChildCount();
        i3 = r1.getFirstVisiblePosition();

        label_0:
        {
            if (i0 <= 0)
            {
                if (i0 >= 0)
                {
                    break label_0;
                }
                else
                {
                    if (i3 <= 0)
                    {
                        if (r1.getChildAt((int) 0).getTop() >= 0)
                        {
                            break label_0;
                        }
                    }
                }
            }
            else
            {
                if (i3 + i2 >= i1)
                {
                    if (r1.getChildAt(i2 + -1).getBottom() <= r1.getHeight())
                    {
                        break label_0;
                    }
                }
            }

            z0 = true;
        } //end label_0:


        return z0;
    }

    public void scrollTargetBy(int  i0, int  i1)
    {

        ListView r1;
        int i2;
        View r2;
        r1 = mTarget;
        i2 = r1.getFirstVisiblePosition();

        if (i2 != -1)
        {
            r2 = r1.getChildAt(0);

            if (r2 != null)
            {
                r1.setSelectionFromTop(i2, r2.getTop() - i1);
            }
        }
    }
}
