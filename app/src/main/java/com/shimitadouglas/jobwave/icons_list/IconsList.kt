package com.shimitadouglas.jobwave.icons_list

import com.shimitadouglas.jobwave.R

data class IconsList(val title: String, val image: Int)


fun getJobsIcons(): List<IconsList> {
    return listOf(
        IconsList(title = "all", image = R.drawable.all),
        IconsList(title = "android", image = R.drawable.android),
        IconsList(title = "angular", image = R.drawable.angular),
        IconsList(title = "backend", image = R.drawable.backend),
        IconsList(title = "c++", image = R.drawable.cpp),
        IconsList(title = "cybersecurity", image = R.drawable.cybersecurity),
        IconsList(title = "data_science", image = R.drawable.data_science),
        IconsList(title = "django", image = R.drawable.django),
        IconsList(title = "flask", image = R.drawable.flask),
        IconsList(title = "flutter", image = R.drawable.flutter),
        IconsList(title = "frontend", image = R.drawable.frontend),
        IconsList(title = "game", image = R.drawable.game),
        IconsList(title = "ios", image = R.drawable.ios),
        IconsList(title = "java", image = R.drawable.java),
        IconsList(title = "kotlin", image = R.drawable.kotlin),
        IconsList(title = "laravel", image = R.drawable.laravel),
        IconsList(title = "artificial", image = R.drawable.machine),
        IconsList(title = "microsoft", image = R.drawable.microsoft),
        IconsList(title = "network", image = R.drawable.network),
        IconsList(title = "nodejs", image = R.drawable.nodejs),
        IconsList(title = "mobile", image = R.drawable.phone),
        IconsList(title = "react", image = R.drawable.react),
        IconsList(title = "reactnative", image = R.drawable.reactnative),
        IconsList(title = "sharp", image = R.drawable.sharp),
        IconsList(title = "system", image = R.drawable.system_analyst),
        IconsList(title = "ui_ux", image = R.drawable.ui_ux),
        IconsList(title = "unity", image = R.drawable.unity),
    )
}