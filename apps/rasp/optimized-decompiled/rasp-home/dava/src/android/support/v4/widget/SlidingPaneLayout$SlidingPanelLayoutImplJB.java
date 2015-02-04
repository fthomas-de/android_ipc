package android.support.v4.widget;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import android.util.Log;
import android.view.View;

class SlidingPaneLayout$SlidingPanelLayoutImplJB extends SlidingPaneLayout$SlidingPanelLayoutImplBase
{
    private Method mGetDisplayList;
    private Field mRecreateDisplayList;

    SlidingPaneLayout$SlidingPanelLayoutImplJB()
    {

        Class r1, r6;
        Object n0;
        Class[] r4;
        Method r5;
        String r7;
        Field $r8, r10;
        this.<init>();

        label_2:
        {
            label_0:
            {
                r1 = class "android/view/View";

                n0 = null;

                r4 = (Class[]) n0;

                r5 = r1.getDeclaredMethod("getDisplayList", r4);

                mGetDisplayList = r5;
                break label_2;
            } //end label_0:


            Log.e("SlidingPaneLayout", "Couldn\'t fetch getDisplayList method; dimming won\'t work right.", $r11);
        } //end label_2:


        label_3:
        {
            label_1:
            {
                r6 = class "android/view/View";

                r7 = "mRecreateDisplayList";

                try
                {
                    $r8 = r6.getDeclaredField(r7);
                }
                catch (NoSuchFieldException $r14)
                {
                    break label_1;
                }

                mRecreateDisplayList = $r8;

                r10 = mRecreateDisplayList;

                r10.setAccessible(true);
                break label_3;
            } //end label_1:


            Log.e("SlidingPaneLayout", "Couldn\'t fetch mRecreateDisplayList field; dimming will be slow.", $r14);
        } //end label_3:

    }

    public void invalidateChildRegion(SlidingPaneLayout  r1, View  r2)
    {

        Field r6;
        Object n0;
        Object[] r8;
        label_7:
        {
            if (mGetDisplayList != null)
            {
                if (mRecreateDisplayList != null)
                {
                    label_6:
                    {
                        label_5:
                        {
                            label_4:
                            {
                                try
                                {
                                    r6 = mRecreateDisplayList;
                                }
                                catch (Exception $r10)
                                {
                                    break label_4;
                                }

                                try
                                {
                                    r6.setBoolean(r2, true);
                                }
                                catch (Exception $r10)
                                {
                                    break label_4;
                                }

                                n0 = null;

                                try
                                {
                                    r8 = (Object[]) n0;
                                    break label_5;
                                }
                                catch (Exception $r10)
                                {
                                }
                            } //end label_4:


                            Log.e("SlidingPaneLayout", "Error refreshing display list state", $r10);
                            break label_6;
                        } //end label_5:


                        mGetDisplayList.invoke(r2, r8);
                    } //end label_6:


                    this.invalidateChildRegion(r1, r2);
                    break label_7;
                }
            }

            r2.invalidate();
        } //end label_7:

    }
}
