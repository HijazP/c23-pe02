"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const bcrypt_1 = __importDefault(require("bcrypt"));
const jwt_1 = __importDefault(require("@hapi/jwt"));
async function registerUser(request, h) {
    const { prisma } = request.server.app;
    const { email, password } = request.payload;
    try {
        const desa = await prisma.pengguna.create({
            data: {
                namaLengkap: '',
                email: email,
                password: bcrypt_1.default.hashSync(password, 10),
                foto: '',
            },
            select: {
                id: true,
                namaLengkap: true,
                email: true,
            },
        });
        return h.response({
            statusCode: 201,
            message: 'Pengguna berhasil ditambahkan',
            desa
        }).code(201);
    }
    catch (err) {
        console.log(err);
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500);
    }
}
async function loginUser(request, h) {
    const { email, password } = request.payload;
    const { prisma } = request.server.app;
    try {
        const user = await prisma.pengguna.findUnique({
            where: {
                email,
            },
        });
        const decoded = bcrypt_1.default.compareSync(password, user ? user.password : '');
        if (!decoded || !user) {
            return h.response({
                statusCode: 401,
                message: 'Email atau password salah'
            }).code(401);
        }
        const token = jwt_1.default.token.generate({ userId: user.id }, { key: process.env.JWT_SECRET, algorithm: 'HS256' });
        return h.response({
            statusCode: 200,
            data: { id: user.id, email: user.email, 'nama lengkap': user.namaLengkap },
            message: `Berhasil masuk ke ${user.namaLengkap}`,
            token
        }).code(200);
    }
    catch (err) {
        console.log(err);
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500);
    }
}
async function updateUser(request, h) {
    const token = request.auth.artifacts.token;
    let userId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            userId = decode.decoded.payload.userId;
        }
    }
    const { prisma } = request.server.app;
    const { namaLengkap, email, password } = request.payload;
    try {
        const user = await prisma.pengguna.update({
            where: {
                id: userId
            },
            data: {
                namaLengkap: namaLengkap,
                email: email,
                password: bcrypt_1.default.hashSync(password, 10),
                foto: '',
            },
            select: {
                id: true,
                namaLengkap: true,
                email: true,
                foto: true,
            },
        });
        return h.response({
            statusCode: 200,
            message: 'User berhasil diupdate',
            user
        }).code(200);
    }
    catch (err) {
        console.log(err);
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500);
    }
}
async function ambilKursus(request, h) {
    const token = request.auth.artifacts.token;
    let userId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            userId = decode.decoded.payload.userId;
        }
    }
    const { prisma } = request.server.app;
    const { id } = request.params;
    try {
        const jumlahModul = await prisma.modul.count({
            where: {
                idKursus: parseInt(id)
            }
        });
        const ambilKursus = await prisma.ambilkursus.create({
            data: {
                kursus: {
                    connect: {
                        id: parseInt(id)
                    }
                },
                pengguna: {
                    connect: { id: userId }
                },
                jumlahModul: jumlahModul,
                modulSekarang: 0,
                statusSelesai: false,
            }
        });
        return h.response({
            statusCode: 200,
            message: 'Kursus berhasil diambil',
            ambilKursus
        }).code(200);
    }
    catch (err) {
        console.log(err);
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500);
    }
}
async function updateAmbilKursus(request, h) {
    const token = request.auth.artifacts.token;
    let userId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            userId = decode.decoded.payload.userId;
        }
    }
    const { prisma } = request.server.app;
    const { id, status } = request.query;
    try {
        const kursus = await prisma.ambilkursus.findUnique({
            where: {
                id: parseInt(id)
            }
        });
        if (kursus !== null) {
            if (status === 'completed') {
                const ambilKursus = await prisma.ambilkursus.update({
                    where: {
                        id: parseInt(id)
                    },
                    data: {
                        modulSekarang: kursus.jumlahModul,
                        statusSelesai: true
                    }
                });
                return h.response({
                    statusCode: 200,
                    message: 'Kursus berhasil diselesaikan',
                    ambilKursus
                }).code(200);
            }
            else if (status === 'next') {
                const ambilKursus = await prisma.ambilkursus.update({
                    where: {
                        id: parseInt(id)
                    },
                    data: {
                        modulSekarang: kursus.modulSekarang + 1
                    }
                });
                return h.response({
                    statusCode: 200,
                    message: 'Kursus berhasil diupdate',
                    ambilKursus
                }).code(200);
            }
        }
    }
    catch (err) {
        console.log(err);
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500);
    }
}
async function rekomendasiKursus(request, h) {
    const { prisma } = request.server.app;
    const { kursus1, kursus2, kursus3, kursus4, kursus5 } = request.payload;
    try {
        const kursus = await prisma.kursus.findMany({
            where: {
                OR: [
                    {
                        namaKursus: kursus1
                    },
                    {
                        namaKursus: kursus2
                    },
                    {
                        namaKursus: kursus3
                    },
                    {
                        namaKursus: kursus4
                    },
                    {
                        namaKursus: kursus5
                    }
                ]
            }
        });
        return h.response({
            statusCode: 200,
            message: 'Kursus berhasil diambil',
            kursus
        }).code(200);
    }
    catch (err) {
        console.log(err);
        return h.response({
            statusCode: 500,
            message: 'Ada masalah di server'
        }).code(500);
    }
}
async function ambilMasalah(request, h) {
}
exports.default = {
    registerUser,
    loginUser,
    updateUser,
    ambilKursus,
    updateAmbilKursus,
    rekomendasiKursus,
    ambilMasalah
};
//# sourceMappingURL=userHandler.js.map