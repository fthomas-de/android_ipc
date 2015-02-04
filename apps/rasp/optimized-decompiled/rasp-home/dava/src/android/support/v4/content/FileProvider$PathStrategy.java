package android.support.v4.content;

import android.net.Uri;
import java.io.File;

abstract interface FileProvider$PathStrategy
{

    public abstract java.io.File getFileForUri(android.net.Uri  r0);

    public abstract android.net.Uri getUriForFile(java.io.File  r0);
}
