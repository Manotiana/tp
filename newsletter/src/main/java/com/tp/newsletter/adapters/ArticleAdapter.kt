package com.tp.newsletter.adapters

import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tp.newsletter.R
import com.tp.newsletter.model.Article
import com.tp.newsletter.utils.PaddingBackgroundColorSpan
import java.lang.Long.parseLong


class   ArticleAdapter ( val dataset: MutableList<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.article_title)
            val imageView = root.findViewById<ImageView>(R.id.article_image)
            val articleSource = root.findViewById<TextView>(R.id.article_source)
            val articleDesc = root.findViewById<TextView>(R.id.article_description)
            addPaddingAndBackground(articleDesc, item.description)
            txtTitle.text = item.title
            articleSource.text = item.source.name


            //txtDesc.text = item.description
            Glide
                    .with(root)
                    .load(item.urlToImage)
                    .fitCenter()
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);

        }
        private fun  addPaddingAndBackground(textView: TextView, str: String, padding: Int = 10){
            textView.setShadowLayer(padding.toFloat() /* radius */, 0.0f, 0.0f, 0 /* transparent */)
            textView.setPadding(padding,padding,padding,padding)
            val spannable: Spannable = SpannableString(str)
            spannable.setSpan(PaddingBackgroundColorSpan(
                    parseLong("A6000000", 16).toInt(),
                    padding
            ), 0, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            textView.text = spannable
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Log.i("Fragment Dataset size","${dataset.size}")
       holder.bind(dataset[position])
    }
     fun addNextResults(itemsNew: List<Article>){
        dataset.addAll(itemsNew)
        notifyDataSetChanged()
    }

}