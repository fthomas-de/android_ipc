package android.support.v4.app;

import android.widget.TabHost$TabContentFactory;
import android.content.Context;
import android.view.View;

class FragmentTabHost$DummyTabFactory implements android.widget.TabHost$TabContentFactory
{
    private final Context mContext;

    public FragmentTabHost$DummyTabFactory(Context  r1)
    {


        this.<init>();
        mContext = r1;
    }

    public View createTabContent(String  r1)
    {

        View r2;
        r2 = new View(mContext);
        r2.setMinimumWidth(0);
        r2.setMinimumHeight(0);
        return r2;
    }
}
