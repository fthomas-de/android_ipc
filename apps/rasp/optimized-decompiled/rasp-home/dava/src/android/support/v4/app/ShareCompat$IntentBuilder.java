package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import java.util.ArrayList;
import android.net.Uri;
import android.text.Html;

public class ShareCompat$IntentBuilder
{
    private Activity mActivity;
    private ArrayList mBccAddresses;
    private ArrayList mCcAddresses;
    private CharSequence mChooserTitle;
    private Intent mIntent;
    private ArrayList mStreams;
    private ArrayList mToAddresses;

    private ShareCompat$IntentBuilder(Activity  r1)
    {


        this.<init>();
        mActivity = r1;
        mIntent = (new Intent()).setAction("android.intent.action.SEND");
        mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", r1.getPackageName());
        mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", r1.getComponentName());
        mIntent.addFlags(524288);
    }

    public ShareCompat$IntentBuilder addEmailBcc(String  r1)
    {


        if (mBccAddresses == null)
        {
            mBccAddresses = new ArrayList();
        }

        mBccAddresses.add(r1);
        return this;
    }

    public ShareCompat$IntentBuilder addEmailBcc(String[]  r1)
    {


        this.combineArrayExtra("android.intent.extra.BCC", r1);
        return this;
    }

    public ShareCompat$IntentBuilder addEmailCc(String  r1)
    {


        if (mCcAddresses == null)
        {
            mCcAddresses = new ArrayList();
        }

        mCcAddresses.add(r1);
        return this;
    }

    public ShareCompat$IntentBuilder addEmailCc(String[]  r1)
    {


        this.combineArrayExtra("android.intent.extra.CC", r1);
        return this;
    }

    public ShareCompat$IntentBuilder addEmailTo(String  r1)
    {


        if (mToAddresses == null)
        {
            mToAddresses = new ArrayList();
        }

        mToAddresses.add(r1);
        return this;
    }

    public ShareCompat$IntentBuilder addEmailTo(String[]  r1)
    {


        this.combineArrayExtra("android.intent.extra.EMAIL", r1);
        return this;
    }

    public ShareCompat$IntentBuilder addStream(Uri  r1)
    {

        Uri r5;
        r5 = (Uri) mIntent.getParcelableExtra("android.intent.extra.STREAM");

        if (r5 != null)
        {
            if (mStreams == null)
            {
                mStreams = new ArrayList();
            }

            if (r5 != null)
            {
                mIntent.removeExtra("android.intent.extra.STREAM");
                mStreams.add(r5);
            }

            mStreams.add(r1);
        }
        else
        {
            this = this.setStream(r1);
        }

        return this;
    }

    private void combineArrayExtra(String  r1, ArrayList  r2)
    {

        String[] r4, r5;
        int i1;
        r4 = mIntent.getStringArrayExtra(r1);

        if (r4 == null)
        {
            i1 = 0;
        }
        else
        {
            i1 = r4.length;
        }

        r5 = new String[r2.size() + i1];
        r2.toArray(r5);

        if (r4 != null)
        {
            System.arraycopy(r4, 0, r5, r2.size(), i1);
        }

        mIntent.putExtra(r1, r5);
    }

    private void combineArrayExtra(String  r1, String[]  r2)
    {

        Intent r3;
        String[] r4, r5;
        int i1;
        r3 = this.getIntent();
        r4 = r3.getStringArrayExtra(r1);

        if (r4 == null)
        {
            i1 = 0;
        }
        else
        {
            i1 = r4.length;
        }

        r5 = new String[r2.length + i1];

        if (r4 != null)
        {
            System.arraycopy(r4, 0, r5, 0, i1);
        }

        System.arraycopy(r2, 0, r5, i1, r2.length);
        r3.putExtra(r1, r5);
    }

    public Intent createChooserIntent()
    {


        return Intent.createChooser(this.getIntent(), mChooserTitle);
    }

    public static ShareCompat$IntentBuilder 'from'(Activity  r0)
    {


        return new ShareCompat$IntentBuilder(r0);
    }

    Activity getActivity()
    {


        return mActivity;
    }

    public Intent getIntent()
    {

        byte b0;
        Object n0;
        boolean z0;
        b0 = (byte) (byte) 1;
        n0 = null;

        if (mToAddresses != null)
        {
            this.combineArrayExtra("android.intent.extra.EMAIL", mToAddresses);
            mToAddresses = n0;
        }

        if (mCcAddresses != null)
        {
            this.combineArrayExtra("android.intent.extra.CC", mCcAddresses);
            mCcAddresses = n0;
        }

        if (mBccAddresses != null)
        {
            this.combineArrayExtra("android.intent.extra.BCC", mBccAddresses);
            mBccAddresses = n0;
        }

        label_1:
        {
            if (mStreams != null)
            {
                if (mStreams.size() > 1)
                {
                    break label_1;
                }
            }

            b0 = (byte) (byte) 0;
        } //end label_1:


        z0 = mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");

        if (b0 == (byte) 0)
        {
            if (z0 != false)
            {
                mIntent.setAction("android.intent.action.SEND");

                label_0:
                {
                    if (mStreams != null)
                    {
                        if (mStreams.isEmpty() == false)
                        {
                            mIntent.putExtra("android.intent.extra.STREAM", (Parcelable) mStreams.get(0));
                            break label_0;
                        }
                    }

                    mIntent.removeExtra("android.intent.extra.STREAM");
                } //end label_0:


                mStreams = n0;
            }
        }

        label_2:
        if (b0 != (byte) 0)
        {
            if (z0 == false)
            {
                mIntent.setAction("android.intent.action.SEND_MULTIPLE");

                if (mStreams != null)
                {
                    if (mStreams.isEmpty() == false)
                    {
                        mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", mStreams);
                        break label_2;
                    }
                }

                mIntent.removeExtra("android.intent.extra.STREAM");
            }
        }

        return mIntent;
    }

    public ShareCompat$IntentBuilder setChooserTitle(int  i0)
    {


        return this.setChooserTitle(mActivity.getText(i0));
    }

    public ShareCompat$IntentBuilder setChooserTitle(CharSequence  r1)
    {


        mChooserTitle = r1;
        return this;
    }

    public ShareCompat$IntentBuilder setEmailBcc(String[]  r1)
    {


        mIntent.putExtra("android.intent.extra.BCC", r1);
        return this;
    }

    public ShareCompat$IntentBuilder setEmailCc(String[]  r1)
    {


        mIntent.putExtra("android.intent.extra.CC", r1);
        return this;
    }

    public ShareCompat$IntentBuilder setEmailTo(String[]  r1)
    {


        if (mToAddresses != null)
        {
            mToAddresses = null;
        }

        mIntent.putExtra("android.intent.extra.EMAIL", r1);
        return this;
    }

    public ShareCompat$IntentBuilder setHtmlText(String  r1)
    {


        mIntent.putExtra("android.intent.extra.HTML_TEXT", r1);

        if (mIntent.hasExtra("android.intent.extra.TEXT") == false)
        {
            this.setText(Html.fromHtml(r1));
        }

        return this;
    }

    public ShareCompat$IntentBuilder setStream(Uri  r1)
    {


        if (mIntent.getAction().equals("android.intent.action.SEND") == false)
        {
            mIntent.setAction("android.intent.action.SEND");
        }

        mStreams = null;
        mIntent.putExtra("android.intent.extra.STREAM", r1);
        return this;
    }

    public ShareCompat$IntentBuilder setSubject(String  r1)
    {


        mIntent.putExtra("android.intent.extra.SUBJECT", r1);
        return this;
    }

    public ShareCompat$IntentBuilder setText(CharSequence  r1)
    {


        mIntent.putExtra("android.intent.extra.TEXT", r1);
        return this;
    }

    public ShareCompat$IntentBuilder setType(String  r1)
    {


        mIntent.setType(r1);
        return this;
    }

    public void startChooser()
    {


        mActivity.startActivity(this.createChooserIntent());
    }
}
