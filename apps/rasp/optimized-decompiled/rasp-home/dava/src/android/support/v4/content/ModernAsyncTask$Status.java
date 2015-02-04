package android.support.v4.content;


public final enum class ModernAsyncTask$Status extends Enum
{
    private static final ModernAsyncTask$Status[] $VALUES;
    public static final enum ModernAsyncTask$Status FINISHED;
    public static final enum ModernAsyncTask$Status PENDING;
    public static final enum ModernAsyncTask$Status RUNNING;

    static
    {

        ModernAsyncTask$Status[] r6;
        PENDING = new ModernAsyncTask$Status("PENDING", 0);
        RUNNING = new ModernAsyncTask$Status("RUNNING", 1);
        FINISHED = new ModernAsyncTask$Status("FINISHED", 2);
        r6 = new ModernAsyncTask$Status[3];
        r6[0] = PENDING;
        r6[1] = RUNNING;
        r6[2] = FINISHED;
        $VALUES = r6;
    }

    private ModernAsyncTask$Status(String  r1, int  i0)
    {
        super(r1, i0);


        this.<init>(r1, i0);
    }

    public static ModernAsyncTask$Status valueOf(String  r0)
    {


        return (ModernAsyncTask$Status) Enum.valueOf(class "android/support/v4/content/ModernAsyncTask$Status", r0);
    }

    public static ModernAsyncTask$Status[] values()
    {


        return (ModernAsyncTask$Status[]) $VALUES.clone();
    }
}
