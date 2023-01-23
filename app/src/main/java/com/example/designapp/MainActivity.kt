package com.example.designapp

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.designapp.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryadapter: ArrayAdapter<String>
    private lateinit var localityadapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageSelection()

        val categoryList = java.util.ArrayList<String>()
        categoryList.add("Select Category")
        categoryList.add("Damaged")
        categoryList.add("Broken")

        val localityList = java.util.ArrayList<String>()
        localityList.add("Select Locality")
        localityList.add("High")
        localityList.add("Low")


        categoryadapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item,
            categoryList
        )
        binding.spCategory.adapter = categoryadapter


        localityadapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item,
            localityList
        )
        binding.spLocality.adapter = localityadapter

        binding.spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        binding.spLocality.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }
    }

    private fun imageSelection() {
        binding.addImg.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        val cameraIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(cameraIntent, 1)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            val dat: Uri? = data!!.data
            var image: Bitmap? = null
            try {
                image = MediaStore.Images.Media.getBitmap(this.contentResolver, dat)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val h = 60 // height in pixels

            val w = 60 // width in pixels

            val scaled = image?.let { Bitmap.createScaledBitmap(it, h, w, true) }
            binding.previewImg.setImageBitmap(scaled)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}