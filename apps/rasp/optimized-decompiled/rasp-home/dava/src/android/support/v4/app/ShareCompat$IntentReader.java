package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.pm.ApplicationInfo;
import android.content.Intent;
import android.text.Html;
import android.net.Uri;
import java.util.ArrayList;

public class ShareCompat$IntentReader
{
    private static final String TAG = "IntentReader";
    private Activity mActivity;
    private ComponentName mCallingActivity;
    private String mCallingPackage;
    private Intent mIntent;
    private ArrayList mStreams;

    private ShareCompat$IntentReader(Activity  r1)
    {


        this.<init>();
        mActivity = r1;
        mIntent = r1.getIntent();
        mCallingPackage = ShareCompat.getCallingPackage(r1);
        mCallingActivity = ShareCompat.getCallingActivity(r1);
    }

    public static ShareCompat$IntentReader 'from'(Activity  r0)
    {


        return new ShareCompat$IntentReader(r0);
    }

    public ComponentName getCallingActivity()
    {


        return mCallingActivity;
    }

    public Drawable getCallingActivityIcon()
    {

        Drawable r1;
        PackageManager r3;
        ComponentName r7;
        r1 = null;

        label_0:
        if (mCallingActivity != null)
        {
            r3 = mActivity.getPackageManager();

            r7 = mCallingActivity;

            r1 = r3.getActivityIcon(r7);
        }

        return r1;
    }

    public Drawable getCallingApplicationIcon()
    {

        Drawable r1;
        PackageManager r3;
        String r7;
        r1 = null;

        label_1:
        if (mCallingPackage != null)
        {
            r3 = mActivity.getPackageManager();

            r7 = mCallingPackage;

            r1 = r3.getApplicationIcon(r7);
        }

        return r1;
    }

    public CharSequence getCallingApplicationLabel()
    {

        CharSequence r1, $r9;
        PackageManager r3;
        String r6;
        ApplicationInfo $r7;
        r1 = null;

        label_4:
        if (mCallingPackage != null)
        {
            r3 = mActivity.getPackageManager();

            label_3:
            {
                label_2:
                {
                    r6 = mCallingPackage;

                    try
                    {
                        $r7 = r3.getApplicationInfo(r6, 0);
                    }
                    catch (PackageManager$NameNotFoundException $r10)
                    {
                        break label_2;
                    }

                    $r9 = r3.getApplicationLabel($r7);
                    break label_3;
                } //end label_2:


                Log.e("IntentReader", "Could not retrieve label for calling application", $r10);
                break label_4;
            } //end label_3:


            r1 = $r9;
        }

        return r1;
    }

    public String getCallingPackage()
    {


        return mCallingPackage;
    }

    public String[] getEmailBcc()
    {


        return mIntent.getStringArrayExtra("android.intent.extra.BCC");
    }

    public String[] getEmailCc()
    {


        return mIntent.getStringArrayExtra("android.intent.extra.CC");
    }

    public String[] getEmailTo()
    {


        return mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
    }

    public String getHtmlText()
    {

        String r3;
        CharSequence r4;
        r3 = mIntent.getStringExtra("android.intent.extra.HTML_TEXT");

        if (r3 == null)
        {
            r4 = this.getText();

            if (r4 instanceof Spanned == false)
            {
                if (r4 != null)
                {
                    r3 = ShareCompat.access$000().escapeHtml(r4);
                }
            }
            else
            {
                r3 = Html.toHtml((Spanned) r4);
            }
        }

        return r3;
    }

    public Uri getStream()
    {


        return (Uri) mIntent.getParcelableExtra("android.intent.extra.STREAM");
    }

    public Uri getStream(int  i0)
    {

        Uri r9;
        if (mStreams == null)
        {
            if (this.isMultipleShare() != false)
            {
                mStreams = mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
        }

        if (mStreams == null)
        {
            if (i0 != 0)
            {
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Stream items available: ").append(this.getStreamCount()).append(" index requested: ").append(i0).toString());
            }
            else
            {
                r9 = (Uri) mIntent.getParcelableExtra("android.intent.extra.STREAM");
            }
        }
        else
        {
            r9 = (Uri) mStreams.get(i0);
        }

        return r9;
    }

    public int getStreamCount()
    {

        int i0;
        if (mStreams == null)
        {
            if (this.isMultipleShare() != false)
            {
                mStreams = mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
        }

        if (mStreams == null)
        {
            if (mIntent.hasExtra("android.intent.extra.STREAM") == false)
            {
                i0 = 0;
            }
            else
            {
                i0 = 1;
            }
        }
        else
        {
            i0 = mStreams.size();
        }

        return i0;
    }

    public String getSubject()
    {


        return mIntent.getStringExtra("android.intent.extra.SUBJECT");
    }

    public CharSequence getText()
    {


        return mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
    }

    public String getType()
    {


        return mIntent.getType();
    }

    public boolean isMultipleShare()
    {


        return "android.intent.action.SEND_MULTIPLE".equals(mIntent.getAction());
    }

    public boolean isShareIntent()
    {

        String r2;
        boolean z2;
        r2 = mIntent.getAction();

        label_5:
        {
            if ("android.intent.action.SEND".equals(r2) == false)
            {
                if ("android.intent.action.SEND_MULTIPLE".equals(r2) == false)
                {
                    z2 = false;
                    break label_5;
                }
            }

            z2 = true;
        } //end label_5:


        return z2;
    }

    public boolean isSingleShare()
    {


        return "android.intent.action.SEND".equals(mIntent.getAction());
    }
}
