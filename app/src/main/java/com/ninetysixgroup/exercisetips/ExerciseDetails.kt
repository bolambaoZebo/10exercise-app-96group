package com.ninetysixgroup.exercisetips

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise_details.*
import kotlinx.android.synthetic.main.popup_dialog.*
import java.util.*

class ExerciseDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_details)
        val intent = intent
        val content = intent.getStringExtra("content")
        val imageContent = intent.getIntExtra("image", 0)
        val actionbar = supportActionBar
        actionbar!!.title = "Let Start"

        detailText.setText(content)
        imageView2.setImageResource(imageContent)
        popupAds()
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
}