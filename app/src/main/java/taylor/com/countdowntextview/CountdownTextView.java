package taylor.com.countdowntextview;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

/**
 * a TextView which has the ability to count down by itself and show the count down seconds
 */
public class CountdownTextView extends AppCompatTextView implements View.OnClickListener {
    public static final int COUNT_DOWN_INTERVAL_IN_MILLISECOND = 1000;
    /**
     * the countdown span in second
     */
    private static Handler countdownHandler;
    private String COUNTDOWN_PATTERN = "%s s";
    private OnClickListener onClickListener;
    private Runnable countdownRunnable;
    private String originText;

    public CountdownTextView(Context context) {
        super(context);
        init();
    }

    public CountdownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountdownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private void init() {
        countdownHandler = new Handler();
        this.setOnClickListener(this);
        originText = getText().toString();
    }

    /**
     * set countdown span for counting down
     *
     * @param duration in second
     */
    public void setDuration(final int duration) {
        countdownRunnable = new Runnable() {
            int timeSpan = duration;

            @Override
            public void run() {
                timeSpan--;
                setText(String.format(COUNTDOWN_PATTERN, String.valueOf(timeSpan)));
                if (timeSpan != 0) {
                    //repeat self to continue counting
                    countdownHandler.postDelayed(this, COUNT_DOWN_INTERVAL_IN_MILLISECOND);
                } else {
                    //the end of countdown
                    setText(originText);
                    timeSpan = duration;
                    stop();
                    setClickable(true);
                }
            }
        };
    }

    /**
     * stop counting down
     */
    public void stop() {
        if (countdownHandler != null) {
            countdownHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick();
        }
        //start counting down
        countdownHandler.post(countdownRunnable);
        setClickable(false);
    }

    /**
     * an callback let CountdownTextView's host if he wants to do something when CountdownTextView is clicked
     */
    public interface OnClickListener {
        void onClick();
    }
}
