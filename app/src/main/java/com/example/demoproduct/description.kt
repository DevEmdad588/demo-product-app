package com.example.demoproduct

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


class description : AppCompatActivity() {

    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var productDescriptionTextView: TextView
    private lateinit var productImageView: ShapeableImageView
    private lateinit var productStockTextView: TextView
    private lateinit var productBrandTextView: TextView
    private lateinit var productDiscountTextView: TextView
    private lateinit var backButton: Button
    private lateinit var orderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_description)// Initialize UI elements
        productNameTextView = findViewById(R.id.desname)
        productPriceTextView = findViewById(R.id.desprice)
        productDescriptionTextView = findViewById(R.id.desdescription)
        productImageView = findViewById(R.id.desimage)
        productStockTextView = findViewById(R.id.desstock)
        productBrandTextView = findViewById(R.id.desbrand)
        productDiscountTextView = findViewById(R.id.desdiscount)

        // Get data from Intent
        val productName = intent.getStringExtra("product_name")
        val productPrice = intent.getDoubleExtra("product_price", 0.0)
        val productDescription = intent.getStringExtra("product_description")
        val productImage = intent.getStringExtra("product_image")
        val productStock = intent.getIntExtra("product_stock", 0)
        val productBrand = intent.getStringExtra("product_brand")
        val productDiscount = intent.getDoubleExtra("product_discount", 0.0)

        // Update UI with product details
        productNameTextView.text = productName
        productPriceTextView.text = "Price: $productPrice"
        productDescriptionTextView.text = productDescription
        productStockTextView.text = "Stock: $productStock"
        productBrandTextView.text = "Brand: $productBrand"
        productDiscountTextView.text = "Discount: $productDiscount %"

        // Load product image
        Picasso.get().load(productImage).into(productImageView)

        backButton = findViewById(R.id.backbtn)
        orderButton = findViewById(R.id.orderbtn)

        backButton.setOnClickListener {
            finish()
        }
        orderButton.setOnClickListener {
            showAlertDialog()

        }
    }
    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Order Confirmation")
        builder.setMessage("Are you sure you want to place this order?")

        builder.setPositiveButton("Yes") { dialog, which ->
            // Handle order placement here
            Toast.makeText(this, "Order confirmed!!!", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") { dialog, which ->
            // Dismiss the dialog
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}