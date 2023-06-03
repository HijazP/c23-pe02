"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const client_1 = require("@prisma/client");
const bcrypt_1 = __importDefault(require("bcrypt"));
const prisma = new client_1.PrismaClient();
async function main() {
    await prisma.desa.deleteMany({});
    await prisma.modul.deleteMany({});
    await prisma.problem.deleteMany({});
    await prisma.modulProblem.deleteMany({});
    await prisma.kursus.deleteMany({});
    const pagerageung = await prisma.desa.create({
        data: {
            email: 'hanif@pagerageung.com',
            password: bcrypt_1.default.hashSync('123456', 10),
            namaDesa: 'Pagerageung',
            telepon: '081234567890',
            lokasiDesa: 'Jl. Raya Pagerageung',
            latitude: -6.987,
            longitude: 107.123,
        },
    });
    const kursus1 = await prisma.kursus.create({
        data: {
            namaKursus: 'Solusi Jalan Rusak',
        }
    });
    const problem1 = await prisma.problem.create({
        data: {
            namaProblem: 'Jalan Rusak',
            deskripsi: 'Jalan rusak parah',
            Desa: {
                connect: { id: pagerageung.id },
            },
        },
    });
    const modul1 = await prisma.modul.create({
        data: {
            namaModul: 'Perbaikan Jalan',
            Kursus: {
                connect: { id: kursus1.id },
            }
        }
    });
    const modulproblem1 = await prisma.modulProblem.create({
        data: {
            Modul: {
                connect: { id: modul1.id },
            },
            Problem: {
                connect: { id: problem1.id },
            },
        },
    });
}
main()
    .catch((e) => {
    console.error(e);
    process.exit(1);
})
    .finally(async () => {
    // Disconnect Prisma Client
    await prisma.$disconnect();
});
//# sourceMappingURL=seed.js.map