import Hapi from '@hapi/hapi'
import userHandler from './handlers/userHandler';

const userPlugin = {
    name: 'app/user',
    dependencies: ['prisma'],
    register: async function(server: Hapi.Server) {
        server.route([
            {
                // Daftar user
                method: 'POST',
                path: '/user/register',
                handler: userHandler.registerUser,
                options: {
                    auth: false,
                }
            },
            {
                // Masuk user
                method: 'POST',
                path: '/user/login',
                handler: userHandler.loginUser,
                options: {
                    auth: false,
                }
            },
            {
                // Mendapatkan data user
                method: 'GET',
                path: '/user',
                handler: userHandler.getUser,
            },
            {
                // Update user berdasarkan id
                method: 'PUT',
                path: '/user/update',
                handler: userHandler.updateUser,
            },
            {
                // Mengambil semua kursus
                method: 'GET',
                path: '/user/course',
                handler: userHandler.getAllKursus,
            },
            {
                // Mengambil kursus
                method: 'GET',
                path: '/user/course/{id}',
                handler: userHandler.ambilKursus,
            },
            {
                // Update kursus yang sedang diambil
                method: 'PUT',
                path: '/user/course',
                handler: userHandler.updateAmbilKursus,
            },
            {
                // Progress kursus
                method: 'GET',
                path: '/user/course/progress',
                handler: userHandler.progressKursus,
            },
            {
                // Mengambil detail kursus
                method: 'GET',
                path: '/user/course/{id}/detail',
                handler: userHandler.getKursus,
            },
            {
                // Rekomendasi kursus
                method: 'POST',
                path: '/user/course/recommendation/list',
                handler: userHandler.rekomendasiKursus,
            },
            {
                // Mengambil detail modul dari id kursus atau id modul
                method: 'GET',
                path: '/user/course/module',
                handler: userHandler.getModul,

            },
            {
                // Rekomendasi desa berdasarkan kursus
                method: 'GET',
                path: '/user/recommendation/{desa}',
                handler: userHandler.rekomendasiDesa,
            },
            {
                // Mengambil masalah
                method: 'GET',
                path: '/user/problem/{id}',
                handler: userHandler.ambilMasalah
            },
            {
                // Menyelesaikan masalah
                method: 'PUT',
                path: '/user/problem/{id}',
                handler: userHandler.selesaiMasalah
            },
        ])
    },
}
export default userPlugin
