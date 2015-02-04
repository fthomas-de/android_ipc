package android.support.v4.widget;

import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.os.Bundle;

class ExploreByTouchHelper$ExploreByTouchNodeProvider extends AccessibilityNodeProviderCompat
{
    final ExploreByTouchHelper this$0;

    private ExploreByTouchHelper$ExploreByTouchNodeProvider(ExploreByTouchHelper  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    ExploreByTouchHelper$ExploreByTouchNodeProvider(ExploreByTouchHelper  r1, ExploreByTouchHelper$1  r2)
    {
        this(r1);


        this.<init>(r1);
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int  i0)
    {


        return ExploreByTouchHelper.access$100(this$0, i0);
    }

    public boolean performAction(int  i0, int  i1, Bundle  r1)
    {


        return ExploreByTouchHelper.access$200(this$0, i0, i1, r1);
    }
}
