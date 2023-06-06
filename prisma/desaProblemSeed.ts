import { PrismaClient } from '@prisma/client'

const prisma = new PrismaClient()

async function main() {

    const dampak = await prisma.dampak.createMany({
        data: [
            {namaDampak: 'mengubah paradigma mengenai sustainability'},
            {namaDampak: 'memahami prinsip ekologi'},
            {namaDampak: 'memahami elemen dasar green mindset'},

        ]
    }

    const kursus = await prisma.kursus.createMany({
        data: [
            {
                namaKursus: 'Fundamental Course (1)',
                deskripsi: 'Fundamental Attitude toward sustainability',
            }
        ]
    })

    async function main() {
        const modul = await prisma.modul.createMany({
            data: [
                {namaModul: 'Peurubahan menuju sustainable mindset'},
                {}
            ]
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
