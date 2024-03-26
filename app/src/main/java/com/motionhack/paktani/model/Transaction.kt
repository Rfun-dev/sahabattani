package com.motionhack.paktani.model

data class Transaction(
    val id: String? = null,
    val namaBarang: String? = null,
    val provinsi: String? = null,
    val kabupaten : String? = null,
    val kecamatan : String? = null,
    val kelurahan : String? = null,
    val alamat : String? = null,
    val kodepos : String? = null,
    val jenis_pembayaran : String? = null,
    val jenis_pengiriman : String? = null,
    val jumlahBarang : String? = null,
    val totalHarga : String? = null,
    val gambarBarang : String? = null,
    val tanggal : String? = null

)