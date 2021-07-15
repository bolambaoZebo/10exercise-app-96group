package com.ninetysixgroup.exercisetips

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ninetysixgroup.exercisetips.databinding.ActivityScrollingBinding
import com.ninetysixgroup.model.WorkOutModel
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.popup_dialog.*
import java.util.*
import kotlin.collections.ArrayList

class ScrollingActivity : AppCompatActivity(), RecyclerAdapter.onItemClicked {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar.title = ""
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = ""

        popupAds()
        val url = "https://88proasia.com"
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)

        binding.fab.setOnClickListener { view ->
            startActivity(openURL)
        }

        home_recycler.layoutManager = GridLayoutManager(
            this,
            2,
            LinearLayoutManager.VERTICAL,
            false
        )
        home_recycler.adapter = RecyclerAdapter(this,getAllRacing(), this)


    }

    private fun popupAds(){
        val url = "https://88proasia.com"
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)

        val random = Random()
        val imgs = getResources().obtainTypedArray(R.array.pop_random_);

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_dialog)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        dialog.background.background = resources.getDrawable(imgs.getResourceId(random.nextInt(3), -1))

        dialog.img_exit.setOnClickListener{
            dialog.dismiss()
        }

        dialog.btn_clickhere.setOnClickListener{
            startActivity(openURL)
        }

        dialog.show();
    }

    private fun getAllRacing(): ArrayList<WorkOutModel> {
        val homeimages = getResources().obtainTypedArray(R.array.array_image)
        val content = resources.getStringArray(R.array.array_content)
        val contentImage = getResources().obtainTypedArray(R.array.array_contentimage)
        val list = ArrayList<WorkOutModel>()


        for (i in 0 until homeimages.length()){
            list.add(WorkOutModel(
                homeimages.getResourceId(i, -1),
                contentImage.getResourceId(i, -1),
                content[i],
                RecyclerAdapter.VIEW_TYPE_ONE))
        }

        return list
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onClicked(context: Context, content: String, image: Int) {
        val intent = Intent(context, ExerciseDetails::class.java).apply {
            putExtra("content", "${content}")
            putExtra("image", image)
        }
        startActivity(intent)
    }
}