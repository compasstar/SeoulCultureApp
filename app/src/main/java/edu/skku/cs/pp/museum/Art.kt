package edu.skku.cs.pp.museum

data class Art(var SemaPsgudInfoKorInfo:Information) {
    data class Information(val row: ArrayList<ArtInfo>) {
        data class ArtInfo(
            val prdct_cl_nm: String,
            val manage_no_year: String,
            val prdct_nm_korean: String,
            val prdct_nm_eng: String,
            val prdct_stndrd: String,
            val mnfct_year: String,
            val matrl_technic: String,
            val prdct_detail: String,
            val writr_nm: String,
            val main_image: String,
            val thumb_image: String
        )
    }
}

