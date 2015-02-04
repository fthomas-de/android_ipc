package android.support.v4.app;

import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.view.View;

class ListFragment$2 implements android.widget.AdapterView$OnItemClickListener
{
    final ListFragment this$0;

    ListFragment$2(ListFragment  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void onItemClick(AdapterView  r1, View  r2, int  i0, long  l1)
    {


        this$0.onListItemClick((ListView) r1, r2, i0, l1);
    }
}
