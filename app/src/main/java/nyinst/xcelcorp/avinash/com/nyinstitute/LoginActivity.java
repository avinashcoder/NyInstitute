package nyinst.xcelcorp.avinash.com.nyinstitute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText et_user_id,et_password;
    Button bt_login_submit;
    private String user_id,password;
    TextView tv_new_user;
    String URL_POST ="https://nyinstitute.000webhostapp.com/login.php";
    
    //This is comment for check

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_user_id=(EditText)findViewById(R.id.login_user_id);
        et_password=(EditText)findViewById(R.id.login_password);
        bt_login_submit=(Button)findViewById(R.id.login_submit);

        tv_new_user=(TextView)findViewById(R.id.new_user_sign_up);

        bt_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_id=et_user_id.getText().toString();
                password=et_password.getText().toString();

                Toast.makeText(getApplicationContext(), "Geting Data", Toast.LENGTH_LONG).show();
                loginActivity();

            }
        });

        tv_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginActivity(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Login Success"+response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error + "", Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user_id", user_id);
                params.put("password", password);
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
