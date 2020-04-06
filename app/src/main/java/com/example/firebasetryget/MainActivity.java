package com.example.firebasetryget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public EditText veg_name,veg_avb,veg_price;
    public Button save;

   public  static  final String Keyveg ="vegitable";
    public  static  final String Key_avb ="avb";
    public  static  final String KEY_PRICE= "price";

    public FirebaseFirestore  db= FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        veg_name=findViewById(R.id.vegname);
        veg_avb=findViewById(R.id.avb);
        veg_price=findViewById(R.id.price);

        save = findViewById(R.id.save);



        save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String vegName=veg_name.getText().toString().trim();
              String vegAvablity=veg_avb.getText().toString().trim();
              String vegPrice=veg_price.getText().toString().trim();


              Map<String, Object> data= new HashMap<>();
              data.put(Keyveg,vegName);
              data.put(Key_avb ,vegAvablity);
              data.put(KEY_PRICE,vegPrice);
              db.collection("Journal")
                      .document("DataTable")
                      .set(data)
                      .addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void aVoid) {
                              Toast.makeText(MainActivity.this,"sucsesfull",Toast.LENGTH_SHORT).show();
                          }
                      })
                      .addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception e) {
                              Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_SHORT).show();

                          }
                      });



          }
      });


    }
}
