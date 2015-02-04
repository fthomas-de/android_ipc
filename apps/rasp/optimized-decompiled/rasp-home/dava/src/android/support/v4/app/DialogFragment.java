package android.support.v4.app;

import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnDismissListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.Window;
import android.view.View;
import android.app.Activity;
import android.content.DialogInterface;

public class DialogFragment extends Fragment implements android.content.DialogInterface$OnCancelListener, android.content.DialogInterface$OnDismissListener
{
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    int mBackStackId;
    boolean mCancelable;
    Dialog mDialog;
    boolean mDismissed;
    boolean mShownByMe;
    boolean mShowsDialog;
    int mStyle;
    int mTheme;
    boolean mViewDestroyed;

    public DialogFragment()
    {


        this.<init>();
        mStyle = 0;
        mTheme = 0;
        mCancelable = true;
        mShowsDialog = true;
        mBackStackId = -1;
    }

    public void dismiss()
    {


        this.dismissInternal(false);
    }

    public void dismissAllowingStateLoss()
    {


        this.dismissInternal(true);
    }

    void dismissInternal(boolean  z0)
    {

        FragmentTransaction r1;
        if (mDismissed == false)
        {
            mDismissed = true;
            mShownByMe = false;

            if (mDialog != null)
            {
                mDialog.dismiss();
                mDialog = null;
            }

            mViewDestroyed = true;

            if (mBackStackId < 0)
            {
                r1 = this.getFragmentManager().beginTransaction();
                r1.remove(this);

                if (z0 == false)
                {
                    r1.commit();
                }
                else
                {
                    r1.commitAllowingStateLoss();
                }
            }
            else
            {
                this.getFragmentManager().popBackStack(mBackStackId, (int) 1);
                mBackStackId = -1;
            }
        }
    }

    public Dialog getDialog()
    {


        return mDialog;
    }

    public LayoutInflater getLayoutInflater(Bundle  r1)
    {

        LayoutInflater r3;
        label_0:
        if (mShowsDialog != false)
        {
            mDialog = this.onCreateDialog(r1);

            switch (mStyle)
            {
                case 3:
                    mDialog.getWindow().addFlags(24);

                case 1:
                case 2:
                    mDialog.requestWindowFeature(1);

                default:
                    if (mDialog == null)
                    {
                        r3 = (LayoutInflater) mActivity.getSystemService("layout_inflater");
                        break label_0;
                    }
                    else
                    {
                        r3 = (LayoutInflater) mDialog.getContext().getSystemService("layout_inflater");
                        break label_0;
                    }
            }
        }
        else
        {
            r3 = this.getLayoutInflater(r1);
        }

        return r3;
    }

    public boolean getShowsDialog()
    {


        return mShowsDialog;
    }

    public int getTheme()
    {


        return mTheme;
    }

    public boolean isCancelable()
    {


        return mCancelable;
    }

    public void onActivityCreated(Bundle  r1)
    {

        View r2;
        Bundle r4;
        this.onActivityCreated(r1);

        if (mShowsDialog != false)
        {
            r2 = this.getView();

            if (r2 != null)
            {
                if (r2.getParent() == null)
                {
                    mDialog.setContentView(r2);
                }
                else
                {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
            }

            mDialog.setOwnerActivity(this.getActivity());
            mDialog.setCancelable(mCancelable);
            mDialog.setOnCancelListener(this);
            mDialog.setOnDismissListener(this);

            if (r1 != null)
            {
                r4 = r1.getBundle("android:savedDialogState");

                if (r4 != null)
                {
                    mDialog.onRestoreInstanceState(r4);
                }
            }
        }
    }

    public void onAttach(Activity  r1)
    {


        this.onAttach(r1);

        if (mShownByMe == false)
        {
            mDismissed = false;
        }
    }

    public void onCancel(DialogInterface  r1)
    {

    }

    public void onCreate(Bundle  r1)
    {

        boolean z2;
        this.onCreate(r1);

        if (mContainerId != 0)
        {
            z2 = false;
        }
        else
        {
            z2 = true;
        }

        mShowsDialog = z2;

        if (r1 != null)
        {
            mStyle = r1.getInt("android:style", (int) 0);
            mTheme = r1.getInt("android:theme", (int) 0);
            mCancelable = r1.getBoolean("android:cancelable", true);
            mShowsDialog = r1.getBoolean("android:showsDialog", mShowsDialog);
            mBackStackId = r1.getInt("android:backStackId", -1);
        }
    }

    public Dialog onCreateDialog(Bundle  r1)
    {


        return new Dialog(this.getActivity(), this.getTheme());
    }

    public void onDestroyView()
    {


        this.onDestroyView();

        if (mDialog != null)
        {
            mViewDestroyed = true;
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void onDetach()
    {


        this.onDetach();

        if (mShownByMe == false)
        {
            if (mDismissed == false)
            {
                mDismissed = true;
            }
        }
    }

    public void onDismiss(DialogInterface  r1)
    {


        if (mViewDestroyed == false)
        {
            this.dismissInternal(true);
        }
    }

    public void onSaveInstanceState(Bundle  r1)
    {

        Bundle r3;
        this.onSaveInstanceState(r1);

        if (mDialog != null)
        {
            r3 = mDialog.onSaveInstanceState();

            if (r3 != null)
            {
                r1.putBundle("android:savedDialogState", r3);
            }
        }

        if (mStyle != 0)
        {
            r1.putInt("android:style", mStyle);
        }

        if (mTheme != 0)
        {
            r1.putInt("android:theme", mTheme);
        }

        if (mCancelable == false)
        {
            r1.putBoolean("android:cancelable", mCancelable);
        }

        if (mShowsDialog == false)
        {
            r1.putBoolean("android:showsDialog", mShowsDialog);
        }

        if (mBackStackId != -1)
        {
            r1.putInt("android:backStackId", mBackStackId);
        }
    }

    public void onStart()
    {


        this.onStart();

        if (mDialog != null)
        {
            mViewDestroyed = false;
            mDialog.show();
        }
    }

    public void onStop()
    {


        this.onStop();

        if (mDialog != null)
        {
            mDialog.hide();
        }
    }

    public void setCancelable(boolean  z0)
    {


        mCancelable = z0;

        if (mDialog != null)
        {
            mDialog.setCancelable(z0);
        }
    }

    public void setShowsDialog(boolean  z0)
    {


        mShowsDialog = z0;
    }

    public void setStyle(int  i0, int  i1)
    {


        mStyle = i0;

        label_1:
        {
            if (mStyle != 2)
            {
                if (mStyle != 3)
                {
                    break label_1;
                }
            }

            mTheme = 16973913;
        } //end label_1:


        if (i1 != 0)
        {
            mTheme = i1;
        }
    }

    public int show(FragmentTransaction  r1, String  r2)
    {


        mDismissed = false;
        mShownByMe = true;
        r1.add(this, r2);
        mViewDestroyed = false;
        mBackStackId = r1.commit();
        return mBackStackId;
    }

    public void show(FragmentManager  r1, String  r2)
    {

        FragmentTransaction r3;
        mDismissed = false;
        mShownByMe = true;
        r3 = r1.beginTransaction();
        r3.add(this, r2);
        r3.commit();
    }
}
