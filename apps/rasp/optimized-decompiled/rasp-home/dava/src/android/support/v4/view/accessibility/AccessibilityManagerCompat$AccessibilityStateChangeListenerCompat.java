package android.support.v4.view.accessibility;


public abstract class AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat
{
    final Object mListener;

    public AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat()
    {


        this.<init>();
        mListener = AccessibilityManagerCompat.access$000().newAccessiblityStateChangeListener(this);
    }

    public abstract void onAccessibilityStateChanged(boolean  z0);
}
