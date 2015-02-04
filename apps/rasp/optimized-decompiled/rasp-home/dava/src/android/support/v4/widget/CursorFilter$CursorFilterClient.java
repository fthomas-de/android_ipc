package android.support.v4.widget;

import android.database.Cursor;

abstract interface CursorFilter$CursorFilterClient
{

    public abstract void changeCursor(android.database.Cursor  r0);

    public abstract java.lang.CharSequence convertToString(android.database.Cursor  r0);

    public abstract android.database.Cursor getCursor();

    public abstract android.database.Cursor runQueryOnBackgroundThread(java.lang.CharSequence  r0);
}
