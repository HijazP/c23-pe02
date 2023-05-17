# How to Git
## Getting Started
```
git clone https://github.com/HijazP/c23-pe02
git cd c23-pe02
git checkout [working branch]
```
Untuk working branch sendiri, silahkan pilih sesuai fitur yang ingin dikerjakan  
Kita akan menggunakan git flow, jadi ada 2 branch penting yaitu: `master` dan `develop`  
`master` adalah branch utama atau production release, sedangkan `develop` adalah branch branch pengembangan atau "next release"  
Git flow sendiri mempunyai beberapa branch prefix, yaitu `feature`, `bugfix`, `release`, `hotfix`, dan `support`.

## Alur kerja
Sebelum mulai bekerja
```
git fetch -> untuk memeriksa apakah ada update terkini
git pull origin [working branch] -> untuk mengambil updatean terbaru dari repo sesuai working branch
[optional] jika ingin mengambil seluruh updatean, gunakan git pull saja
```
---
Setelah selesai bekerja
```
git add . -> untuk memasukan semua perubahan ke stagging
git commit -m "[pesan commit]" -> masuk ke commit, dan memberi nama perubahannya
```
Aturan pesan commit:
- Jelas, sesuai dengan perubahan yang dilakukan. Contoh: `menambahkan button login di landing page` jangan hanya `menambahkan button` atau `mengubah button.kt` saja. Aturannya *perubahan + fitur + spesifik*
- Ringkas, tidak terbelit-belit dan langsung to the point, sehingga orang yang melihat pesannya bisa langsung paham
- (Opsional) Deskripsi, tapi ini sulit dilakukan dan ada trik tertentu jika menggunakan Bash, strukturnya `git commit -m "(judul commit)" -m "(deskripsi commit)"`  
Ketika sudah yakin dengan perubahan yang dilakukan
```
git push origin [working branch] -> mengirim perubahan ke repo sesuai working branch
```
---
Beres, jika ingin bekerja lagi, ulangi langkah-langkahnya dari awal.

## Catatan
Struktur branch sendiri, untuk fitur adalah `feature/(spesifik)`. Kita tidak menggunakan branch per-path karena menggunakan git flow, jadi ketika selesai, semua perubahan fitur akan dikirim ke branch develop  
Sehingga perlu mengontak masing-masing reviewer sebelum mulai bekerja di salah satu fitur untuk dibuatkan branch fiturnya  
- Uka = MD
- Hanif = ML dan CC
