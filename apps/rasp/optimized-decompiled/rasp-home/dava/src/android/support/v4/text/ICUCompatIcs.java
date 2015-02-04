package android.support.v4.text;

import java.lang.reflect.Method;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

class ICUCompatIcs
{
    private static final String TAG = "ICUCompatIcs";
    private static Method sAddLikelySubtagsMethod;
    private static Method sGetScriptMethod;

    static
    {

        String r0, r4, r8;
        Class r1, r2, r10;
        Class[] r5, r9;
        Method $r6, $r11;
        label_1:
        {
            label_0:
            {
                try
                {
                    r0 = "libcore.icu.ICU";
                }
                catch (Exception $r13)
                {
                    break label_0;
                }

                r1 = Class.forName(r0);

                if (r1 == null)
                {
                    break label_1;
                }
                else
                {
                    try
                    {
                        r4 = "getScript";
                    }
                    catch (Exception $r13)
                    {
                        break label_0;
                    }

                    try
                    {
                        r5 = new Class[1];
                    }
                    catch (Exception $r13)
                    {
                        break label_0;
                    }

                    try
                    {
                        r2 = class "java/lang/String";
                    }
                    catch (Exception $r13)
                    {
                        break label_0;
                    }

                    r5[0] = r2;

                    try
                    {
                        $r6 = r1.getMethod(r4, r5);
                    }
                    catch (Exception $r13)
                    {
                        break label_0;
                    }

                    try
                    {
                        sGetScriptMethod = $r6;
                    }
                    catch (Exception $r13)
                    {
                        break label_0;
                    }

                    r8 = "addLikelySubtags";

                    try
                    {
                        r9 = new Class[1];
                    }
                    catch (Exception $r13)
                    {
                        break label_0;
                    }

                    try
                    {
                        r10 = class "java/lang/String";
                    }
                    catch (Exception $r13)
                    {
                        break label_0;
                    }

                    r9[0] = r10;

                    try
                    {
                        $r11 = r1.getMethod(r8, r9);
                    }
                    catch (Exception $r13)
                    {
                        break label_0;
                    }

                    try
                    {
                        sAddLikelySubtagsMethod = $r11;
                        break label_1;
                    }
                    catch (Exception $r13)
                    {
                    }
                }
            } //end label_0:


            Log.w("ICUCompatIcs", $r13);
        } //end label_1:

    }

    ICUCompatIcs()
    {


        this.<init>();
    }

    public static String addLikelySubtags(String  r0)
    {

        Method r1, r4;
        Object[] r2;
        Object n0;
        Object $r5;
        String r7;
        label_5:
        {
            label_4:
            {
                label_3:
                {
                    label_2:
                    {
                        r1 = sAddLikelySubtagsMethod;

                        if (r1 == null)
                        {
                            break label_4;
                        }
                        else
                        {
                            r2 = new Object[1];

                            r2[0] = r0;

                            r4 = sAddLikelySubtagsMethod;
                            n0 = null;

                            try
                            {
                                $r5 = r4.invoke(n0, r2);
                            }
                            catch (IllegalAccessException $r8)
                            {
                                break label_2;
                            }
                            catch (InvocationTargetException $r10)
                            {
                                break label_3;
                            }

                            r7 = (String) $r5;
                            break label_5;
                        }
                    } //end label_2:


                    Log.w("ICUCompatIcs", $r8);
                    break label_4;
                } //end label_3:


                Log.w("ICUCompatIcs", $r10);
            } //end label_4:


            r7 = r0;
        } //end label_5:


        return r7;
    }

    public static String getScript(String  r0)
    {

        Object n0, n1;
        Method r1, r4;
        Object[] r2;
        Object $r5;
        String r7;
        n0 = null;

        label_9:
        {
            label_8:
            {
                label_7:
                {
                    label_6:
                    {
                        r1 = sGetScriptMethod;

                        if (r1 == null)
                        {
                            break label_8;
                        }
                        else
                        {
                            r2 = new Object[1];

                            r2[0] = r0;

                            r4 = sGetScriptMethod;
                            n1 = null;

                            try
                            {
                                $r5 = r4.invoke(n1, r2);
                            }
                            catch (IllegalAccessException $r8)
                            {
                                break label_6;
                            }
                            catch (InvocationTargetException $r10)
                            {
                                break label_7;
                            }

                            r7 = (String) $r5;
                            break label_9;
                        }
                    } //end label_6:


                    Log.w("ICUCompatIcs", $r8);
                    break label_8;
                } //end label_7:


                Log.w("ICUCompatIcs", $r10);
            } //end label_8:


            r7 = n0;
        } //end label_9:


        return r7;
    }
}
