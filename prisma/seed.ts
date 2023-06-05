import { PrismaClient } from '@prisma/client'
import bcrypt from 'bcrypt'

const prisma = new PrismaClient()

async function main() {
    await prisma.desa.deleteMany({})
    await prisma.modul.deleteMany({})
    await prisma.masalah.deleteMany({})
    await prisma.modulMasalah.deleteMany({})
    await prisma.kursus.deleteMany({})

    const pagerageung = await prisma.desa.create({
        data: {
            email: 'hanif@pagerageung.com',
            password: bcrypt.hashSync('123456', 10),
            namaDesa: 'Pagerageung',
            telepon: '081234567890',
            lokasiDesa: 'Jl. Raya Pagerageung',
            latitude: -6.987,
            longitude: 107.123,
        },
    })

    const kursus1 = await prisma.kursus.create({
        data: {
            namaKursus: 'Solusi Jalan Rusak',
        }
    })

    const problem1 = await prisma.masalah.create({
        data: {
            namaMasalah: 'Jalan Rusak',
            deskripsi: 'Jalan rusak parah',
            Desa: {
                connect: { id: pagerageung.id },
            },
        },
    })

    const modul1 = await prisma.modul.create({
        data: {
            namaModul: 'Perbaikan Jalan',
            Kursus: {
                connect: { id: kursus1.id },
            }
        }
    })

    const modulproblem1 = await prisma.modulMasalah.create({
        data: {
            Modul: {
                connect: { id: modul1.id },
            },
            Masalah: {
                connect: { id: problem1.id },
            },
        },
    })
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
