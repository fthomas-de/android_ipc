package android.support.v4.widget;

import android.view.animation.Interpolator;

final class BakedBezierInterpolator implements android.view.animation.Interpolator
{
    private static final BakedBezierInterpolator INSTANCE;
    private static final float STEP_SIZE = 0.0f;
    private static final float[] VALUES;

    static
    {

        float[] r2;
        INSTANCE = new BakedBezierInterpolator();
        r2 = new float[101];
        r2[0] = 0.0F;
        r2[1] = 2.0E-4F;
        r2[2] = 9.0E-4F;
        r2[3] = 0.0019F;
        r2[4] = 0.0036F;
        r2[5] = 0.0059F;
        r2[6] = 0.0086F;
        r2[7] = 0.0119F;
        r2[8] = 0.0157F;
        r2[9] = 0.0209F;
        r2[10] = 0.0257F;
        r2[11] = 0.0321F;
        r2[12] = 0.0392F;
        r2[13] = 0.0469F;
        r2[14] = 0.0566F;
        r2[15] = 0.0656F;
        r2[16] = 0.0768F;
        r2[17] = 0.0887F;
        r2[18] = 0.1033F;
        r2[19] = 0.1186F;
        r2[20] = 0.1349F;
        r2[21] = 0.1519F;
        r2[22] = 0.1696F;
        r2[23] = 0.1928F;
        r2[24] = 0.2121F;
        r2[25] = 0.237F;
        r2[26] = 0.2627F;
        r2[27] = 0.2892F;
        r2[28] = 0.3109F;
        r2[29] = 0.3386F;
        r2[30] = 0.3667F;
        r2[31] = 0.3952F;
        r2[32] = 0.4241F;
        r2[33] = 0.4474F;
        r2[34] = 0.4766F;
        r2[35] = 0.5F;
        r2[36] = 0.5234F;
        r2[37] = 0.5468F;
        r2[38] = 0.5701F;
        r2[39] = 0.5933F;
        r2[40] = 0.6134F;
        r2[41] = 0.6333F;
        r2[42] = 0.6531F;
        r2[43] = 0.6698F;
        r2[44] = 0.6891F;
        r2[45] = 0.7054F;
        r2[46] = 0.7214F;
        r2[47] = 0.7346F;
        r2[48] = 0.7502F;
        r2[49] = 0.763F;
        r2[50] = 0.7756F;
        r2[51] = 0.7879F;
        r2[52] = 0.8F;
        r2[53] = 0.8107F;
        r2[54] = 0.8212F;
        r2[55] = 0.8326F;
        r2[56] = 0.8415F;
        r2[57] = 0.8503F;
        r2[58] = 0.8588F;
        r2[59] = 0.8672F;
        r2[60] = 0.8754F;
        r2[61] = 0.8833F;
        r2[62] = 0.8911F;
        r2[63] = 0.8977F;
        r2[64] = 0.9041F;
        r2[65] = 0.9113F;
        r2[66] = 0.9165F;
        r2[67] = 0.9232F;
        r2[68] = 0.9281F;
        r2[69] = 0.9328F;
        r2[70] = 0.9382F;
        r2[71] = 0.9434F;
        r2[72] = 0.9476F;
        r2[73] = 0.9518F;
        r2[74] = 0.9557F;
        r2[75] = 0.9596F;
        r2[76] = 0.9632F;
        r2[77] = 0.9662F;
        r2[78] = 0.9695F;
        r2[79] = 0.9722F;
        r2[80] = 0.9753F;
        r2[81] = 0.9777F;
        r2[82] = 0.9805F;
        r2[83] = 0.9826F;
        r2[84] = 0.9847F;
        r2[85] = 0.9866F;
        r2[86] = 0.9884F;
        r2[87] = 0.9901F;
        r2[88] = 0.9917F;
        r2[89] = 0.9931F;
        r2[90] = 0.9944F;
        r2[91] = 0.9955F;
        r2[92] = 0.9964F;
        r2[93] = 0.9973F;
        r2[94] = 0.9981F;
        r2[95] = 0.9986F;
        r2[96] = 0.9992F;
        r2[97] = 0.9995F;
        r2[98] = 0.9998F;
        r2[99] = 1.0F;
        r2[100] = 1.0F;
        VALUES = r2;
        STEP_SIZE = 1.0F / (float) (VALUES.length + -1);
    }

    private BakedBezierInterpolator()
    {


        this.<init>();
    }

    public static final BakedBezierInterpolator getInstance()
    {


        return INSTANCE;
    }

    public float getInterpolation(float  f0)
    {

        float f1;
        int i1;
        f1 = 1.0F;

        if (f0 - 1.0F < 0)
        {
            if (f0 - 0.0F > 0)
            {
                i1 = Math.min((int) ((float) (VALUES.length + -1) * f0), VALUES.length + -2);
                f1 = VALUES[i1] + (VALUES[i1 + 1] - VALUES[i1]) * ((f0 - (float) i1 * STEP_SIZE) / STEP_SIZE);
            }
            else
            {
                f1 = 0.0F;
            }
        }

        return f1;
    }
}
