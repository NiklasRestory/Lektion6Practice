package com.example.letslearninkotlin

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var btn_linear : Button

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv_reply : TextView = findViewById(R.id.tv_reply)

        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add contact")
            .setMessage("Do you want to add to contact list?")
            .setIcon(R.drawable.ic_add_contact_foreground)
            .setPositiveButton("Yes") { _, _ ->
                tv_reply.text = "You added to contacts."
            }
            .setNegativeButton("No") { _, _ ->
                tv_reply.text = "You added no contacts."
            }

        val btn_dialog1 : Button = findViewById(R.id.btn_dialog1)
        val btn_dialog2 : Button = findViewById(R.id.btn_dialog2)
        val btn_dialog3 : Button = findViewById(R.id.btn_dialog3)

        btn_dialog1.setOnClickListener {
            addContactDialog.show()
        }

        val options = arrayOf("First Item", "Second Item", "Third Item")
        val singleChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setSingleChoiceItems(options, 0) { dialogInterface, i ->
                tv_reply.text = "You clicked ${options[i]}"
            }
            .setPositiveButton("Accept") { _, _ ->
                tv_reply.text = tv_reply.text.toString() + " You accepted."
            }
            .setNegativeButton("Decline") { _, _ ->
                tv_reply.text = tv_reply.text.toString() + " You declined."
            }
        btn_dialog2.setOnClickListener {
            singleChoiceDialog.show()
        }

        val multiChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose out of these options")
            .setMultiChoiceItems(options, booleanArrayOf(false, false, false)) { _, i, isChecked->
                if(isChecked) {
                    tv_reply.text = "You checked ${options[i]}"
                }
                else {
                    tv_reply.text = "You unchecked ${options[i]}"
                }

            }
            .setPositiveButton("Accept") { _, _ ->
                tv_reply.text = tv_reply.text.toString() + " You accepted."
            }
            .setNegativeButton("Decline") { _, _ ->
                tv_reply.text = tv_reply.text.toString() + " You declined."
            }
        btn_dialog3.setOnClickListener {
            multiChoiceDialog.show()
        }

        btn_linear = findViewById(R.id.btn_linear)

        val user = User()

        val anInteger = AnInteger(5);

        btn_linear.setOnClickListener {
            val intent = Intent(this, LinearActivity::class.java)
            intent.putExtra("AnInt", anInteger)
            startActivity(intent)
        }

        val sp_months : Spinner = findViewById(R.id.sp_months)

        val customList = listOf("First", "Second", "Third", "Fourth")
        val spinnerAdapter = ArrayAdapter<String>(this,
            R.layout.support_simple_spinner_dropdown_item, customList)
        sp_months.adapter = spinnerAdapter

        sp_months.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //Press CTRL-I.
            override fun onItemSelected(adapterView: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                tv_reply.text = "You selected ${adapterView?.getItemAtPosition(position)}"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //We always have something selected. This never happens.
            }
        }

        val btn_addOne : Button = findViewById(R.id.btn_addOne)
        btn_addOne.setOnClickListener {
            val databaseHelper : DataBaseHelper = DataBaseHelper(this)

            val success = databaseHelper.addOne(User(username = "Joe", password = "fly"))
            tv_reply.text = "Success: " + success
        }

        /*val userList : List<User> = mutableListOf(
            User(0, "Hey", "Yo"),
            User(0, "Hey2", "Yo"),
            User(0, "Hey3", "Yo"),
            User(0, "Hey4", "Yo"),
            User(0, "Hey5", "Yo"),
            User(0, "Hey6", "Yo"),
            User(0, "Hey7", "Yo"),
            User(0, "Hey8", "Yo"),
            User(0, "Hey9", "Yo"),
            User(0, "Hey0", "Yo"),
            User(0, "Hey1", "Yo"),
            User(0, "Hey2", "Yo"))*/
        val databaseHelper = DataBaseHelper(this)

        val userList =  databaseHelper.getEveryone()

        val listAdapter = RecyclerListAdapter(userList)
        var rv_list : RecyclerView = findViewById(R.id.rv_list)
        rv_list.adapter = listAdapter
        rv_list.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var tv_reply : TextView = findViewById(R.id.tv_reply)

        when(item.itemId) {
            R.id.mi_settings -> tv_reply.text = "Settings has been clicked!"
            R.id.mi_favorites -> tv_reply.text = "Favorites has been clicked!"
            R.id.mi_addContact -> tv_reply.text = "Add Contact has been clicked!"
            R.id.mi_feedback -> tv_reply.text = "Feedback has been clicked!"
            R.id.mi_close -> {
                finish()
            }
        }

        return true
    }














    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var tv_reply : TextView = findViewById(R.id.tv_reply)
        //Item is which item was picked.
        when(item.itemId) {
            R.id.mi_addContact -> tv_reply.text = "AddContact is clicked."
            R.id.mi_close -> finish()
            R.id.mi_favorites -> tv_reply.text = "Favorites is clicked."
            R.id.mi_feedback -> tv_reply.text = "Feedback is clicked."
            R.id.mi_settings -> tv_reply.text = "Settings is clicked."
        }
        return true
    }*/
}