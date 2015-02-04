package android.support.v4.content;

import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import android.text.TextUtils;
import android.net.Uri;
import java.util.Map$Entry;
import java.util.Iterator;
import java.util.Set;
import android.net.Uri$Builder;

class FileProvider$SimplePathStrategy implements android.support.v4.content.FileProvider$PathStrategy
{
    private final String mAuthority;
    private final HashMap mRoots;

    public FileProvider$SimplePathStrategy(String  r1)
    {


        this.<init>();
        mRoots = new HashMap();
        mAuthority = r1;
    }

    public void addRoot(String  r1, File  r2)
    {

        File $r7;
        if (TextUtils.isEmpty(r1) == false)
        {
            try
            {
                $r7 = r2.getCanonicalFile();
            }
            catch (IOException $r11)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(r2).toString(), $r11);
            }

            mRoots.put(r1, $r7);
            return;
        }
        else
        {
            throw new IllegalArgumentException("Name must not be empty");
        }
    }

    public File getFileForUri(Uri  r1)
    {

        String r2, r3, r10;
        int i2;
        File r6, r12, $r18;
        r2 = r1.getEncodedPath();
        i2 = r2.indexOf(47, 1);
        r3 = Uri.decode(r2.substring(1, i2));
        r10 = Uri.decode(r2.substring(i2 + 1));
        r12 = (File) mRoots.get(r3);

        if (r12 != null)
        {
            r6 = new File(r12, r10);

            try
            {
                $r18 = r6.getCanonicalFile();
            }
            catch (IOException $r24)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(r6).toString());
            }

            if ($r18.getPath().startsWith(r12.getPath()) != false)
            {
                return $r18;
            }
            else
            {
                throw new SecurityException("Resolved path jumped beyond configured root");
            }
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unable to find configured root for ").append(r1).toString());
        }
    }

    public Uri getUriForFile(File  r1)
    {

        Map$Entry r3, r12;
        Iterator r5;
        String r7, $r10, r32, r34, r44;
        try
        {
            $r10 = r1.getCanonicalPath();
        }
        catch (IOException $r18)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(r1).toString());
        }

        r3 = null;
        r5 = mRoots.entrySet().iterator();

        label_0:
        while (r5.hasNext() != false)
        {
            r12 = (Map$Entry) r5.next();
            r7 = ((File) r12.getValue()).getPath();

            if ($r10.startsWith(r7) != false)
            {
                if (r3 != null)
                {
                    if (r7.length() <= ((File) r3.getValue()).getPath().length())
                    {
                        continue label_0;
                    }
                }

                r3 = r12;
            }
        }

        if (r3 != null)
        {
            r32 = ((File) r3.getValue()).getPath();

            if (r32.endsWith("/") == false)
            {
                r34 = $r10.substring(r32.length() + 1);
            }
            else
            {
                r34 = $r10.substring(r32.length());
            }

            r44 = (new StringBuilder()).append(Uri.encode((String) r3.getKey())).append('/').append(Uri.encode(r34, "/")).toString();
            return (new Uri$Builder()).scheme("content").authority(mAuthority).encodedPath(r44).build();
        }
        else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to find configured root that contains ").append($r10).toString());
        }
    }
}
