import { PrismaClient } from '@prisma/client'
import bcrypt from "bcrypt";

const prisma = new PrismaClient()

async function main() {
    const desa = await prisma.desa.createMany({
        data: [
            {
                email: 'pinogu@gorontalo.id',
                password: bcrypt.hashSync('123456', 10),
                namaDesa: 'Pinogu',
                telepon: '081234567890',
                lokasiDesa: 'Desa Pinogu, Kecamatan Pinogu, Kabupaten Bone Bolango, Gorontalo',
                latitude: 0.5039652206276404,
                longitude: 123.43014019479045
            },
            {
                email: 'dataranhijau@gorontalo.id',
                password: bcrypt.hashSync('123456', 10),
                namaDesa: 'Dataran Hijau',
                telepon: '081234567890',
                lokasiDesa: 'Desa Dataran Hijau, Kecamatan Suwawa Timur, Kabupaten Bone Bolango, Gorontalo',
                latitude: 0.4952209335295771,
                longitude: 123.4404633690134

            },
            {
                email: 'lewalu@alor.id',
                password: bcrypt.hashSync('123456', 10),
                namaDesa: 'Lewalu',
                telepon: '081234567890',
                lokasiDesa: 'Desa Lewalu, Kecamatan Alor Barat Laut, Kabupaten Alor, Nusa Tenggara Timur',
                latitude: -8.256740689437446,
                longitude: 124.4315629815835
            },
            {
                email: 'aimoli@alor.id',
                password: bcrypt.hashSync('123456', 10),
                namaDesa: 'Aimoli',
                telepon: '081234567890',
                lokasiDesa: 'Desa Aimoli, Kecamatan Alor Barat Laut, Kabupaten Alor, Nusa Tenggara Timur',
                latitude: -8.191830463562995,
                longitude: 124.43424116979291
            },
        ]
    })

    const masalahTemplate: {namaMasalah: string, deskripsi: string}[] = []

    //Desa Pinogu - Gorontalo
    masalahTemplate.push({ namaMasalah: 'Akses jalan yang buruk', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Bahasa bonda yang mulai jarang digunakan di kalangan anak muda dan anak-anak', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Budaya yang mulai jarang dilakukan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Budidaya dan pengolahan kopi', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Bukti sejarah yang belum ditemukan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Jaringan internet yang kurang lancar', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kawasan organik yang perlu dikembangkan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kearifan lokal dan budaya Pinogu perlu dilestarikan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kebutuhan air bersih', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kebutuhan pangan yang belum lengkap', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Listrik yang kurang menyeluruh', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Pembangunan infrastruksur di kawasan Pinogu', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Pemberdayaan sumber daya manusia', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Pengelolaan sistem dan hasil pertanian serta peternakan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Pengembangan dan pengelolaan potensi pariwisata', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Pengembangan kawasan organik', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Pengolahan produk dari sapi', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Penyalaan listrik yang belum 24 jam', deskripsi: '' });
    
    //Desa Dataran Hijau - Gorontalo
    masalahTemplate.push({ namaMasalah: 'Akses yang sulit', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Infrastruktur', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kendala listrik', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kendala pengelolaan kebun kopi', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kendala pengolahan lahan kebun', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kesulitan alat pengolahan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kurangnya bukti sejarah', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kurangnya pemahaman pengolahan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kendala jaringan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Mindset warga lokal yang kurang berkembang', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya ilmu tentang pengolahan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya minat masyarakat dengan kopi Pinogu', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya mindset tentang bertani', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya mindset warga tentang pemasaran', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya pelestarian budaya', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya Publikasi tentang Haulalahe', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya publikasi tentang Pinogu', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Sulitnya memasarkan produk pertanian', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Tidak ada pelestarian budaya', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Tidak ada penerus usaha', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Tidak ada pengolahan limbah', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Tidak ada tempat wisata', deskripsi: '' });

    //Desa Lewalu - Alor
    masalahTemplate.push({ namaMasalah: 'Akses yang sulit', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Infrastruktur', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kendala listrik', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kendala pengelolaan kebun kopi', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kendala pengolahan lahan kebun', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kesulitan alat pengolahan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kurangnya bukti sejarah', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kurangnya pemahaman pengolahan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Kendala jaringan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Mindset warga lokal yang kurang berkembang', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya ilmu tentang pengolahan', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya minat masyarakat dengan kopi Pinogu', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya mindset tentang bertani', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya mindset warga tentang pemasaran', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya pelestarian budaya', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya Publikasi tentang Haulalahe', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Minimnya publikasi tentang Pinogu', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Sulitnya memasarkan produk pertanian', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Tidak ada pelestarian budaya', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Tidak ada penerus usaha', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Tidak ada pengolahan limbah', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Tidak ada tempat wisata', deskripsi: '' });

    //Desa Aimoli - Alor
    masalahTemplate.push({ namaMasalah: 'Fasilitas penunjang wisata yang masih minim', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Wisata kuliner yang terbatas', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Akses informasi wisata yang masih terbatas', deskripsi: '' });

    const problem: number = 58
    for (let i = 0; i < problem; i++) {
        if (i < 18) {
            const masalah = await prisma.masalah.create({
                data: {
                    namaMasalah: masalahTemplate[i].namaMasalah,
                    deskripsi: masalahTemplate[i].deskripsi,
                    Desa: {
                        connect: { id: 1 }
                    }
                }
            })
        }
        else if (i < 40) {
            const masalah = await prisma.masalah.create({
                data: {
                    namaMasalah: masalahTemplate[i].namaMasalah,
                    deskripsi: masalahTemplate[i].deskripsi,
                    Desa: {
                        connect: { id: 2 }
                    }
                }
            })
        }
        else if (i < 55) {
            const masalah = await prisma.masalah.create({
                data: {
                    namaMasalah: masalahTemplate[i].namaMasalah,
                    deskripsi: masalahTemplate[i].deskripsi,
                    Desa: {
                        connect: { id: 3 }
                    }
                }
            })
        }
        else {
            const masalah = await prisma.masalah.create({
                data: {
                    namaMasalah: masalahTemplate[i].namaMasalah,
                    deskripsi: masalahTemplate[i].deskripsi,
                    Desa: {
                        connect: { id: 4 }
                    }
                }
            })
        }
    }
}

main()
    .catch((e: Error) => {
        console.error(e)
        process.exit(1)
    })
    .finally(async () => {
        // Disconnect Prisma Client
        await prisma.$disconnect()
    })
