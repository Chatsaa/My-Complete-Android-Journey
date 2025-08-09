package com.jumbish.hw2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerViewPractice : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FootballPlayerAdapter
    private lateinit var footballerList: ArrayList<FootBallers>
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_practice)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.rpRecyclerView)
        searchView = findViewById(R.id.searchView)


//       Data initialized
        footballerList = arrayListOf(
            FootBallers(
                "Lionel Messi",
                37,
                "Messi is Argentine forward, 8-time Ballon d'Or winner.",
                R.drawable.lional_messi
            ),
            FootBallers(
                "Cristiano Ronaldo",
                40,
                "Portuguese striker, all-time top scorer.",
                R.drawable.christiano_ronaldo
            ),
            FootBallers(
                "Kylian Mbappé", 26, "French winger, known for lightning speed.", R.drawable.kylian
            ),
            FootBallers(
                "Neymar Jr.", 33, "Brazilian forward, skillful and flashy.", R.drawable.neymar
            ),
            FootBallers(
                "Erling Haaland", 24, "Norwegian striker, goal machine.", R.drawable.erling
            ),
            FootBallers(
                "Luka Modrić", 39, "Croatian midfielder, Ballon d'Or 2018.", R.drawable.luka
            ),
            FootBallers(
                "Mohamed Salah", 33, "Egyptian winger, Liverpool star.", R.drawable.mohamed_salah
            ),
            FootBallers(
                "Kevin De Bruyne", 34, "Belgian playmaker, assists king.", R.drawable.kevin_de
            ),
            FootBallers(
                "Vinícius Jr.", 25, "Brazilian winger, Real Madrid talent.", R.drawable.viniciusjr
            ),
            FootBallers(
                "Harry Kane", 32, "English striker, consistent goal scorer.", R.drawable.harry_kana
            ),
            FootBallers(
                "Jude Bellingham",
                22,
                "English midfielder, rising Real Madrid star.",
                R.drawable.jude_b
            ),
            FootBallers("Pedri", 22, "Spanish midfielder, calm and creative.", R.drawable.pedri),
            FootBallers(
                "João Félix", 25, "Portuguese attacker, stylish playmaker.", R.drawable.joao_flexi
            ),
            FootBallers("Gavi", 21, "Spanish youngster, energetic midfielder.", R.drawable.gavi),
            FootBallers(
                "Lautaro Martínez",
                28,
                "Argentine striker, quick and sharp finisher.",
                R.drawable.lautaro_mrtinez
            ),
            FootBallers(
                "Bukayo Saka", 23, "English winger, Arsenal’s golden boy.", R.drawable.bukayo_saka
            ),
            FootBallers(
                "Declan Rice",
                26,
                "English defensive midfielder, rock solid.",
                R.drawable.declan_rich
            ),
            FootBallers(
                "Marcus Rashford",
                27,
                "English forward, pacey and powerful.",
                R.drawable.marcus_rashford
            ),
            FootBallers(
                "Khvicha Kvaratskhelia",
                24,
                "Georgian winger, Napoli’s wonderkid.",
                R.drawable.khvicha_kvaratskhelia
            ),
            FootBallers(
                "Rafael Leão",
                25,
                "Portuguese winger, flair and speed.Portuguese winger, flair and speed.Portuguese winger, flair and speed.Portuguese winger, flair and speed.",
                R.drawable.rafael_leo
            )

        )
        adapter = FootballPlayerAdapter(footballerList) { position ->
            // showDeleteDialogue(position)

        }

        // searchView Listener (filter)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filterList(newText ?: "")
                return true
            }


        })
        recyclerView.adapter = adapter

        //  Swipe to delete + undo
        val itemTouchHelper = ItemTouchHelper ( object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
//                val deletedItem = footballerList[position]
//                footballerList.removeAt(position)
//                adapter.notifyItemRemoved(position)

                val (deletedItem, originalIndex) = adapter.removeAt(position)


                Snackbar.make(recyclerView, "${deletedItem.name} deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
//                        footballerList.add(position, deletedItem)
//                        adapter.notifyItemInserted(position)

                        adapter.restoreItem(deletedItem,originalIndex,position)
                    }.show()
            }

        })
itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    private fun showDeleteDialogue(position: Int) {
        AlertDialog.Builder(this).setTitle("Delete Player")
            .setMessage("Do you want to delete this player").setPositiveButton("Yes") { _, _ ->
                (adapter.footballerList).removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, adapter.footballerList.size)
            }.setNegativeButton("No", null).show()


    }
}