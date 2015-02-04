package android.support.v4.app;

import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;

class FragmentManagerImpl$5 implements android.view.animation.Animation$AnimationListener
{
    final FragmentManagerImpl this$0;
    final Fragment val$fragment;

    FragmentManagerImpl$5(FragmentManagerImpl  r1, Fragment  r2)
    {


        this$0 = r1;
        val$fragment = r2;
        this.<init>();
    }

    public void onAnimationEnd(Animation  r1)
    {


        if (val$fragment.mAnimatingAway != null)
        {
            val$fragment.mAnimatingAway = null;
            this$0.moveToState(val$fragment, val$fragment.mStateAfterAnimating, (int) 0, (int) 0, false);
        }
    }

    public void onAnimationRepeat(Animation  r1)
    {

    }

    public void onAnimationStart(Animation  r1)
    {

    }
}
