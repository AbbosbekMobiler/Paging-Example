package abbosbek.mobiler.paginglibrary.adapter

import abbosbek.mobiler.paginglibrary.databinding.ItemLayoutBinding
import abbosbek.mobiler.paginglibrary.models.RickMorty
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale

class PagingAdapter : PagingDataAdapter<RickMorty, PagingAdapter.MyViewHolder>(differCallback) {

    inner class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    companion object{
        val differCallback = object : DiffUtil.ItemCallback<RickMorty>(){
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            textView.text = "${item?.name}"
            val imageLink = item?.image
            imageView.load(imageLink){
                crossfade(true)
                crossfade(1000)
                scale(Scale.FILL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

}