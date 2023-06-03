"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const client_1 = require("@prisma/client");
// plugin to instantiate Prisma Client
const prismaPlugin = {
    name: 'prisma',
    register: async function (server) {
        const prisma = new client_1.PrismaClient();
        server.app.prisma = prisma;
        // Close DB connection after the server's connection listeners are stopped
        // Related issue: https://github.com/hapijs/hapi/issues/2839
        server.ext({
            type: 'onPostStop',
            method: async (server) => {
                server.app.prisma.$disconnect();
            },
        });
    },
};
exports.default = prismaPlugin;
//# sourceMappingURL=prisma.js.map