package subtleparesh.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name, add, number;
    Button submit;
    String Name , address, phoneNumber;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editText2);
        add = findViewById(R.id.editText3);
        number = findViewById(R.id.editText4);
        submit = findViewById(R.id.button);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("User", "************** INSERTION STARTED ******************");

                Name = name.getText().toString();
                address = add.getText().toString();
                phoneNumber = number.getText().toString();

                databaseHelper.addUser(new User(Name, address, phoneNumber));
                Log.e("User", "************* INSERTION COMPLETED *******************");


                List<User> users = databaseHelper.getAllUser();

                Log.e("User", "************** DATA RETRIVING ******************");
                for(User user : users)
                {
                    Log.e("User",  " id "+user.getId());
                    Log.e("User", " name "+user.getName());
                    Log.e("User", " address "+user.getAddress());
                    Log.e("User", " phoneNumber "+user.getPhoneNumber());
                    Log.e("User", "**************************************");

                }
                Log.e("User", "**************** RETRIVING COMPLETED ****************");
            }
        });



    }
}
