package com.ph.bookmarkofall.ui

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ph.bookmarkofall.R

class SwipeDismissDialogFragment : BottomSheetDialogFragment() {

    private var slideOffset = 0f
    private var slideAnimation: Animation? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_swipe_dismiss, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 배경 터치 시 다이얼로그 닫히지 않도록 설정
        dialog?.setCanceledOnTouchOutside(false)

        // 백그라운드 색상 투명으로 설정
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // BottomSheetBehavior 설정
        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
       /* bottomSheetBehavior.peekHeight = 0
        bottomSheetBehavior.isHideable = true*/
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                this@SwipeDismissDialogFragment.slideOffset = slideOffset
                updateDialogBackground()
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss()
                }
            }
        })

        // 슬라이드 애니메이션 초기화
      //  initSlideAnimation()
    }

    override fun onStart() {
        super.onStart()
        // 다이얼로그 너비 전체로 설정
       // dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        // 슬라이드 중간에 다이얼로그가 취소된 경우 투명도를 원래대로 되돌리고 레이아웃을 내림
        slideOffset = 0f
        updateDialogBackground()
        dismiss()
    }
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (nextAnim == R.anim.slide_up) {
            slideAnimation
        } else {
            super.onCreateAnimation(transit, enter, nextAnim)
        }
    }
    private fun initSlideAnimation() {
        slideAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f,
            Animation.RELATIVE_TO_SELF, 0f
        ).apply {
            duration = 300
            interpolator = DecelerateInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    slideOffset = 1f
                    updateDialogBackground()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
        }
    }

    private fun updateDialogBackground() {
        val alpha = 1f - slideOffset
        val backgroundColor = ContextCompat.getColor(requireContext(), R.color.white)
        val alphaColor = (alpha * 255).toInt() shl 24 or (backgroundColor and 0x00FFFFFF)
            view?.setBackgroundColor(alphaColor)
    }

    companion object {
        fun newInstance(): SwipeDismissDialogFragment {
            return SwipeDismissDialogFragment()
        }
    }

}
/*class SwipeDismissDialogFragment : DialogFragment() {
    private var initialY: Float = 0f
    private var isSlidingUp = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_swipe_dismiss, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coordinatorLayout = view.findViewById<CoordinatorLayout>(R.id.coordinatorLayout)
        val layoutParams = coordinatorLayout.layoutParams as ViewGroup.MarginLayoutParams
        val initialMarginTop = layoutParams.topMargin

        val gestureDetector =
            GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onScroll(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
                    if (distanceY > 0) {
                        // 슬라이드 업
                        slideUp(distanceY, coordinatorLayout, layoutParams, initialMarginTop)
                    } else {
                        // 슬라이드 다운
                        slideDown(distanceY, coordinatorLayout, layoutParams, initialMarginTop)
                    }
                    return super.onScroll(e1, e2, distanceX, distanceY)
                }

                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    // 슬라이드 완료 후 제스처 릴리즈 시 다이얼로그 dismiss
                    if (!isSlidingUp) {
                        dismiss()
                    }
                    return super.onFling(e1, e2, velocityX, velocityY)
                }
            })

        coordinatorLayout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    private fun slideUp(
        distanceY: Float,
        coordinatorLayout: CoordinatorLayout,
        layoutParams: ViewGroup.MarginLayoutParams,
        initialMarginTop: Int
    ) {
        if (!isSlidingUp) {
            val dialogView = coordinatorLayout.getChildAt(0)
            initialY = dialogView.y
            isSlidingUp = true

            dialogView.animate().apply {
                translationYBy(distanceY)
                alphaBy(-0.02f)
                duration = 0
            }

            val newMarginTop = (layoutParams.topMargin - distanceY).toInt()
            layoutParams.setMargins(
                layoutParams.leftMargin,
                newMarginTop,
                layoutParams.rightMargin,
                layoutParams.bottomMargin
            )
            coordinatorLayout.layoutParams = layoutParams
        }
    }

    private fun slideDown(
        distanceY: Float,
        coordinatorLayout: CoordinatorLayout,
        layoutParams: ViewGroup.MarginLayoutParams,
        initialMarginTop: Int
    ) {
        if (isSlidingUp) {
            val dialogView = coordinatorLayout.getChildAt(0)
            isSlidingUp = false

            dialogView.animate().apply {
                translationYBy(distanceY)
                alphaBy(0.02f)
                duration = 0
            }

            val newMarginTop = (layoutParams.topMargin - distanceY).toInt()
            if (newMarginTop >= initialMarginTop) {
                layoutParams.setMargins(
                    layoutParams.leftMargin,
                    newMarginTop,
                    layoutParams.rightMargin,
                    layoutParams.bottomMargin
                )
                coordinatorLayout.layoutParams = layoutParams
            }
        }
    }
}*/
/* private var initialY: Float = 0f
 private var isSlidingUp = false


 override fun onCreateView(
     inflater: LayoutInflater,
     container: ViewGroup?,
     savedInstanceState: Bundle?
 ): View? {
     return inflater.inflate(R.layout.dialog_fragment_swipe_dismiss, container, false)
 }
 override fun onStart() {
     super.onStart()
     val dialog = dialog
     if (dialog != null) {
         val window = dialog.window
         val layoutParams = window?.attributes
         layoutParams?.gravity = Gravity.TOP
         layoutParams?.height = WindowManager.LayoutParams.MATCH_PARENT

         window?.attributes = layoutParams
         window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
         dialog.window!!.setLayout(
             ViewGroup.LayoutParams.MATCH_PARENT,
             ViewGroup.LayoutParams.MATCH_PARENT
         )
         dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
     }
 }
 @SuppressLint("ClickableViewAccessibility")
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

     // Dialog의 터치 이벤트를 RecyclerView에 전달하지 않도록 설정
     view.setOnTouchListener { _, event ->
         gestureDetector.onTouchEvent(event)
         true
     }
 }

 override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
     val dialog = super.onCreateDialog(savedInstanceState)
     // Dialog 배경 투명 처리
     dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
     // Dialog 바깥 부분 터치 시 Dialog가 dismiss 되지 않도록 설정
     dialog.setCanceledOnTouchOutside(false)
     dialog.window?.setWindowAnimations(R.style.DialogSlideUpAnimation) // 애니메이션 설정

     return dialog
 }*/
