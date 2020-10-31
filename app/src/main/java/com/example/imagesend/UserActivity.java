package com.example.imagesend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.security.AccessController.getContext;

public class UserActivity extends AppCompatActivity {
        private static final String TAG = "UserActivity";
        private List<Userlist> list = new ArrayList<>();
        private UserAdapter adapter;
        private FirebaseUser firebaseUser;
        private FirebaseFirestore firestore;


        private RecyclerView recyclerView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_user);

                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                firestore = FirebaseFirestore.getInstance();

                if (firebaseUser != null) {
                        getContactList(); // If they using this app
                        // getContactList();
                }

        }


        private void getContactList() {

                firestore.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {
                                        String userID = snapshots.getString("userID");
                                        String userName = snapshots.getString("userName");
                                        String imageUrl = snapshots.getString("imageProfile");
                                        String desc = snapshots.getString("bio");
                                        String phone = snapshots.getString("userPhone");

                                        Userlist user = new Userlist();
                                        user.setUserID(userID);
                                        user.setUserName(userName);
                                        user.setImageProfile(imageUrl);
                                        user.setUserPhone(phone);

                                        if (userID != null && !userID.equals(firebaseUser.getUid())) {

                                                        list.add(user);

                                        }
                                }


                                adapter = new UserAdapter(list, UserActivity.this);
                                recyclerView.setAdapter(adapter);
                        }

                });
        }
}

