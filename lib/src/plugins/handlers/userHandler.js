var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
import bcrypt from 'bcrypt';
import Jwt from '@hapi/jwt';
function registerUser(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const { prisma } = request.server.app;
        const { email, password } = request.payload;
        try {
            const desa = yield prisma.pengguna.create({
                data: {
                    namaLengkap: '',
                    email: email,
                    password: bcrypt.hashSync(password, 10),
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
    });
}
function loginUser(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const { email, password } = request.payload;
        const { prisma } = request.server.app;
        try {
            const user = yield prisma.pengguna.findUnique({
                where: {
                    email,
                },
            });
            const decoded = bcrypt.compareSync(password, user ? user.password : '');
            if (!decoded || !user) {
                return h.response({
                    statusCode: 401,
                    message: 'Email atau password salah'
                }).code(401);
            }
            const token = Jwt.token.generate({ userId: user.id }, { key: process.env.JWT_SECRET, algorithm: 'HS256' });
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
    });
}
function updateUser(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let userId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                userId = decode.decoded.payload.userId;
            }
        }
        const { prisma } = request.server.app;
        const { namaLengkap, email, password } = request.payload;
        try {
            const user = yield prisma.pengguna.update({
                where: {
                    id: userId
                },
                data: {
                    namaLengkap: namaLengkap,
                    email: email,
                    password: bcrypt.hashSync(password, 10),
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
    });
}
function ambilKursus(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let userId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                userId = decode.decoded.payload.userId;
            }
        }
        const { prisma } = request.server.app;
        const { id } = request.params;
        try {
            const jumlahModul = yield prisma.modul.count({
                where: {
                    idKursus: parseInt(id)
                }
            });
            const ambilKursus = yield prisma.ambilkursus.create({
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
    });
}
function rekomendasiKursus(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const { prisma } = request.server.app;
        const { kursus1, kursus2, kursus3, kursus4, kursus5 } = request.payload;
        try {
            const kursus = yield prisma.kursus.findMany({
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
    });
}
function rekomendasiDesa(request, h) {
    var _a;
    return __awaiter(this, void 0, void 0, function* () {
        const { prisma } = request.server.app;
        const { desa } = request.params;
        try {
            let masalahdesa = [];
            const recDesa = yield prisma.desa.findMany({
                where: {
                    namaDesa: desa
                }
            });
            if (recDesa.length !== undefined) {
                masalahdesa = yield prisma.masalah.findMany({
                    where: {
                        idDesa: (_a = recDesa[0]) === null || _a === void 0 ? void 0 : _a.id
                    }
                });
            }
            return h.response({
                statusCode: 200,
                message: 'Rekomendasi desa dan masalah berhasil ditampilkan',
                recDesa,
                masalahdesa
            }).code(200);
        }
        catch (err) {
            console.log(err);
            return h.response({
                statusCode: 500,
                message: 'Ada masalah di server'
            }).code(500);
        }
    });
}
function ambilMasalah(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let userId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                userId = decode.decoded.payload.userId;
            }
        }
        const { prisma } = request.server.app;
        const { id } = request.params;
        try {
            const masalah = yield prisma.masalah.findUnique({
                where: {
                    id: parseInt(id)
                }
            });
            const ambilmasalah = yield prisma.ambilmasalah.create({
                data: {
                    masalah: {
                        connect: {
                            id: masalah === null || masalah === void 0 ? void 0 : masalah.id
                        }
                    },
                    pengguna: {
                        connect: { id: userId }
                    },
                    statusSelesai: false,
                }
            });
            return h.response({
                statusCode: 200,
                message: 'Masalah berhasil diambil',
                masalah,
                ambilmasalah
            }).code(200);
        }
        catch (err) {
            console.log(err);
            return h.response({
                statusCode: 500,
                message: 'Ada masalah di server'
            }).code(500);
        }
    });
}
function selesaiMasalah(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const { prisma } = request.server.app;
        const { id } = request.params;
        try {
            const ambilmasalah = yield prisma.ambilmasalah.update({
                where: {
                    id: parseInt(id)
                },
                data: {
                    statusSelesai: true
                }
            });
            return h.response({
                statusCode: 200,
                message: 'Masalah berhasil diselesaikan',
                ambilmasalah
            }).code(200);
        }
        catch (err) {
            console.log(err);
            return h.response({
                statusCode: 500,
                message: 'Ada masalah di server'
            }).code(500);
        }
    });
}
export default {
    registerUser,
    loginUser,
    updateUser,
    ambilKursus,
    rekomendasiKursus,
    rekomendasiDesa,
    ambilMasalah,
    selesaiMasalah,
};
//# sourceMappingURL=userHandler.js.map