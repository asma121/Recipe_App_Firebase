package com.example.recipeappfirebase_optional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var ettitle: EditText
    lateinit var etname: EditText
    lateinit var etingre: EditText
    lateinit var etinstru: EditText
    lateinit var busave: Button
    lateinit var buview: Button
    lateinit var rv:RecyclerView
    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Firebase.firestore
        setContentView(R.layout.activity_main)
        ettitle=findViewById(R.id.ettitle)
        etname=findViewById(R.id.etname)
        etingre=findViewById(R.id.etingre)
        etinstru=findViewById(R.id.etinstru)
        busave=findViewById(R.id.busave)
        buview=findViewById(R.id.buview)
        rv=findViewById(R.id.rv)

        busave.setOnClickListener {
            if (ettitle.text.isNotEmpty() && etname.text.isNotEmpty() && etingre.text.isNotEmpty() && etinstru.text.isNotEmpty()){
                val recipe = hashMapOf(
                    "Title" to ettitle.text.toString(),
                    "Author" to etname.text.toString(),
                    "Ingredients" to etingre.text.toString(),
                    "Instructions" to etinstru.text.toString()
                )

                db.collection("recipes")
                    .add(recipe)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
               }
        }

        buview.setOnClickListener {
            var recipes=ArrayList<ArrayList<String>>()
            db.collection("recipes")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                        recipes.add(arrayListOf( document.get("Title").toString(),
                            document.get("Author").toString(),
                            document.get("Ingredients").toString(),
                            document.get("Instructions").toString()))
                    }
                    rv.adapter=myAdapter(recipes)
                    rv.layoutManager=LinearLayoutManager(this)
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }

        }

    }
}