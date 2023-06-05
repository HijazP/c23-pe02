import Hapi from '@hapi/hapi'
import desaHandler from './handlers/desaHandler';

const desaPlugin = {
    name: 'app/desa',
    dependencies: ['prisma'],
    register: async function(server: Hapi.Server) {
        server.route([
            {
                // Daftar desa
                method: 'POST',
                path: '/desa/register',
                handler: desaHandler.register,
                options: {
                    auth: false,
                }
            },
            {
                // Masuk desa
                method: 'POST',
                path: '/desa/login',
                handler: desaHandler.login,
                options: {
                    auth: false,
                }
            },
            {
                // Menambahkan masalah desa
                method: 'POST',
                path: '/desa/problem',
                handler: desaHandler.addProblem,
            },
            {
                // Mengambil semua masalah desa berdasarkan id desa
                method: 'GET',
                path: '/desa/problem',
                handler: desaHandler.getAllProblems,
            },
            {
                // Mengambil masalah desa berdasarkan id masalah
                method: 'GET',
                path: '/desa/problem/{id}',
                handler: desaHandler.getProblemById,
            },
            {
                // Mengubah masalah desa berdasarkan id masalah
                method: 'PUT',
                path: '/desa/problem/{id}',
                handler: desaHandler.updateProblemById,
            },
            {
                // Menghapus masalah desa berdasarkan id masalah
                method: 'DELETE',
                path: '/desa/problem/{id}',
                handler: desaHandler.deleteProblemById,
            },
        ])
    },
}
export default desaPlugin
