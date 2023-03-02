package com.example.task2gdsc

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.task2gdsc.databinding.FragmentMenu2Binding


class Menu2Fragment : Fragment() {
    private lateinit var binding:FragmentMenu2Binding
    private var apps:java.util.ArrayList<AppInfo> = arrayListOf()
    private var mAdapter: RecyclerViewAdapter? = null
    private lateinit var packageManager: PackageManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenu2Binding.inflate(layoutInflater)
        packageManager = requireContext().packageManager

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rcvMenu2.layoutManager = layoutManager

        if(apps.isEmpty()){
            binding.progressBarMenu2.visibility = View.VISIBLE
            getInfoApp()
            updateUI()
        }else{
            updateUI()
        }

        mAdapter!!.setOnClickListener(object:RecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                launchApp(apps[position].packageName!!)
            }

        })


        return binding.root
    }

    private fun getInfoApp(){
        val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)


        for (packageInfo in packages) {
            val appName = packageInfo.loadLabel(packageManager).toString()
            val icon = packageInfo.loadIcon(packageManager)
            val packageName = packageInfo.packageName
            val app = AppInfo(icon, appName, packageName)
            apps.add(app)
        }
    }
    private fun updateUI(){
        binding.progressBarMenu2.visibility = View.GONE
        binding.rcvMenu2.visibility = View.VISIBLE
        mAdapter = RecyclerViewAdapter(apps)
        binding.rcvMenu2.adapter = mAdapter
    }

    private fun launchApp(packageName:String){
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if(intent!=null){
            startActivity(intent)
        }else{
            Toast.makeText(requireContext(), "ứng dụng không hỗ trợ mở như vậy", Toast.LENGTH_SHORT).show()
        }
    }
}