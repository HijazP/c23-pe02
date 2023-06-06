import { PrismaClient } from '@prisma/client'

const prisma = new PrismaClient()

async function main() {
    const modulTemplate: { id: number, namaModul: string }[] = []

    // kursus 1
    modulTemplate.push({'id': 1, namaModul: 'Perubahan menuju sustainable mindset'})

    // kursus 2
    modulTemplate.push({'id': 8, namaModul: 'Growth mindset'})
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
