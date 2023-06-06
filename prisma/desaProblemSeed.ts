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

    masalahTemplate.push({ namaMasalah: 'Akses jalan yang buruk', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Bahasa bonda yang mulai jarang digunakan di kalangan anak muda dan anak-anak', deskripsi: '' });
    masalahTemplate.push({ namaMasalah: 'Budaya yang mulai jarang dilakukan', deskripsi: 'Tumpukan sampah' });
    masalahTemplate.push({ namaMasalah: '', deskripsi: '' });

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
