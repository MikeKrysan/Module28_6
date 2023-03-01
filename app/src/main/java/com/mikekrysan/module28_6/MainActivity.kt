package com.mikekrysan.module28_6

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //ПРИМЕР 1. ПРЯМОЙ И ОБРАТНЫЙ ПЕРЕХОД
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1) requestFeature
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Transition for MainActivity
        // 2) setup exitTransition
        window.exitTransition = Slide(Gravity.START).apply {
            mode = Slide.MODE_OUT;
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        // 3) setup reenterTransition
        window.reenterTransition = Slide(Gravity.START).apply {
            duration = 1000;
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        root.setOnClickListener {
            // 4) create activityOptions
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this)
            startActivity(Intent(this, SecondActivity::class.java), activityOptions.toBundle())
        }
    }
}

/*
1) Вызываем requestFeature, чтобы обозначить, что мы будем анимировать активности с помощью TransitionFramework. Этот метод обязательно должен вызываться до вызова onCreate.

2) Устанавливаем переход выхода из этой активности как Slide влево. Чтобы анимации не выполнялись, для статус бара и бара навигации вызываются методы excludeTarget для
    каждого перехода с id каждого бара.
3) Устанавливается анимация входа при обратном переходе. Метод onBackPressed вызывает обратный переход. Также исключаем из перехода статус и навигационный бар, как и в переходе выхода.

4) Создаём параметры перехода. Они необходимы для анимированного перехода с использованием Transition. Передаём созданный с помощью makeSceneTransitionAnimation объект вторым
    аргументом для метода startActivity.
 */