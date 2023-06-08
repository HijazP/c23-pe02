# API DOCS
## DESA

---
### Daftar Desa
#### Method: POST
```https://be.api-amati.com/desa/register```
#### Data yang dikirim:
#### JSON
```
{
    "email": "kamu@ganteng.anjay",
    "password": "kamugantengdeh",
    "namaDesa": "Cihideung",
    "telepon": "081234567890"
}
```
#### Response:
1. Berhasil
```
{
    "statusCode": 201,
    "message": "Desa berhasil ditambahkan",
    "desa": {
        "id": 1,
        "email": "kamu@ganteng.anjay",
        "namaDesa": "Cihideung",
        "telepon": "081234567890",
    }
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Desa sudah pernah terdaftar
{
    "statusCode": 401,
    "message": "Desa sudah terdaftar dengan email yang sama"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}


// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### Masuk Desa
#### Method: POST
```https://be.api-amati.com/desa/login```
#### Data yang dikirim:
#### JSON
```
{
    "email": "kamu@ganteng.anjay",
    "password": "kamugantengdeh"
}
```
#### Response:
1. Berhasil
```
{
    "statusCode": 200,
    "data": {
        "id": 1,
        "email": "kamu@ganteng.anjay",
        "nama": "Cihideung"
    },
    "message": "Berhasil masuk ke Cihideung",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXNhSWQiOjEsImlhdCI6MTY4NjAyNTM2M30.YnrooZN_-BroR90ipQfWcoqFOpGhx1ANrupVTGgPJ1w"
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Email atau password salah
{
    "statusCode": 401,
    "message": "Email atau password salah"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```
---
### Mendapatkan data desa
#### Method: GET
```https://be.api-amati.com/desa```
#### perlu token
#### Response:
1. Berhasil
```
{
    "statusCode": 200,
    "message": "Berhasil mendapatkan data desa",
    "desa": {
        "id": 12,
        "email": "pinogu@gorontalo.id",
        "namaDesa": "Pinogu",
        "telepon": "081234567890",
        "lokasiDesa": "Desa Pinogu, Kecamatan Pinogu, Kabupaten Bone Bolango, Gorontalo",
        "longitude": 123.4301401947905,
        "latitude": 0.5039652206276404,
        "foto": "pinogu_gorontalo.jpg"
    }
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```
---
### Ubah profil desa
#### Masih error, nanti dilanjut kalau ga males

---
### Tambah Masalah Desa
#### Method: POST
```https://be.api-amati.com/desa/problem```
#### Data yang dikirim:
#### JSON (perlu token)
```
{
    "namaMasalah": "Jalan rusak",
    "deskripsi": "Jalan di dekat sungai rusak parah ngab"
}
```
#### Response:
1. Berhasil
```
{
    "statusCode": 201,
    "masalah": {
        "id": 1,
        "namaMasalah": "Jalan rusak",
        "deskripsi": "Jalan di dekat sungai rusak parah ngab",
        "idDesa": 1
    },
    "message": "Berhasil menambahkan masalah desa"
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### Tampilkan Semua Masalah berdasarkan Desa
#### Method: GET
```https://be.api-amati.com/desa/problem```
#### (perlu token)
#### Response:
1. Berhasil
```
// Jika pernah menambahkan masalah
{
    "statusCode": 200,
    "totalMasalah": 1,
    "masalah": [
        {
            "id": 1,
            "namaMasalah": "Jalan rusak",
            "deskripsi": "Jalan di dekat sungai rusak parah ngab",
            "idDesa": 1
        }
    ],
    "message": "Semua masalah berhasil ditampilkan"
}

// Jika belum pernah menambahkan masalah
// atau menampilkan masalah tidak sesuai token akun
{
    "statusCode": 200,
    "message": "Belum menambahkan masalah, tidak ada masalah yang bisa ditampilkan"
}
```
2. Gagal
```
// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### Tampilkan Semua Masalah berdasarkan ID Masalah
#### Method: GET
```https://be.api-amati.com/desa/problem/{id}```
#### Parameter untuk {id} (perlu token)
#### Response:
1. Berhasil
```
// Jika id masalah dan desa sesuai
{
    "statusCode": 200,
    "masalah": [
        {
            "id": 1,
            "namaMasalah": "Jalan rusak",
            "deskripsi": "Jalan di dekat sungai rusak parah ngab",
            "idDesa": 1
        }
    ],
    "message": "Berhasil menampilkan masalah berdasarkan id"
}

// Jika belum pernah menambahkan masalah
// atau menampilkan masalah tidak sesuai token akun
// atau id masalah tidak sesuai dengan desa
{
    "statusCode": 200,
    "message": "Belum menambahkan masalah, tidak ada masalah yang bisa ditampilkan"
}
```
2. Gagal
```
// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### Ubah Masalah berdasarkan ID Masalah
#### Method: PUT
```https://be.api-amati.com/desa/problem/{id}```
#### Parameter untuk {id}
#### Data yang dikirim:
#### JSON (perlu token)
```
{
    "namaMasalah": "Jalan rusak parah",
    "deskripsi": "Jalan di dekat sungai rusak parah ngab euy"
}
```
#### Response:
1. Berhasil
```
// Jika id masalah dan desa sesuai
{
    "statusCode": 200,
    "data": {
        "id": "1",
        "namaMasalah": "Jalan rusak parah",
        "deskripsi": "Jalan di dekat sungai rusak parah ngab euy"
    },
    "message": "Berhasil mengubah masalah berdasarkan id"
}

// Jika belum pernah menambahkan masalah
// atau mengubah masalah tidak sesuai token akun
// atau id masalah tidak sesuai dengan desa
{
    "statusCode": 200,
    "message": "Belum menambahkan masalah, tidak ada masalah yang bisa ditampilkan"
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### Hapus Masalah berdasarkan ID Masalah
#### Method: DELETE
```https://be.api-amati.com/desa/problem/{id}```
#### Parameter untuk {id} (perlu token)
#### Response:
1. Berhasil
```
// Jika id masalah dan desa sesuai
{
    "statusCode": 200,
    "data": {
        "id masalah": "2"
    },
    "message": "Berhasil menghapus masalah berdasarkan id"
}

// Jika belum pernah menambahkan masalah
// atau menghapus masalah tidak sesuai token akun
// atau id masalah tidak sesuai dengan desa
{
    "statusCode": 200,
    "message": "Belum menambahkan masalah, tidak ada masalah yang bisa dihapus"
}
```
2. Gagal
```
// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---  

## USER

---
### Daftar User
#### Method: POST
```https://be.api-amati.com/user/register```
#### Data yang dikirim:
#### JSON
```
{
    "namaLengkap": "saya dong",
    "email": "kamu@ganteng.anjay",
    "password": "kamugantengdeh"
}
```
#### Response:
1. Berhasil
```
{
    "statusCode": 201,
    "message": "Pengguna berhasil ditambahkan",
    "user": {
        "id": 1,
        "namaLengkap": "saya dong",
        "email": "kamu@ganteng.anjay"
    }
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Email sudah terdaftar
{
    "statusCode": 401,
    "message": "Email sudah terdaftar"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}


// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---

### Login User
#### Method: POST
```https://be.api-amati.com/user/login```
#### Data yang dikirim:
#### JSON
```
{
    "email": "kamu@ganteng.anjay",
    "password": "kamugantengdeh"
}
```
#### Response:
1. Berhasil
```
{
    "statusCode": 200,
    "data": {
        "id": 1,
        "email": "kamu@ganteng.anjay",
        "nama lengkap": "saya sad dong"
    },
    "message": "Berhasil masuk ke saya sad dong",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTY4NjI0ODAwNn0.llLm5rzuGfY8LuSXNNBOoI_cBtDRim6G8XDanh6PplY"
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Email atau password salah
{
    "statusCode": 401,
    "message": "Email atau password salah"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### Mendapatkan data user
#### Method: GET
```https://be.api-amati.com/user```
#### perlu token
#### Response:
1. Berhasil
```
{
    "statusCode": 200,
    "message": "User berhasil ditampilkan",
    "user": {
        "id": 1,
        "namaLengkap": "saya sad dong",
        "email": "kamu@ganteng.anjay",
        "foto": ""
    }
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### Ubah profil user
#### Method: PUT
```https://be.api-amati.com/user/update```
#### Data yang dikirim:
#### JSON (perlu token)
```
{
    "namaLengkap": "saya tidak sad",
    "email": "kamu@ganteng.anjay",
    "password": "kamugantengdeh",
    "foto": "foto_kamu.jpg"
}
```
1. Berhasil
```
{
    "statusCode": 200,
    "message": "User berhasil diupdate",
    "user": {
        "id": 1,
        "namaLengkap": "saya tidak sad",
        "email": "kamu@ganteng.anjay",
        "foto": "foto_kamu.jpg"
    }
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### User Mengambil Kursus
#### Method: GET
```https://be.api-amati.com/user/course/{id}```
#### Parameter {id} = Kursus id, sudah ada 11 kursus di db
#### Perlu token
#### Response:
1. Berhasil
```
{
    "statusCode": 200,
    "message": "Kursus berhasil diambil",
    "ambilKursus": {
        "id": 1,
        "idKursus": 1,
        "idPengguna": 1,
        "jumlahModul": 7,
        "modulSekarang": 0,
        "statusSelesai": false
    }
}
```
2. Gagal
```
// id kursus sama dengan yang pernah diambil
{
    "statusCode": 400,
    "message": "Kursus sudah diambil"
}

// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```

---
### Update Progress Kursus
#### Method: PUT
```https://be.api-amati.com/user/course?id={id}&status={status}```
#### Data yang dikirim:
#### Query (perlu token)
```
    id = id kursus
    status = next || completed
    next ketika lanjut ke modul selanjutnya (modulSekarang + 1)
    completed ketika menyelesaikan modul
```
#### Response:
1. Berhasil
```
// next, modulSekarang sebelumnya 1
// statusSelesai = false
{
    "statusCode": 200,
    "message": "Kursus berhasil diupdate",
    "ambilKursus": {
        "id": 1,
        "idKursus": 1,
        "idPengguna": 1,
        "jumlahModul": 7,
        "modulSekarang": 2,
        "statusSelesai": false
    }
}

// completed, modulSekarang = jumlahModul
// statusSelesai = true
{
    "statusCode": 200,
    "message": "Kursus berhasil diselesaikan",
    "ambilKursus": {
        "id": 1,
        "idKursus": 1,
        "idPengguna": 1,
        "jumlahModul": 7,
        "modulSekarang": 7,
        "statusSelesai": true
    }
}

// Salah query
{
    "statusCode": 200,
    "message": "Tidak ada kursus yang diupdate"
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```
---
### Menampilkan detail kursus
#### Method: GET
```https://be.api-amati.com/user/course/{id}/detail```
#### Data yang dikirim:
#### Query (perlu token)
```
    id = id kursus
```
#### Response:
1. Berhasil
```
// id = 1
{
    "statusCode": 200,
    "message": "Kursus berhasil ditampilkan",
    "kursus": {
        "id": 1,
        "namaKursus": "Fundamental Course (1)",
        "deskripsi": "Fundamental Attitude toward sustainability",
        "dampak": "mengubah paradigma mengenai sustainability. Memahami Prinsip Ekologi. Memahami Elemen dasar Green Mindset. Memahami Konsep Carbon Footprint. Membuat Rancang Bangun Ecology. Memahami Sustainable Community. Memahami Poin-poin SGGs",
        "foto": ""
    }
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```
---
### Rekomendasi kursus
#### Method: GET
```https://be.api-amati.com/recommendations/{nama kursus}```
#### {nama kursus} = sesuai dengan database, tidak perlu token
#### Response:
1. Berhasil
```
{
    "Kursus Saat Ini": "Fundamental Course (1)",
    "Rekomendasi": [
        "Fundamental Course (2)",
        "Waste Management",
        "Fundamental Course (3)",
        "Program Startup",
        "Solar Academy",
        "Indonesia Sustainability Coral Reef University Network (ISCORE)",
        "Moringa Academy",
        "Indonesia Sustainable Social Forestry Education Program (IS-FREE)",
        "Integrated Farming",
        "Ecotourism"
    ]
}
```
2. Gagal
```
Internal Server Error
```
---
### Detail rekomendasi desa
#### Method: POST
```https://be.api-amati.com/user/course/recommendation/list```
#### Data yang dikirim:
#### JSON (perlu token)
```
// k kecil semua
{
    "kursus1": "Fundamental Course (2)",
    "kursus2": "Waste Management",
    "kursus3": "Program Startup",
    "kursus4": "Solar Academy",
    "kursus5": "Indonesia Sustainability Coral Reef University Network (ISCORE)"
}
```
#### Response:
1. Berhasil
```
{
    "statusCode": 200,
    "message": "Kursus berhasil diambil",
    "kursuspertama": [
        {
            "id": 2,
            "namaKursus": "Fundamental Course (2)",
            "deskripsi": "Innovation method as problem solving",
            "dampak": "Memahami konsep Growth Mindset. Memahami konsep Design Thinking. Memahami Konsep Lean Startup. Memahami Digital Startup",
            "foto": ""
        }
    ],
    "kursuskedua": [
        {
            "id": 8,
            "namaKursus": "Waste Management",
            "deskripsi": "Bukan hanya kota yang darurat sampah, Desa Desa di Indonesia pun memiliki issue yang sama. Manajemen sampah saat ini menjadi industri yang sangat besar di Indonesia. Hampir semua perusahaan membutuhkannya. Program ini mengajak peserta membuat Inovasi proses manajemen sampah di Desa. Dari mulai Edukasi, daur ulang, peluang bisnis.",
            "dampak": "pelindungan habitat. mengurangi polusi sampah di tanah, air dan udara. Aksi nyata pemuda lokal. Sampah terpilah. Mengurangi timbunan sampah di TPU setempat. Recycle sampah ke pabrk pengolahan. Meningkatkan kemandirian warga Desa dengan merubah sampah menjadi sesuatu yang bernilai Mencegah musibah karna sampah",
            "foto": ""
        }
    ],
    "kursusketiga": [
        {
            "id": 11,
            "namaKursus": "Program Startup",
            "deskripsi": "Pemerintah Indonesia saat ini memiliki target untuk meningkatkan rasio wirausaha menjadi 3,95% di tahun 2024. Jenis wirausaha yang diharapkan tidak hanya mengutamakan keuntungan semata, namun juga mengurangi dampak negatif terhadap Sosial dan Lingkungan sekitar. Saat ini sustainable business telah menjadi paradigma yg dipakai dalam membangun sebuah Bisnis. Paradigma baru yang lebih menguntungkan secara ekonomi sekaligus ramah sosial dan lingkungan. Indonesia mengharapkan munculnya wirausaha baru yang muncul dari issue SDGs terutama di Desa. Seperti kita ketahui, desa memiliki Mega Biodiversity yang sangat luar biasa untuk dikembangkan model bisnisnya.",
            "dampak": "Munculnya usaha baru berbasis isu & potensi Desa.",
            "foto": ""
        }
    ],
    "kursuskeempat": [
        {
            "id": 10,
            "namaKursus": "Solar Academy",
            "deskripsi": "Pemerintah Indonesia saat ini memiliki target untuk meningkatkan rasio wirausaha menjadi 3,95% di tahun 2024. Jenis wirausaha yang diharapkan tidak hanya mengutamakan keuntungan semata, namun juga mengurangi dampak negatif terhadap Sosial dan Lingkungan sekitar. Saat ini sustainable business telah menjadi paradigma yg dipakai dalam membangun sebuah Bisnis. Paradigma baru yang lebih menguntungkan secara ekonomi sekaligus ramah sosial dan lingkungan. Indonesia mengharapkan munculnya wirausaha baru yang muncul dari issue SDGs terutama di Desa. Seperti kita ketahui, desa memiliki Mega Biodiversity yang sangat luar biasa untuk dikembangkan model bisnisnya.",
            "dampak": "Memberikan pengetahuan dan informasi terkini kepada mahasiswa dalam memahami terkait energi terbarukan, khususnya sistem PLTS (Pembangkit Listrik Tenaga Surya).  Munculnya Inovasi energi terbarukan untuk masyarakat Indonesia. Munculnya wirausaha baru di bidang energi terbarukan",
            "foto": ""
        }
    ],
    "kursuskelima": [
        {
            "id": 4,
            "namaKursus": "Indonesia Sustainability Coral Reef University Network (ISCORE)",
            "deskripsi": "Terumbu karang menutupi kurang dari 1% wilayah lautan, tetapi mereka secara langsung mendukung jutaan orang dengan menyediakan makanan, pekerjaan, dan sumber daya lainnya. Bahkan lebih banyak orang yang mendapat manfaat dari terumbu karang secara tidak langsung; ekosistem ini membantu memberi makan 1 miliar orang di Asia saja. Populasi manusia hampir 7 miliar orang, dan kemungkinan akan tumbuh menjadi 9 miliar pada tahun 2040. Kita membutuhkan terumbu karang (dan ekosistem lainnya) untuk memasok lebih banyak sumber daya untuk mendukung jumlah kita yang terus bertambah, tetapi mereka semakin terancam dengan kehancuran.",
            "dampak": "pelindungan habibat. mengurangi polusi. mengurangi dampak erosi. Promosi pariwisata. Meningkatkan komunitas lokal. Pemeliharaan agen perubahan berkelanjutan dan perlindungan terumbu karang",
            "foto": ""
        }
    ]
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```
---
### Prediksi desa berdasarkan kursus
#### Method: GET
```https://be.api-amati.com/predict/{kursus}```
#### Data yang dikirim:
#### Query (tidak perlu token)
```
    kursus = nama kursus
    https://be.api-amati.com/predict/Ecotourism
```
#### Response:
1. Berhasil
```
[
    "Nearest similarity pair for [['Ecotourism']]: [desa aimoli, alor], Similarity: 0.2610730142189104"
]
```
2. Gagal
```
Internal Server Error
```
---
### Menampilkan semua masalah desa berdasarkan rekomendasi desa
#### Method: GET
```https://be.api-amati.com/user/recommendation/{desa}```
#### Data yang dikirim:
#### Query (perlu token)
```
    desa = desa dari rekomendasi /predict
    https://be.api-amati.com/user/recommendation/aimoli
```
#### Response:
1. Berhasil
```
{
    "statusCode": 200,
    "message": "Rekomendasi desa dan masalah berhasil ditampilkan",
    "recDesa": [
        {
            "id": 4,
            "email": "aimoli@alor.id",
            "password": "$2b$10$b6rkXi6qiQxnYeA3dCdpAeBp815INdxn88XoLJwWzL85rwsOr9Uh6",
            "namaDesa": "Aimoli",
            "telepon": "081234567890",
            "lokasiDesa": "Desa Aimoli, Kecamatan Alor Barat Laut, Kabupaten Alor, Nusa Tenggara Timur",
            "latitude": -8.191830463562995,
            "longitude": 124.4342411697929,
            "foto": "aimoli_alor.jpg"
        }
    ],
    "masalahdesa": [
        {
            "id": 54,
            "namaMasalah": "Fasilitas penunjang wisata yang masih minim",
            "deskripsi": "",
            "idDesa": 4
        },
        {
            "id": 55,
            "namaMasalah": "Wisata kuliner yang terbatas",
            "deskripsi": "",
            "idDesa": 4
        },
        {
            "id": 56,
            "namaMasalah": "Akses informasi wisata yang masih terbatas",
            "deskripsi": "",
            "idDesa": 4
        }
    ]
}

// Jika desa belum memiliki masalah
{
    "statusCode": 200,
    "message": "Desa belum memiliki masalah",
    "recDesa": []
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```
---
### Mengambil masalah desa
#### Method: GET
```https://be.api-amati.com/user/problem/{id}```
#### Data yang dikirim:
#### Query (perlu token)
```
    id = id masalah dari desa yang ditampilkan 
    menggunakan /user/recommendation/{desa}
```
#### Response:
1. Berhasil
```
{
    "statusCode": 200,
    "message": "Masalah berhasil diambil",
    "masalah": {
        "id": 56,
        "namaMasalah": "Akses informasi wisata yang masih terbatas",
        "deskripsi": "",
        "idDesa": 4
    },
    "ambilmasalah": {
        "id": 1,
        "idMasalah": 56,
        "idPengguna": 1,
        "statusSelesai": false
    }
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```
---
### Menyelesaikan masalah desa
#### Method: PUT
```https://be.api-amati.com/user/problem/{id}```
#### Data yang dikirim:
#### Query (perlu token)
```
    id = id masalah dari desa yang ditampilkan 
    menggunakan /user/recommendation/{desa}
```
#### Response:
1. Berhasil
```
// Karena user hanya bisa mengambil 1 masalah desa
// maka hanya perlu menggantii statusSelesai
{
    "statusCode": 200,
    "message": "Masalah berhasil diselesaikan",
    "ambilmasalah": {
        "id": 1,
        "idMasalah": 56,
        "idPengguna": 1,
        "statusSelesai": true
    }
}
```
2. Gagal
```
// Salah format
{
    "statusCode": 400,
    "error": "Bad Request",
    "message": "Invalid request payload JSON format"
}

// Tidak ada token
{
    "statusCode": 401,
    "error": "Unauthorized",
    "message": "Missing authentication"
}

// Salah path atau method
{
    "statusCode": 404,
    "error": "Not Found",
    "message": "Not Found"
}

// Server error (bisa jadi data kurang lengkap)
{
    "statusCode": 500,
    "message": "Ada masalah di server"
}
```
---

