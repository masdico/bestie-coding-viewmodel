package my.masdico.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import my.masdico.viewmodel.databinding.ListMatchBinding


class ListMatchAdapter(private val list: ArrayList<BasketballTeam>) : RecyclerView.Adapter<ListMatchAdapter.ListViewHolder>() {

    private lateinit var itemListener: OnItemClickListener
    private lateinit var buttonListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClicked(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        itemListener = listener
    }

    fun setOnButtonClickListener(listener: OnItemClickListener){
        buttonListener = listener
    }

    class ListViewHolder(itemView: ListMatchBinding) : RecyclerView.ViewHolder(itemView.root) {
        var tvName: TextView = itemView.tvTeamName
        var tvDetail: TextView = itemView.tvTeamDetails
        var imgLogo: ImageView = itemView.imgList
        var btnCount: Button = itemView.btnCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListMatchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val basketballTeam = list[position]

        Glide.with(holder.itemView.context)
            .load(basketballTeam.imgLogo)
            .apply(RequestOptions().override(55,55))
            .into(holder.imgLogo)
        holder.tvName.text = basketballTeam.name
        holder.tvDetail.text = basketballTeam.details

        holder.btnCount.setOnClickListener { buttonListener.onItemClicked(position) }
        holder.itemView.setOnClickListener { itemListener.onItemClicked(position) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}