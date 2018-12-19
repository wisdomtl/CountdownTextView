package taylor.com.countdowntextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountdownTextView tv = findViewById(R.id.tv_countdown);
        tv.setDuration(10);
        tv.setOnClickListener(new CountdownTextView.OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "start counting", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
