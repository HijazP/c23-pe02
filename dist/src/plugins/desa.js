"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const desaHandler_1 = __importDefault(require("./handlers/desaHandler"));
const desaPlugin = {
    name: 'app/desa',
    dependencies: ['prisma'],
    register: async function (server) {
        server.route([
            {
                // Daftar desa
                method: 'POST',
                path: '/desa/register',
                handler: desaHandler_1.default.registerDesa,
                options: {
                    auth: false,
                }
            },
            {
                // Masuk desa
                method: 'POST',
                path: '/desa/login',
                handler: desaHandler_1.default.loginDesa,
                options: {
                    auth: false,
                }
            },
            {
                // Update desa berdasarkan id
                method: 'PUT',
                path: '/desa/update',
                handler: desaHandler_1.default.updateDesa,
            },
            {
                // Menambahkan masalah desa
                method: 'POST',
                path: '/desa/problem',
                handler: desaHandler_1.default.addProblem,
            },
            {
                // Mengambil semua masalah desa berdasarkan id desa
                method: 'GET',
                path: '/desa/problem',
                handler: desaHandler_1.default.getAllProblems,
            },
            {
                // Mengambil masalah desa berdasarkan id masalah
                method: 'GET',
                path: '/desa/problem/{id}',
                handler: desaHandler_1.default.getProblemById,
            },
            {
                // Mengubah masalah desa berdasarkan id masalah
                method: 'PUT',
                path: '/desa/problem/{id}',
                handler: desaHandler_1.default.updateProblemById,
            },
            {
                // Menghapus masalah desa berdasarkan id masalah
                method: 'DELETE',
                path: '/desa/problem/{id}',
                handler: desaHandler_1.default.deleteProblemById,
            },
        ]);
    },
};
exports.default = desaPlugin;
//# sourceMappingURL=desa.js.map