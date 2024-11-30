package com.example.demoproduct

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ProductAdapter(val context: Activity, val productlist: List<Product>):
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductAdapter.MyViewHolder, position: Int) {
        val currentItem = productlist[position]
        holder.pname?.text = currentItem.title
                holder.preview?.text = "Rating: ${currentItem.rating}"
        holder.pprice?.text = "Price: ${currentItem.price}"
        holder.pdesc?.text  = "Description: ${currentItem.description}"
        holder.pdiscount?.text = "Discount: ${currentItem.discountPercentage}"
        holder.pstock?.text  = "InStock: ${currentItem.stock}"
        holder.pbrand?.text  = "Brand: ${currentItem.brand}"
        Picasso.get().load(currentItem.thumbnail).into(holder.pimage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, description::class.java)
            intent.putExtra("product_name", currentItem.title)
            intent.putExtra("product_price", currentItem.price)
            intent.putExtra("product_description", currentItem.description)
            intent.putExtra("product_image", currentItem.thumbnail)
            intent.putExtra("product_stock", currentItem.stock)
            intent.putExtra("product_brand", currentItem.brand)
            intent.putExtra("product_discount", currentItem.discountPercentage)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return productlist.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var pname: TextView? = itemView.findViewById(R.id.productName)
        var pimage: ShapeableImageView? = itemView.findViewById(R.id.imageView)
        var preview: TextView? = itemView.findViewById(R.id.review)
        var pprice: TextView? = itemView.findViewById(R.id.desprice)
        var pdesc: TextView? = itemView.findViewById(R.id.desdescription)
        var pdiscount: TextView? = itemView.findViewById(R.id.desdiscount)
        var pstock: TextView? = itemView.findViewById(R.id.desstock)
        var pbrand: TextView? = itemView.findViewById(R.id.desbrand)

    }
}
