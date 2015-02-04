package android.support.v4.view;

import android.content.Context;
import android.view.View;
import android.view.MenuItem;
import android.view.SubMenu;
import android.util.Log;

public abstract class ActionProvider
{
    private static final String TAG = "ActionProvider(support)";
    private final Context mContext;
    private ActionProvider$SubUiVisibilityListener mSubUiVisibilityListener;
    private ActionProvider$VisibilityListener mVisibilityListener;

    public ActionProvider(Context  r1)
    {


        this.<init>();
        mContext = r1;
    }

    public Context getContext()
    {


        return mContext;
    }

    public boolean hasSubMenu()
    {


        return false;
    }

    public boolean isVisible()
    {


        return true;
    }

    public abstract android.view.View onCreateActionView();

    public View onCreateActionView(MenuItem  r1)
    {


        return this.onCreateActionView();
    }

    public boolean onPerformDefaultAction()
    {


        return false;
    }

    public void onPrepareSubMenu(SubMenu  r1)
    {

    }

    public boolean overridesItemVisibility()
    {


        return false;
    }

    public void refreshVisibility()
    {


        if (mVisibilityListener != null)
        {
            if (this.overridesItemVisibility() != false)
            {
                mVisibilityListener.onActionProviderVisibilityChanged(this.isVisible());
            }
        }
    }

    public void setSubUiVisibilityListener(ActionProvider$SubUiVisibilityListener  r1)
    {


        mSubUiVisibilityListener = r1;
    }

    public void setVisibilityListener(ActionProvider$VisibilityListener  r1)
    {


        if (mVisibilityListener != null)
        {
            if (r1 != null)
            {
                Log.w("ActionProvider(support)", (new StringBuilder()).append("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ").append(this.getClass().getSimpleName()).append(" instance while it is still in use somewhere else?").toString());
            }
        }

        mVisibilityListener = r1;
    }

    public void subUiVisibilityChanged(boolean  z0)
    {


        if (mSubUiVisibilityListener != null)
        {
            mSubUiVisibilityListener.onSubUiVisibilityChanged(z0);
        }
    }
}
