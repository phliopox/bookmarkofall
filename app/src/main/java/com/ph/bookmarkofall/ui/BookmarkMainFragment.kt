package com.ph.bookmarkofall.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ph.bookmarkofall.data.common.PreferenceManager
import com.ph.bookmarkofall.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BookmarkMainFragment : Fragment() {
    private lateinit var binding : FragmentBookmarkBinding
    private lateinit var userPref : PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //앱 최초 실행시 gender 선택 다이얼로그 띄우기
        userPref = PreferenceManager(requireContext().applicationContext)
        runBlocking {
            launch {
                if(userPref.firstActivate.first().isNullOrEmpty()){
                    val dialog = SwipeDismissDialogFragment.newInstance()
                    dialog.show(parentFragmentManager, "swipe_dismiss_dialog")
                    // userPref.isNotFirstActivate()  //테스트를 위해 계속 띄우려고 지워둠 !
                }
            }
        }
    }
}