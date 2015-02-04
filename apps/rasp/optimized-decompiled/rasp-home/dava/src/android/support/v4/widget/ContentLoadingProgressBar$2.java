package android.support.v4.widget;


class ContentLoadingProgressBar$2 implements java.lang.Runnable
{
    final ContentLoadingProgressBar this$0;

    ContentLoadingProgressBar$2(ContentLoadingProgressBar  r1)
    {


        this$0 = r1;
        this.<init>();
    }

    public void run()
    {


        ContentLoadingProgressBar.access$202(this$0, false);

        if (ContentLoadingProgressBar.access$300(this$0) == false)
        {
            ContentLoadingProgressBar.access$102(this$0, System.currentTimeMillis());
            this$0.setVisibility((int) 0);
        }
    }
}
