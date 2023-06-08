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
function registerDesa(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const { prisma } = request.server.app;
        const { email, password, namaDesa, telepon } = request.payload;
        try {
            const checkDesa = yield prisma.desa.findUnique({
                where: {
                    email: email
                }
            });
            if (checkDesa) {
                return h.response({
                    statusCode: 401,
                    message: 'Desa sudah terdaftar dengan email yang sama'
                }).code(401);
            }
            const desa = yield prisma.desa.create({
                data: {
                    email: email,
                    password: bcrypt.hashSync(password, 10),
                    namaDesa: namaDesa,
                    telepon: telepon,
                },
                select: {
                    id: true,
                    email: true,
                    namaDesa: true,
                    telepon: true,
                },
            });
            return h.response({
                statusCode: 201,
                message: 'Desa berhasil ditambahkan',
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
function loginDesa(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const { email, password } = request.payload;
        const { prisma } = request.server.app;
        try {
            const desa = yield prisma.desa.findUnique({
                where: {
                    email,
                },
            });
            const decoded = bcrypt.compareSync(password, desa ? desa.password : '');
            if (!decoded || !desa) {
                return h.response({
                    statusCode: 401,
                    message: 'Email atau password salah'
                }).code(401);
            }
            const token = Jwt.token.generate({ desaId: desa.id }, { key: process.env.JWT_SECRET, algorithm: 'HS256' });
            return h.response({
                statusCode: 200,
                data: { id: desa.id, email: desa.email, nama: desa.namaDesa },
                message: `Berhasil masuk ke ${desa.namaDesa}`,
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
function updateDesa(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let desaId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                desaId = decode.decoded.payload.desaId;
            }
        }
        const { prisma } = request.server.app;
        const payload = request.payload;
        try {
            const desa = yield prisma.desa.update({
                where: {
                    id: desaId
                },
                data: {
                    email: payload.email,
                    password: bcrypt.hashSync(payload.password, 10),
                    namaDesa: payload.namaDesa,
                    telepon: payload.telepon,
                    lokasiDesa: payload.lokasiDesa,
                    longitude: payload.longitude,
                    latitude: payload.latitude,
                },
                select: {
                    id: true,
                    email: true,
                    namaDesa: true,
                    telepon: true,
                    lokasiDesa: true,
                    longitude: true,
                    latitude: true,
                },
            });
            return h.response({
                statusCode: 200,
                message: 'Desa berhasil diupdate',
                desa
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
function addProblem(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let desaId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                desaId = decode.decoded.payload.desaId;
            }
        }
        const { namaMasalah, deskripsi } = request.payload;
        const { prisma } = request.server.app;
        try {
            const masalah = yield prisma.masalah.create({
                data: {
                    namaMasalah: namaMasalah,
                    deskripsi: deskripsi,
                    desa: {
                        connect: { id: desaId }
                    },
                },
            });
            return h.response({
                statusCode: 200,
                masalah,
                message: 'Berhasil menambahkan masalah desa'
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
function getAllProblems(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let desaId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                desaId = decode.decoded.payload.desaId;
            }
        }
        const { prisma } = request.server.app;
        try {
            const masalah = yield prisma.masalah.findMany({
                where: {
                    idDesa: desaId
                }
            });
            if (masalah.length > 0) {
                return h.response({
                    statusCode: 200,
                    totalMasalah: masalah.length,
                    masalah,
                    message: 'Semua masalah berhasil ditampilkan'
                }).code(200);
            }
            else {
                return h.response({
                    statusCode: 200,
                    message: 'Belum menambahkan masalah, tidak ada masalah yang bisa ditampilkan'
                }).code(200);
            }
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
function getProblemById(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let desaId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                desaId = decode.decoded.payload.desaId;
            }
        }
        const { prisma } = request.server.app;
        const { id } = request.params;
        try {
            const masalah = yield prisma.masalah.findMany({
                where: {
                    AND: [
                        { id: parseInt(id) },
                        { idDesa: desaId }
                    ]
                },
            });
            if (masalah.length) {
                return h.response({
                    statusCode: 200,
                    masalah,
                    message: 'Berhasil menampilkan masalah berdasarkan id'
                }).code(200);
            }
            else {
                return h.response({
                    statusCode: 200,
                    message: 'Belum menambahkan masalah, tidak ada masalah yang bisa ditampilkan'
                }).code(200);
            }
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
function updateProblemById(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let desaId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                desaId = decode.decoded.payload.desaId;
            }
        }
        const { prisma } = request.server.app;
        const { id } = request.params;
        const { namaMasalah, deskripsi } = request.payload;
        try {
            const masalah = yield prisma.masalah.updateMany({
                where: {
                    AND: [
                        { id: parseInt(id, 10) },
                        { idDesa: desaId }
                    ]
                },
                data: {
                    namaMasalah: namaMasalah,
                    deskripsi: deskripsi
                }
            });
            if (masalah.count > 0) {
                return h.response({
                    statusCode: 200,
                    data: { id, namaMasalah, deskripsi },
                    message: 'Berhasil mengubah masalah berdasarkan id'
                }).code(200);
            }
            else {
                return h.response({
                    statusCode: 200,
                    message: 'Belum menambahkan masalah, tidak ada masalah yang bisa diubah'
                }).code(200);
            }
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
function deleteProblemById(request, h) {
    return __awaiter(this, void 0, void 0, function* () {
        const token = request.auth.artifacts.token;
        let desaId;
        if (typeof token === "string") {
            const decode = Jwt.token.decode(token);
            if (decode !== undefined) {
                desaId = decode.decoded.payload.desaId;
            }
        }
        const { prisma } = request.server.app;
        const { id } = request.params;
        try {
            const masalah = yield prisma.masalah.deleteMany({
                where: {
                    AND: [
                        { id: parseInt(id) },
                        { idDesa: desaId }
                    ]
                },
            });
            if (masalah.count > 0) {
                return h.response({
                    statusCode: 200,
                    data: { 'id masalah': id },
                    message: 'Berhasil menghapus masalah berdasarkan id'
                }).code(200);
            }
            else {
                return h.response({
                    statusCode: 200,
                    message: 'Belum menambahkan masalah, tidak ada masalah yang bisa dihapus'
                }).code(200);
            }
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
    registerDesa,
    loginDesa,
    updateDesa,
    addProblem,
    getAllProblems,
    getProblemById,
    updateProblemById,
    deleteProblemById,
};
//# sourceMappingURL=desaHandler.js.map