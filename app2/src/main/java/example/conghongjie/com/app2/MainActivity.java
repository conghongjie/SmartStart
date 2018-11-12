package example.conghongjie.com.app2;

import android.os.Bundle;
import android.app.Activity;

import com.conghongjie.start.Test;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Test.main(this.getApplicationContext());
    }
}
