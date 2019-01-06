package com.padmitriy.android.rndrx.util

import com.padmitriy.android.rndrx.model.Summit

fun generateNewSummitList(): ArrayList<Summit> {
    return ArrayList<Summit>().apply {
        add(
            Summit(
                "Zugspitze",
                2962.0f,
                5.0f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Zugspitze_2.JPG/270px-Zugspitze_2.JPG"
            )
        )
        add(
            Summit(
                "Schneefernerkopf",
                2874.0f,
                4.5f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Zugspitze-Schneefernerkpf1.jpg/270px-Zugspitze-Schneefernerkpf1.jpg"
            )
        )
        add(
            Summit(
                "Hochwanner",
                2744.0f,
                4.0f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Hochwanner_von_S_HQ.jpg/270px-Hochwanner_von_S_HQ.jpg"
            )
        )
        add(
            Summit(
                "Middle Höllentalspitze",
                2743.0f,
                3.5f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Hoellentalspitzen.jpg/270px-Hoellentalspitzen.jpg"
            )
        )
        add(
            Summit(
                "Hochblassen",
                2703.0f,
                3.0f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Hochblassen_Zusteig_Meilerhuette_20141004.jpg/270px-Hochblassen_Zusteig_Meilerhuette_20141004.jpg"
            )
        )
        add(
            Summit(
                "Wetterwandeck",
                2698.0f,
                2.5f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Zugspitze-Schneefernerkpf1.jpg/270px-Zugspitze-Schneefernerkpf1.jpg"
            )
        )
        add(
            Summit(
                "Leutascher Dreitorspitze",
                2682.0f,
                2.0f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Partenkirchner_Dreitorspitze_von_S_aus_dem_Leutascher_Platt_HQ.jpg/270px-Partenkirchner_Dreitorspitze_von_S_aus_dem_Leutascher_Platt_HQ.jpg"
            )
        )
        add(
            Summit(
                "Eastern Plattspitze",
                2680.0f,
                1.5f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Plattspitze_NE.JPG/270px-Plattspitze_NE.JPG"
            )
        )
        add(
            Summit(
                "Mädelegabel",
                2645.0f,
                1.0f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Maedelegabele_Schwarzmilzferner_2009.JPG/270px-Maedelegabele_Schwarzmilzferner_2009.JPG"
            )
        )
        add(
            Summit(
                "Großer Hundstod",
                2593.0f,
                0.5f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Grosser_Hundstod.jpg/270px-Grosser_Hundstod.jpg"
            )
        )
    }
}