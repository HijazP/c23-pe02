"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const userHandler_1 = __importDefault(require("./handlers/userHandler"));
const userPlugin = {
    name: 'app/user',
    dependencies: ['prisma'],
    register: async function (server) {
        server.route([
            {
                // Daftar user
                method: 'POST',
                path: '/user/register',
                handler: userHandler_1.default.registerUser,
                options: {
                    auth: false,
                }
            },
            {
                // Masuk user
                method: 'POST',
                path: '/user/login',
                handler: userHandler_1.default.loginUser,
                options: {
                    auth: false,
                }
            },
            {
                // Update user berdasarkan id
                method: 'PUT',
                path: '/user/update',
                handler: userHandler_1.default.updateUser,
            },
            {
                // Mengambil kursus
                method: 'POST',
                path: '/user/course/{id}',
                handler: userHandler_1.default.ambilKursus,
            },
            {
                // Update kursus yang sedang diambil
                method: 'PUT',
                path: '/user/course',
                handler: userHandler_1.default.updateAmbilKursus,
            },
            {
                // Rekomendasi kursus
                method: 'POST',
                path: '/user/course/recommendation/list',
                handler: userHandler_1.default.rekomendasiKursus,
            },
            {
                // Mengambil masalah
                method: 'GET',
                path: '/user/problem/{id}',
                handler: userHandler_1.default.ambilMasalah
            },
        ]);
    },
};
exports.default = userPlugin;
//# sourceMappingURL=user.js.map