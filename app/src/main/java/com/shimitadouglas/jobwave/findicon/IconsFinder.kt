package com.shimitadouglas.jobwave.findicon

import com.shimitadouglas.jobwave.R


fun findIcon(title: String): Int {
    title.lowercase()
    var image: Int = R.drawable.job
    if (title.contains("mobile", ignoreCase = true)) image =
        R.drawable.phone else if (title.contains("android", ignoreCase = true)) image =
        R.drawable.android else if (title.contains("flutter", ignoreCase = true)) image =
        R.drawable.flutter else if (title.contains("senior flutter", ignoreCase = true)) image =
        R.drawable.flutter else if (title.contains("linux", ignoreCase = true)) image =
        R.drawable.linux else if (title.contains("kotlin", ignoreCase = true)) image =
        R.drawable.kotlin else if (title.contains("ktor", ignoreCase = true)) image =
        R.drawable.kotlin else if (title.contains("mongo", ignoreCase = true)) image =
        R.drawable.mongo else if (title.contains("firebase", ignoreCase = true)) image =
        R.drawable.firebase else if (title.contains("mongodb", ignoreCase = true)) image =
        R.drawable.mongo else if (title.contains("security", ignoreCase = true)) image =
        R.drawable.cybersecurity else if (title.contains("ios", ignoreCase = true)) image =
        R.drawable.ios else if (title.contains("penetration", ignoreCase = true)) image =
        R.drawable.cybersecurity else if (title.contains("forensic", ignoreCase = true)) image =
        R.drawable.cybersecurity else if (title.contains("malware", ignoreCase = true)) image =
        R.drawable.cybersecurity else if (title.contains("angular", ignoreCase = true)) image =
        R.drawable.angular else if (title.contains("unreal", ignoreCase = true)) image =
        R.drawable.game else if (title.contains("unity", ignoreCase = true)) image =
        R.drawable.unity else if (title.contains(".net", ignoreCase = true)) image =
        R.drawable.sharp else if (title.contains("game", ignoreCase = true)) image =
        R.drawable.game else if (title.contains("artificial", ignoreCase = true)) image =
        R.drawable.machine else if (title.contains("machine", ignoreCase = true)) image =
        R.drawable.machine else if (title.contains("app", ignoreCase = true)) image =
        R.drawable.phone else if (title.contains("system", ignoreCase = true)) image =
        R.drawable.system_analyst else if (title.contains("django", ignoreCase = true)) image =
        R.drawable.django else if (title.contains("flask", ignoreCase = true)) image =
        R.drawable.flask else if (title.contains(
            "reactjs",
            ignoreCase = true
        )
    ) R.drawable.react else if (title.contains("docker", ignoreCase = true)) image =
        R.drawable.docker else if (title.contains("kuber", ignoreCase = true)) image =
        R.drawable.kubernetes else if (title.contains("docker/kuber", ignoreCase = true)) image =
        R.drawable.docker_kuber else if (title.contains("docker kuber", ignoreCase = true)) image =
        R.drawable.docker_kuber else if (title.contains("reactnative", ignoreCase = true)) image =
        R.drawable.reactnative else if (title.contains("react native", ignoreCase = true)) image =
        R.drawable.reactnative else if (title.contains("react-native", ignoreCase = true)) image =
        R.drawable.reactnative else if (title.contains("node", ignoreCase = true)) image =
        R.drawable.nodejs else if (title.contains("frontend", ignoreCase = true)) image =
        R.drawable.frontend else if (title.contains("cloud", ignoreCase = true)) image =
        R.drawable.cloud else if (title.contains("backend", ignoreCase = true)) image =
        R.drawable.backend else if (title.contains("network", ignoreCase = true)) image =
        R.drawable.network else if (title.contains("svelt", ignoreCase = true)) image =
        R.drawable.svelte else if (title.contains("cybersecurity", ignoreCase = true)) image =
        R.drawable.cybersecurity else if (title.contains("designer", ignoreCase = true)) image =
        R.drawable.ui_ux else if (title.contains("ui", ignoreCase = true) || title.contains(
            "ux"
        )
    ) image = R.drawable.ui_ux else if (title.contains("nextjs", ignoreCase = true)) image =
        R.drawable.nextjs else if (title.contains("microsoft", ignoreCase = true)) image =
        R.drawable.microsoft else if (title.contains("fullstack", ignoreCase = true)) image =
        R.drawable.fullstack else if (title.contains("+react", ignoreCase = true)) image =
        R.drawable.react else if (title.contains("react", ignoreCase = true)) image =
        R.drawable.react else if (title.contains("symfony", ignoreCase = true)) image =
        R.drawable.php else if (title.contains("C#", ignoreCase = true)) image =
        R.drawable.sharp else if (title.contains("back end", ignoreCase = true)) image =
        R.drawable.backend else if (title.contains("back-end", ignoreCase = true)) image =
        R.drawable.backend else if (title.contains("backend", ignoreCase = true)) image =
        R.drawable.backend else if (title.contains("full stack", ignoreCase = true)) image =
        R.drawable.fullstack else if (title.contains("full-stack", ignoreCase = true)) image =
        R.drawable.fullstack else if (title.contains("front end", ignoreCase = true)) image =
        R.drawable.frontend else if (title.contains("front-end", ignoreCase = true)) image =
        R.drawable.frontend else if (title.contains("front", ignoreCase = true)) image =
        R.drawable.frontend else if (title.contains("firmware", ignoreCase = true)) image =
        R.drawable.firmware else if (title.contains("abap", ignoreCase = true)) image =
        R.drawable.abap else if (title.contains("api", ignoreCase = true)) image =
        R.drawable.api else if (title.contains("front", ignoreCase = true)) image =
        R.drawable.frontend else if (title.contains("azure", ignoreCase = true)) image =
        R.drawable.azure else if (title.contains(".net", ignoreCase = true)) image =
        R.drawable.sharp else if (title.contains("asp", ignoreCase = true)) image =
        R.drawable.sharp else if (title.contains("intelligence", ignoreCase = true)) image =
        R.drawable.machine else if (title.contains("ai/ml", ignoreCase = true)) image =
        R.drawable.machine else if (title.contains("ml/ai", ignoreCase = true)) image =
        R.drawable.machine else if (title.contains("deploy", ignoreCase = true)) image =
        R.drawable.deploy else if (title.contains("director", ignoreCase = true)) image =
        R.drawable.director else if (title.contains("discord", ignoreCase = true)) image =
        R.drawable.discord else if (title.contains("engineer", ignoreCase = true)) image =
        R.drawable.engineer else if (title.contains("golang", ignoreCase = true)) image =
        R.drawable.golang else if (title.contains("rust", ignoreCase = true)) image =
        R.drawable.rust else if (title.contains("customer", ignoreCase = true)) image =
        R.drawable.help else if (title.contains("support", ignoreCase = true)) image =
        R.drawable.help else if (title.contains("help", ignoreCase = true)) image =
        R.drawable.help else if (title.contains("desk", ignoreCase = true)) image =
        R.drawable.help else if (title.contains("test", ignoreCase = true)) image =
        R.drawable.test else if (title.contains("intern", ignoreCase = true)) image =
        R.drawable.intern else if (title.contains("macafee", ignoreCase = true)) image =
        R.drawable.macafee else if (title.contains("magneto", ignoreCase = true)) image =
        R.drawable.magneto else if (title.contains("martech", ignoreCase = true)) image =
        R.drawable.martech else if (title.contains("devop", ignoreCase = true)) image =
        R.drawable.devop else if (title.contains("lead", ignoreCase = true)) image =
        R.drawable.techlead else if (title.contains("moodle", ignoreCase = true)) image =
        R.drawable.moodle else if (title.contains("noc", ignoreCase = true)) image =
        R.drawable.noc else if (title.contains("objective", ignoreCase = true)) image =
        R.drawable.obj else if (title.contains("okta", ignoreCase = true)) image =
        R.drawable.okta else if (title.contains("php", ignoreCase = true)) image =
        R.drawable.php else if (title.contains("web", ignoreCase = true)) image =
        R.drawable.web else if (title.contains("swift", ignoreCase = true)) image =
        R.drawable.swift else if (title.contains("laravel", ignoreCase = true)) image =
        R.drawable.laravel else if (title.contains("javascript", ignoreCase = true)) image =
        R.drawable.javascript else if (title.contains("node", ignoreCase = true)) image =
        R.drawable.nodejs else if (title.contains("node.js", ignoreCase = true)) image =
        R.drawable.nodejs else if (title.contains("java", ignoreCase = true)) image =
        R.drawable.java else if (title.contains("spring", ignoreCase = true)) image =
        R.drawable.java else if (title.contains("programmer", ignoreCase = true)) image =
        R.drawable.programmer else if (title.contains("program", ignoreCase = true)) image =
        R.drawable.programmer else if (title.contains("quality", ignoreCase = true)) image =
        R.drawable.quality else if (title.contains("robot", ignoreCase = true)) image =
        R.drawable.robot else if (title.contains("robot", ignoreCase = true)) image =
        R.drawable.frontend else if (title.contains("ruby", ignoreCase = true)) image =
        R.drawable.ruby else if (title.contains("scala", ignoreCase = true)) image =
        R.drawable.scala else if (title.contains("senior", ignoreCase = true)) image =
        R.drawable.senior else if (title.contains("software", ignoreCase = true)) image =
        R.drawable.software else if (title.contains("solution", ignoreCase = true)) image =
        R.drawable.solution else if (title.contains("sql", ignoreCase = true)) image =
        R.drawable.sql else if (title.contains("techlead", ignoreCase = true)) image =
        R.drawable.techlead else if (title.contains("principal", ignoreCase = true)) image =
        R.drawable.programmer else if (title.contains("senior", ignoreCase = true)) image =
        R.drawable.senior else if (title.contains("manager", ignoreCase = true)) image =
        R.drawable.programmer else if (title.contains("linux", ignoreCase = true)) image =
        R.drawable.linux else if (title.contains("python", ignoreCase = true)) image =
        R.drawable.python else if (title.contains("React", ignoreCase = true)) image =
        R.drawable.react else if (title.contains("generative", ignoreCase = true)) image =
        R.drawable.machine else if (title.contains("nlp", ignoreCase = true)) image =
        R.drawable.machine else if (title.contains("ai", ignoreCase = true)) image =
        R.drawable.machine else if (title.contains("data", ignoreCase = true)) image =
        R.drawable.data_science

    return image
}
