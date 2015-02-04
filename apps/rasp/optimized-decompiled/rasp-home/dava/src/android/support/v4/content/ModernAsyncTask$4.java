package android.support.v4.content;


class ModernAsyncTask$4
{
    static final int[] $SwitchMap$android$support$v4$content$ModernAsyncTask$Status;

    static
    {

        ModernAsyncTask$Status r1, r5;
        int[] r3, r4;
        int $i2, $i4;
        $SwitchMap$android$support$v4$content$ModernAsyncTask$Status = new int[ModernAsyncTask$Status.values().length];

        label_2:
        {
            label_0:
            {
                r3 = $SwitchMap$android$support$v4$content$ModernAsyncTask$Status;

                r1 = ModernAsyncTask$Status.RUNNING;

                $i2 = r1.ordinal();

                r3[$i2] = 1;
                break label_2;
            } //end label_0:

        } //end label_2:


        label_3:
        {
            label_1:
            {
                r4 = $SwitchMap$android$support$v4$content$ModernAsyncTask$Status;

                r5 = ModernAsyncTask$Status.FINISHED;

                $i4 = r5.ordinal();

                r4[$i4] = 2;
                break label_3;
            } //end label_1:

        } //end label_3:

    }
}
