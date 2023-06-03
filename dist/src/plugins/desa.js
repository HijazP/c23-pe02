"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// plugin to instantiate Prisma Client
const desaPlugin = {
    name: 'app/desa',
    dependencies: ['prisma'],
    register: async function (server) {
        // here you can use server.app.prisma
        server.route([
            {
                method: 'POST',
                path: '/desa',
                handler: createDesaHandler,
            },
        ]);
    },
};
exports.default = desaPlugin;
async function createDesaHandler(request, h) {
    const { prisma } = request.server.app;
    const payload = request.payload;
    try {
        const createdUser = await prisma.desa.create({
            data: {
                email: payload.email,
                password: payload.password,
                namaDesa: payload.namaDesa,
                telepon: payload.telepon,
                lokasiDesa: payload.lokasiDesa,
                longitude: payload.longitude,
                latitude: payload.latitude,
            },
            select: {
                id: true,
            },
        });
        return h.response(createdDesa).code(201);
    }
    catch (err) {
        console.log(err);
    }
}
//# sourceMappingURL=desa.js.map