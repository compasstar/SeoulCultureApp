package edu.skku.cs.pp.gallery

data class Gallery(var gongGongArtGallery:GalleryInformation) {
    data class GalleryInformation(val row: ArrayList<GalleryInfo>) {
        data class GalleryInfo(
            val GA_KNAME:String,
            val GA_INS_DATE:String,
            val GA_ADDR1:String,
            val GA_ADDR2:String
        )
    }
}