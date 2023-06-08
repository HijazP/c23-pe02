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
                // Update user berdasarkan id
                method: 'PUT',
                path: '/user/update',
                handler: userHandler.updateUser,
            },
            {
                // Mengambil kursus
                method: 'POST',
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
                // Rekomendasi kursus
                method: 'POST',
                path: '/user/course/recommendation/list',
                handler: userHandler.rekomendasiKursus,
            },
            {
                // Rekomendasi desa berdasarkan kursus
                method: 'GET',
                path: '/user/course/recommendation/{desa}',
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
