package com.hikari.nihongonoseito.Kana

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.hikari.nihongonoseito.R
import com.hikari.nihongonoseito.dataclass.Kana
import com.hikari.nihongonoseito.dataclass.KanaExib
import kotlinx.android.synthetic.main.fragment_kana_list.view.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [KanaFragment.OnListFragmentInteractionListener] interface.
 */
class KanaFragment : androidx.fragment.app.Fragment(), SimpleAdapter.ItemClickListener {

    // TODO: Customize parameters
    private var columnCount = 1
    private lateinit var myTopBar: TabLayout
    private var listener: OnListFragmentInteractionListener? = null
    private var telaAtual: Boolean = true //True = Hiragana, False = Katakana
    private lateinit var mRecyclerViewKana: androidx.recyclerview.widget.RecyclerView
    private lateinit var myRecyclerAdapterKana: RecyclerView.Adapter<RecyclerView.ViewHolder>

    val mOnNavigationItemSelectedListener: TabLayout.OnTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabUnselected(p0: TabLayout.Tab?) {
            telaAtual = !telaAtual
        }

        var selected = false
        override fun onTabSelected(p0: TabLayout.Tab?) {
            if (p0 != null) {
                selected = p0.isSelected
                when (p0.position) {
                    0 -> {
                        telaAtual = true;
                        changeHiraKata(telaAtual);
                    }
                    1 -> {
                        telaAtual = false
                        changeHiraKata(telaAtual)
                    }
                }
            }
        }

        override fun onTabReselected(p0: TabLayout.Tab?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        var BoolRetorno: Boolean = false

    }

    private fun changeHiraKata(hiraOuKataBollean: Boolean) {
        var numberOfItens = 0
        val imgWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40f, resources.displayMetrics)
        val mItemArray: java.util.ArrayList<KanaExib>


        //This is the code to provide a sectioned grid
        val sections = java.util.ArrayList<SectionedGridRecyclerViewAdapter.Section>()

        var contador = 0

        numberOfItens = resources.getStringArray(R.array.hira_text).size;
        sections.add(SectionedGridRecyclerViewAdapter.Section(0, "Basico"))
        sections.add(SectionedGridRecyclerViewAdapter.Section(55, "Váriaveis"))
        sections.add(SectionedGridRecyclerViewAdapter.Section(80, "Junções"))
        mItemArray = ArrayList<KanaExib>(numberOfItens)
        val kanaText = if (hiraOuKataBollean) resources.getStringArray(R.array.hira_text) else resources.getStringArray(R.array.kata_text)
        val kanaimg = if (hiraOuKataBollean) resources.getStringArray(R.array.hira_img) else resources.getStringArray(R.array.kata_img)
        for (i in 0 until numberOfItens) {
            try {
                val img: String = if (kanaimg[i].equals("*")) "z" else kanaimg[i]
                mItemArray.add(KanaExib(kanaText[i], resources.getStringArray(R.array.kana_roma)[i], getResources().getDrawable(resources.getIdentifier(img, "drawable", activity?.packageName)), if (i < 55) 0 else if (i < 80) 1 else 2))
            } catch (e: Exception) {
                Log.e("kanaFrag: ", "Not Found asset, image: " + i)
                e.printStackTrace()
            }
        }
        if (context != null) {
            val layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 15)
            mRecyclerViewKana.setLayoutManager(layoutManager)
            myRecyclerAdapterKana = SimpleAdapter(context, mItemArray, imgWidth, true) as RecyclerView.Adapter<RecyclerView.ViewHolder>
            (myRecyclerAdapterKana as SimpleAdapter).setmClickListener(this)
            //Your RecyclerView.Adapter
            mRecyclerViewKana.adapter = myRecyclerAdapterKana
            val itemDecoration = ItemDecorationOffset(context!!, R.dimen.item_offset)
            mRecyclerViewKana.addItemDecoration(itemDecoration)
            mRecyclerViewKana.setHasFixedSize(true)
            myRecyclerAdapterKana.notifyItemRangeChanged(0, mItemArray.size)

            //Add your adapter to the sectionAdapter
            val mSectionedAdapter = SectionedGridRecyclerViewAdapter(context, R.layout.section, R.id.section_text, mRecyclerViewKana, myRecyclerAdapterKana)
            mSectionedAdapter.setSections(sections.toTypedArray())

            //Apply this adapter to the RecyclerView

            mRecyclerViewKana.setAdapter(mSectionedAdapter)

        }


    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(context, mRecyclerViewKana.getChildViewHolder(view).getItemViewType().toString() + " " + position, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_kana_list, container, false)

        myTopBar = view.myTopBar

        myTopBar.addOnTabSelectedListener(mOnNavigationItemSelectedListener)



        myTopBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                telaAtual = p0?.position == 0
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                telaAtual = !(p0?.position == 0)
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

                telaAtual = p0?.position == 0
            }

        })
        //myTopBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // Set the adapter
        mRecyclerViewKana = view.myRecyclerkana
        if (mRecyclerViewKana is androidx.recyclerview.widget.RecyclerView && mRecyclerViewKana != null) {
            changeHiraKata(telaAtual)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Kana?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                KanaFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
