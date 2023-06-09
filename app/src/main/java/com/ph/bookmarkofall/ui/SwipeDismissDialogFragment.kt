package com.ph.bookmarkofall.ui

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.ph.bookmarkofall.R

class SwipeDismissDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_swipe_dismiss, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dialog의 터치 이벤트를 처리하는 GestureDetector 생성
        val gestureDetector = GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                // Y 방향으로 swipe up 시 DialogFragment dismiss
                if (e1?.y ?: 0f > e2?.y ?: 0f) {
                    dismiss()
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })

    /*    // Dialog의 터치 이벤트를 RecyclerView에 전달하지 않도록 설정
        view.findViewById<RecyclerView>(R.id.recyclerView).setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }*/
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        // Dialog 배경 투명 처리
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        // Dialog 바깥 부분 터치 시 Dialog가 dismiss 되지 않도록 설정
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }
}