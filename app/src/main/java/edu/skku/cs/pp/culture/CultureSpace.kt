package edu.skku.cs.pp.culture

data class CultureSpace(var culturalSpaceInfo:CultureInformation) {
    data class CultureInformation(val row: ArrayList<CultureInfo>) {
        data class CultureInfo(
            val FAC_NAME:String,
            val ADDR:String,
            val PHNE:String,
            val MAIN_IMG:String
        )
    }
}