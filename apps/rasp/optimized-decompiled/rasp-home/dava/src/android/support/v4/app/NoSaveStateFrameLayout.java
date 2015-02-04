package android.support.v4.app;

import android.widget.FrameLayout;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;

class NoSaveStateFrameLayout extends FrameLayout
{

    public NoSaveStateFrameLayout(Context  r1)
    {
        super(r1);


        this.<init>(r1);
    }

    protected void dispatchRestoreInstanceState(SparseArray  r1)
    {


        this.dispatchThawSelfOnly(r1);
    }

    protected void dispatchSaveInstanceState(SparseArray  r1)
    {


        this.dispatchFreezeSelfOnly(r1);
    }

    static ViewGroup wrap(View  r0)
    {

        NoSaveStateFrameLayout r1;
        ViewGroup$LayoutParams r3;
        r1 = new NoSaveStateFrameLayout(r0.getContext());
        r3 = r0.getLayoutParams();

        if (r3 != null)
        {
            r1.setLayoutParams(r3);
        }

        r0.setLayoutParams(new FrameLayout$LayoutParams(-1, -1));
        r1.addView(r0);
        return r1;
    }
}
