"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const bcrypt_1 = __importDefault(require("bcrypt"));
const jwt_1 = __importDefault(require("@hapi/jwt"));
async function registerDesa(request, h) {
    const { prisma } = request.server.app;
    const { email, password, namaDesa, telepon } = request.payload;
    try {
        const checkDesa = await prisma.desa.findUnique({
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
        const desa = await prisma.desa.create({
            data: {
                email: email,
                password: bcrypt_1.default.hashSync(password, 10),
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
}
async function loginDesa(request, h) {
    const { email, password } = request.payload;
    const { prisma } = request.server.app;
    try {
        const desa = await prisma.desa.findUnique({
            where: {
                email,
            },
        });
        const decoded = bcrypt_1.default.compareSync(password, desa ? desa.password : '');
        if (!decoded || !desa) {
            return h.response({
                statusCode: 401,
                message: 'Email atau password salah'
            }).code(401);
        }
        const token = jwt_1.default.token.generate({ desaId: desa.id }, { key: process.env.JWT_SECRET, algorithm: 'HS256' });
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
}
async function updateDesa(request, h) {
    const token = request.auth.artifacts.token;
    let desaId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            desaId = decode.decoded.payload.desaId;
        }
    }
    const { prisma } = request.server.app;
    const payload = request.payload;
    try {
        const desa = await prisma.desa.update({
            where: {
                id: desaId
            },
            data: {
                email: payload.email,
                password: bcrypt_1.default.hashSync(payload.password, 10),
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
}
async function addProblem(request, h) {
    const token = request.auth.artifacts.token;
    let desaId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            desaId = decode.decoded.payload.desaId;
        }
    }
    const { namaMasalah, deskripsi } = request.payload;
    const { prisma } = request.server.app;
    try {
        const masalah = await prisma.masalah.create({
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
}
async function getAllProblems(request, h) {
    const token = request.auth.artifacts.token;
    let desaId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            desaId = decode.decoded.payload.desaId;
        }
    }
    const { prisma } = request.server.app;
    try {
        const masalah = await prisma.masalah.findMany({
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
}
async function getProblemById(request, h) {
    const token = request.auth.artifacts.token;
    let desaId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            desaId = decode.decoded.payload.desaId;
        }
    }
    const { prisma } = request.server.app;
    const { id } = request.params;
    try {
        const masalah = await prisma.masalah.findMany({
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
}
async function updateProblemById(request, h) {
    const token = request.auth.artifacts.token;
    let desaId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            desaId = decode.decoded.payload.desaId;
        }
    }
    const { prisma } = request.server.app;
    const { id } = request.params;
    const { namaMasalah, deskripsi } = request.payload;
    try {
        const masalah = await prisma.masalah.updateMany({
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
}
async function deleteProblemById(request, h) {
    const token = request.auth.artifacts.token;
    let desaId;
    if (typeof token === "string") {
        const decode = jwt_1.default.token.decode(token);
        if (decode !== undefined) {
            desaId = decode.decoded.payload.desaId;
        }
    }
    const { prisma } = request.server.app;
    const { id } = request.params;
    try {
        const masalah = await prisma.masalah.deleteMany({
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
}
exports.default = {
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