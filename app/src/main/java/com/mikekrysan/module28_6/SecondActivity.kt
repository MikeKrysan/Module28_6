package com.mikekrysan.module28_6

import android.app.Activity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.Window
import androidx.core.transition.addListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity: Activity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        // 1) requestFeature
//        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
//        // 2) allow overlaps
//        window.allowEnterTransitionOverlap = true
//        window.allowReturnTransitionOverlap = true
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_second)
//        //Transition for SecondActivity
//        // 3) setup enterTransition
////        window.enterTransition = Slide(Gravity.END).apply {
////            duration = 1000;
////            excludeTarget(android.R.id.statusBarBackground, true)
////            excludeTarget(android.R.id.navigationBarBackground, true)
////        }
//        //заменим переход входа на Fade Transition
//        window.enterTransition = Fade().apply {
//            duration = 1000;
//            mode = Fade.MODE_IN
//        }
//        // 4) setup returnTransition
//        window.returnTransition = Slide(Gravity.END).apply {
//            mode = Slide.MODE_OUT;
//            excludeTarget(android.R.id.statusBarBackground, true)
//            excludeTarget(android.R.id.navigationBarBackground, true)
//        }
//    }

    //ПРИМЕР 2. АНИМАЦИЯ СОДЕРЖИМОГО ACTIVITY
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1) requestFeature
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        // 2) allow overlaps
        window.allowEnterTransitionOverlap = true
        window.allowReturnTransitionOverlap = true
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //Transition for SecondActivity
        // 3) setup enterTransition
        window.enterTransition = Fade().apply {
            duration = 1000;
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
            //5 start
            addListener(onStart = {
                val slide = Slide()
                image.visibility = View.INVISIBLE
                TransitionManager.beginDelayedTransition(root, slide)
                image.visibility = View.VISIBLE
            })
            //5 end
        }
        // 4) setup returnTransition
        window.returnTransition = Slide(Gravity.END).apply {
            mode = Slide.MODE_OUT;
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
    }
}

/*
1 Указываем способ анимации для данной активности. Данный флаг должен быть установлен на обоих активностях, которые участвуют в переходе.
2 Разрешаем одновременное воспроизведение переходов. Если флаги установлены в положение true, то соответствующие переходы будут воспроизводиться,
    не дожидаясь окончания соответствующего перехода на изначальной активности (exitTransition) / результирующей активности (reenterTransition).
3 Устанавливаем переход входа на данную активность.
4 Устанавливаем переход выхода при обратном переходе. Вызывается на текущей активности. Обратный переход запускается с помощью onBackPressed метода.

5. Данный блок кода анимирует содержимое экрана. Переход запускается в момент, когда начинается enterTransition.
Чтобы отследить этот момент, добавляется слушатель на событие onStart. С помощью TransitionManager.beginDelayedTransition начинаем прослушивать изменения в сцене.
 В качестве перехода передаем slide Transition. Если не указывать slideEdge явно, то используется значение Gravity.BOTTOM. Это значит, что изображение будет выезжать снизу.
 */