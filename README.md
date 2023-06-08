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

// Desa sudah pernah terdaftarkan (by email)
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
    "statusCode": 200,
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
    "message": "Pengguna berhasil ditambahkan",
    "desa": {
        "id": 1,
        "namaLengkap": "",
        "email": "sadar@diri"
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

// Desa sudah pernah terdaftarkan (by email)
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
