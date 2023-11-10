package com.shimitadouglas.jobwave.data_class

import com.shimitadouglas.jobwave.R

data class HomeGridItem(val image: Int, val title: String)


fun homeListData(): MutableList<HomeGridItem> {
    return mutableListOf(
        HomeGridItem(image = R.drawable.android, title = "Android Developer"),
        HomeGridItem(image = R.drawable.job, title = "All Available Jobs"),
        HomeGridItem(image = R.drawable.ui_ux, title = "UI_UX Designer"),
        HomeGridItem(image = R.drawable.microsoft, title = "C# & .NET Developer"),
        HomeGridItem(image = R.drawable.flask, title = "Flask Developer"),
        HomeGridItem(image = R.drawable.machine, title = "Artificial Intelligence"),
        HomeGridItem(image = R.drawable.ios, title = "IOS Developer"),
        HomeGridItem(image = R.drawable.react, title = "React Developer"),
        HomeGridItem(image = R.drawable.reactnative, title = "ReactNative Developer"),
        HomeGridItem(image = R.drawable.flutter, title = "Flutter Developer"),
        HomeGridItem(image = R.drawable.nextjs, title = "NextJs Developer"),
        HomeGridItem(image = R.drawable.laravel, title = "Laravel Developer"),
        HomeGridItem(image = R.drawable.django, title = "Django Developer"),
        HomeGridItem(image = R.drawable.backend, title = "Backend Developer"),
        HomeGridItem(image = R.drawable.frontend, title = "Frontend Developer"),
        HomeGridItem(image = R.drawable.fullstack, title = "Fullstack Developer"),
        HomeGridItem(image = R.drawable.java, title = "Java Developer"),
        HomeGridItem(image = R.drawable.game, title = "Game Developer"),
        HomeGridItem(image = R.drawable.cpp, title = "C++ Developer"),
        HomeGridItem(image = R.drawable.angular, title = "Angular Developer"),
        HomeGridItem(image = R.drawable.cybersecurity, title = "CyberSecurity "),
        HomeGridItem(image = R.drawable.system_analyst, title = "System Analyst"),
        HomeGridItem(image = R.drawable.data_science, title = "Data Science Engineer"),
        HomeGridItem(image = R.drawable.nodejs, title = "NodeJS  Developer"),
        HomeGridItem(image = R.drawable.javascript, title = "Javascript Engineer"),
        HomeGridItem(image = R.drawable.kotlin, title = "Kotlin Developer"),
        HomeGridItem(image = R.drawable.linux, title = "Linux Engineer"),
        HomeGridItem(image = R.drawable.docker_kuber, title = "Docker & Kubernetes"),
        HomeGridItem(image = R.drawable.golang, title = "Golang Developer"),
        HomeGridItem(image = R.drawable.python, title = "Python Developer"),
    )
}
