package android.support.v4.app;

import android.view.MenuItem;

class ShareCompat$ShareCompatImplBase implements android.support.v4.app.ShareCompat$ShareCompatImpl
{

    ShareCompat$ShareCompatImplBase()
    {


        this.<init>();
    }

    public void configureMenuItem(MenuItem  r1, ShareCompat$IntentBuilder  r2)
    {


        r1.setIntent(r2.createChooserIntent());
    }

    public String escapeHtml(CharSequence  r1)
    {

        StringBuilder r2;
        r2 = new StringBuilder();
        ShareCompat$ShareCompatImplBase.withinStyle(r2, r1, 0, r1.length());
        return r2.toString();
    }

    private static void withinStyle(StringBuilder  r0, CharSequence  r1, int  i0, int  i1)
    {

        int i3;
        char c4;
        i3 = i0;

        while (i3 < i1)
        {
            c4 = r1.charAt(i3);

            label_0:
            if (c4 != '<')
            {
                if (c4 != '>')
                {
                    if (c4 != '&')
                    {
                        if (c4 <= '~')
                        {
                            if (c4 >= ' ')
                            {
                                if (c4 != ' ')
                                {
                                    r0.append(c4);
                                    break label_0;
                                }
                                else
                                {
                                    if (i3 + 1 < i1)
                                    {
                                        if (r1.charAt(i3 + 1) == ' ')
                                        {
                                            r0.append("&nbsp;");
                                            i3 = i3 + 1;
                                        }
                                    }

                                    r0.append(' ');
                                    break label_0;
                                }
                            }
                        }

                        r0.append((new StringBuilder()).append("&#").append(c4).append(";").toString());
                    }
                    else
                    {
                        r0.append("&amp;");
                    }
                }
                else
                {
                    r0.append("&gt;");
                }
            }
            else
            {
                r0.append("&lt;");
            }

            i3 = i3 + 1;
        }
    }
}
