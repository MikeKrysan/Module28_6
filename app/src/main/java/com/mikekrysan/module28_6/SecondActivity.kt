package com.mikekrysan.module28_6

import android.app.Activity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.Window

class SecondActivity: Activity() {

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
//        window.enterTransition = Slide(Gravity.END).apply {
//            duration = 1000;
//            excludeTarget(android.R.id.statusBarBackground, true)
//            excludeTarget(android.R.id.navigationBarBackground, true)
//        }
        //заменим переход входа на Fade Transition
        window.enterTransition = Fade().apply {
            duration = 1000;
            mode = Fade.MODE_IN
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
 */