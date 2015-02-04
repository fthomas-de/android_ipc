package android.support.v4.app;

import android.os.Handler;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.FrameLayout$LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView$OnItemClickListener;

public class ListFragment extends Fragment
{
    static final int INTERNAL_EMPTY_ID = 16711681;
    static final int INTERNAL_LIST_CONTAINER_ID = 16711683;
    static final int INTERNAL_PROGRESS_CONTAINER_ID = 16711682;
    ListAdapter mAdapter;
    CharSequence mEmptyText;
    View mEmptyView;
    private final Handler mHandler;
    ListView mList;
    View mListContainer;
    boolean mListShown;
    private final AdapterView$OnItemClickListener mOnClickListener;
    View mProgressContainer;
    private final Runnable mRequestFocus;
    TextView mStandardEmptyView;

    public ListFragment()
    {


        this.<init>();
        mHandler = new Handler();
        mRequestFocus = new ListFragment$1(this);
        mOnClickListener = new ListFragment$2(this);
    }

    private void ensureList()
    {

        View r2, r5;
        ListAdapter r4;
        if (mList == null)
        {
            r2 = this.getView();

            if (r2 != null)
            {
                if (r2 instanceof ListView == false)
                {
                    mStandardEmptyView = (TextView) r2.findViewById(16711681);

                    if (mStandardEmptyView != null)
                    {
                        mStandardEmptyView.setVisibility(8);
                    }
                    else
                    {
                        mEmptyView = r2.findViewById(16908292);
                    }

                    mProgressContainer = r2.findViewById(16711682);
                    mListContainer = r2.findViewById(16711683);
                    r5 = r2.findViewById(16908298);

                    if (r5 instanceof ListView != false)
                    {
                        mList = (ListView) r5;

                        if (mEmptyView == null)
                        {
                            if (mEmptyText != null)
                            {
                                mStandardEmptyView.setText(mEmptyText);
                                mList.setEmptyView(mStandardEmptyView);
                            }
                        }
                        else
                        {
                            mList.setEmptyView(mEmptyView);
                        }
                    }
                    else
                    {
                        if (r5 != null)
                        {
                            throw new RuntimeException("Content has view with id attribute \'android.R.id.list\' that is not a ListView class");
                        }
                        else
                        {
                            throw new RuntimeException("Your content must have a ListView whose id attribute is \'android.R.id.list\'");
                        }
                    }
                }
                else
                {
                    mList = (ListView) r2;
                }

                mListShown = true;
                mList.setOnItemClickListener(mOnClickListener);

                if (mAdapter == null)
                {
                    if (mProgressContainer != null)
                    {
                        this.setListShown(false, false);
                    }
                }
                else
                {
                    r4 = mAdapter;
                    mAdapter = null;
                    this.setListAdapter(r4);
                }

                mHandler.post(mRequestFocus);
            }
            else
            {
                throw new IllegalStateException("Content view not yet created");
            }
        }
    }

    public ListAdapter getListAdapter()
    {


        return mAdapter;
    }

    public ListView getListView()
    {


        this.ensureList();
        return mList;
    }

    public long getSelectedItemId()
    {


        this.ensureList();
        return mList.getSelectedItemId();
    }

    public int getSelectedItemPosition()
    {


        this.ensureList();
        return mList.getSelectedItemPosition();
    }

    public View onCreateView(LayoutInflater  r1, ViewGroup  r2, Bundle  r3)
    {

        FragmentActivity r4;
        FrameLayout r5, r8;
        LinearLayout r6;
        TextView r9;
        ListView r10;
        r4 = this.getActivity();
        r5 = new FrameLayout(r4);
        r6 = new LinearLayout(r4);
        r6.setId(16711682);
        r6.setOrientation(1);
        r6.setVisibility(8);
        r6.setGravity(17);
        r6.addView(new ProgressBar(r4, null, 16842874), new FrameLayout$LayoutParams(-2, -2));
        r5.addView(r6, new FrameLayout$LayoutParams(-1, -1));
        r8 = new FrameLayout(r4);
        r8.setId(16711683);
        r9 = new TextView(this.getActivity());
        r9.setId(16711681);
        r9.setGravity(17);
        r8.addView(r9, new FrameLayout$LayoutParams(-1, -1));
        r10 = new ListView(this.getActivity());
        r10.setId(16908298);
        r10.setDrawSelectorOnTop(false);
        r8.addView(r10, new FrameLayout$LayoutParams(-1, -1));
        r5.addView(r8, new FrameLayout$LayoutParams(-1, -1));
        r5.setLayoutParams(new FrameLayout$LayoutParams(-1, -1));
        return r5;
    }

    public void onDestroyView()
    {

        Object n0;
        n0 = null;
        mHandler.removeCallbacks(mRequestFocus);
        mList = n0;
        mListShown = false;
        mListContainer = n0;
        mProgressContainer = n0;
        mEmptyView = n0;
        mStandardEmptyView = n0;
        this.onDestroyView();
    }

    public void onListItemClick(ListView  r1, View  r2, int  i0, long  l1)
    {

    }

    public void onViewCreated(View  r1, Bundle  r2)
    {


        this.onViewCreated(r1, r2);
        this.ensureList();
    }

    public void setEmptyText(CharSequence  r1)
    {


        this.ensureList();

        if (mStandardEmptyView != null)
        {
            mStandardEmptyView.setText(r1);

            if (mEmptyText == null)
            {
                mList.setEmptyView(mStandardEmptyView);
            }

            mEmptyText = r1;
            return;
        }
        else
        {
            throw new IllegalStateException("Can\'t be used with a custom content view");
        }
    }

    public void setListAdapter(ListAdapter  r1)
    {

        boolean z0, z2;
        z0 = false;

        if (mAdapter == null)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        mAdapter = r1;

        if (mList != null)
        {
            mList.setAdapter(r1);

            if (mListShown == false)
            {
                if (z2 == false)
                {
                    if (this.getView().getWindowToken() != null)
                    {
                        z0 = true;
                    }

                    this.setListShown(true, z0);
                }
            }
        }
    }

    public void setListShown(boolean  z0)
    {


        this.setListShown(z0, true);
    }

    private void setListShown(boolean  z0, boolean  z1)
    {


        this.ensureList();

        if (mProgressContainer != null)
        {
            if (mListShown != z0)
            {
                mListShown = z0;

                if (z0 == false)
                {
                    if (z1 == false)
                    {
                        mProgressContainer.clearAnimation();
                        mListContainer.clearAnimation();
                    }
                    else
                    {
                        mProgressContainer.startAnimation(AnimationUtils.loadAnimation(this.getActivity(), 17432576));
                        mListContainer.startAnimation(AnimationUtils.loadAnimation(this.getActivity(), 17432577));
                    }

                    mProgressContainer.setVisibility(0);
                    mListContainer.setVisibility(8);
                }
                else
                {
                    if (z1 == false)
                    {
                        mProgressContainer.clearAnimation();
                        mListContainer.clearAnimation();
                    }
                    else
                    {
                        mProgressContainer.startAnimation(AnimationUtils.loadAnimation(this.getActivity(), 17432577));
                        mListContainer.startAnimation(AnimationUtils.loadAnimation(this.getActivity(), 17432576));
                    }

                    mProgressContainer.setVisibility(8);
                    mListContainer.setVisibility(0);
                }
            }

            return;
        }
        else
        {
            throw new IllegalStateException("Can\'t be used with a custom content view");
        }
    }

    public void setListShownNoAnimation(boolean  z0)
    {


        this.setListShown(z0, false);
    }

    public void setSelection(int  i0)
    {


        this.ensureList();
        mList.setSelection(i0);
    }
}
