import { PrismaClient } from '@prisma/client'

const prisma = new PrismaClient()

async function main() {

    const kursusTemplate: { id: number, namaKursus: string, deskripsi: string, dampak: string}[] = []

    kursusTemplate.push({
        id: 1,
        namaKursus: 'Fundamental Course(1)',
        deskripsi: 'Fundamental Attitude toward sustainability',
        dampak: 'mengubah paradigma mengenai sustainability; Memahami Prinsip Ekologi; Memahami Elemen dasar Green Mindset; Memahami Konsep Carbon Footprint; Membuat Rancang Bangun Ecology; Memahami Sustainable Community; Memahami Poin-poin SGGs'
    })
    kursusTemplate.push({
        id: 2,
        namaKursus: 'Fundamental Course(2)',
        deskripsi: 'Fundamental Attitude toward sustainability',
        dampak: ''
    })

    const kursus = await prisma.kursus.createMany({
        data: [
            ...kursusTemplate
        ]
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
