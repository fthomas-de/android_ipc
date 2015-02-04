package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.TextView;

public class SimpleCursorAdapter extends ResourceCursorAdapter
{
    private SimpleCursorAdapter$CursorToStringConverter mCursorToStringConverter;
    protected int[] mFrom;
    String[] mOriginalFrom;
    private int mStringConversionColumn;
    protected int[] mTo;
    private SimpleCursorAdapter$ViewBinder mViewBinder;

    public SimpleCursorAdapter(Context  r1, int  i0, Cursor  r2, String[]  r3, int[]  r4)
    {
        super(r1, i0, r2);


        this.<init>(r1, i0, r2);
        mStringConversionColumn = -1;
        mTo = r4;
        mOriginalFrom = r3;
        this.findColumns(r3);
    }

    public SimpleCursorAdapter(Context  r1, int  i0, Cursor  r2, String[]  r3, int[]  r4, int  i1)
    {
        super(r1, i0, r2, i1);


        this.<init>(r1, i0, r2, i1);
        mStringConversionColumn = -1;
        mTo = r4;
        mOriginalFrom = r3;
        this.findColumns(r3);
    }

    public void bindView(View  r1, Context  r2, Cursor  r3)
    {

        SimpleCursorAdapter$ViewBinder r4;
        int i0, i1;
        int[] r6, r7;
        View r8;
        boolean z0;
        String r9;
        r4 = mViewBinder;
        i0 = mTo.length;
        r6 = mFrom;
        r7 = mTo;
        i1 = 0;

        while (i1 < i0)
        {
            r8 = r1.findViewById(r7[i1]);

            if (r8 != null)
            {
                z0 = false;

                if (r4 != null)
                {
                    z0 = r4.setViewValue(r8, r3, r6[i1]);
                }

                if (z0 == false)
                {
                    r9 = r3.getString(r6[i1]);

                    if (r9 == null)
                    {
                        r9 = "";
                    }

                    if (r8 instanceof TextView == false)
                    {
                        if (r8 instanceof ImageView == false)
                        {
                            throw new IllegalStateException((new StringBuilder()).append(r8.getClass().getName()).append(" is not a ").append(" view that can be bounds by this SimpleCursorAdapter").toString());
                        }
                        else
                        {
                            this.setViewImage((ImageView) r8, r9);
                        }
                    }
                    else
                    {
                        this.setViewText((TextView) r8, r9);
                    }
                }
            }

            i1 = i1 + 1;
        }
    }

    public void changeCursorAndColumns(Cursor  r1, String[]  r2, int[]  r3)
    {


        mOriginalFrom = r2;
        mTo = r3;
        this.changeCursor(r1);
        this.findColumns(mOriginalFrom);
    }

    public CharSequence convertToString(Cursor  r1)
    {

        CharSequence r4;
        if (mCursorToStringConverter == null)
        {
            if (mStringConversionColumn <= -1)
            {
                r4 = this.convertToString(r1);
            }
            else
            {
                r4 = r1.getString(mStringConversionColumn);
            }
        }
        else
        {
            r4 = mCursorToStringConverter.convertToString(r1);
        }

        return r4;
    }

    private void findColumns(String[]  r1)
    {

        int i0, i1;
        if (mCursor == null)
        {
            mFrom = null;
        }
        else
        {
            i0 = r1.length;

            label_0:
            {
                if (mFrom != null)
                {
                    if (mFrom.length == i0)
                    {
                        break label_0;
                    }
                }

                mFrom = new int[i0];
            } //end label_0:


            i1 = 0;

            while (i1 < i0)
            {
                mFrom[i1] = mCursor.getColumnIndexOrThrow(r1[i1]);
                i1 = i1 + 1;
            }
        }
    }

    public SimpleCursorAdapter$CursorToStringConverter getCursorToStringConverter()
    {


        return mCursorToStringConverter;
    }

    public int getStringConversionColumn()
    {


        return mStringConversionColumn;
    }

    public SimpleCursorAdapter$ViewBinder getViewBinder()
    {


        return mViewBinder;
    }

    public void setCursorToStringConverter(SimpleCursorAdapter$CursorToStringConverter  r1)
    {


        mCursorToStringConverter = r1;
    }

    public void setStringConversionColumn(int  i0)
    {


        mStringConversionColumn = i0;
    }

    public void setViewBinder(SimpleCursorAdapter$ViewBinder  r1)
    {


        mViewBinder = r1;
    }

    public void setViewImage(ImageView  r1, String  r2)
    {

        int $i1;
        label_2:
        {
            label_1:
            {
                try
                {
                    $i1 = Integer.parseInt(r2);
                }
                catch (NumberFormatException $r4)
                {
                    break label_1;
                }

                try
                {
                    r1.setImageResource($i1);
                    break label_2;
                }
                catch (NumberFormatException $r4)
                {
                }
            } //end label_1:


            r1.setImageURI(Uri.parse(r2));
        } //end label_2:

    }

    public void setViewText(TextView  r1, String  r2)
    {


        r1.setText(r2);
    }

    public Cursor swapCursor(Cursor  r1)
    {

        Cursor r2;
        r2 = this.swapCursor(r1);
        this.findColumns(mOriginalFrom);
        return r2;
    }
}
