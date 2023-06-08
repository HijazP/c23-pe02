"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.start = void 0;
const hapi_1 = __importDefault(require("@hapi/hapi"));
const prisma_1 = __importDefault(require("./plugins/prisma"));
const jwt_1 = __importDefault(require("@hapi/jwt"));
const inert_1 = __importDefault(require("@hapi/inert"));
const status_1 = __importDefault(require("./plugins/status"));
const desa_1 = __importDefault(require("./plugins/desa"));
const user_1 = __importDefault(require("./plugins/user"));
const server = hapi_1.default.server({
    port: process.env.PORT || 3000,
    host: process.env.HOST || 'localhost',
});
async function start() {
    await server.register([
        prisma_1.default,
        status_1.default,
        desa_1.default,
        user_1.default
    ]);
    await server.register(inert_1.default);
    await server.register(jwt_1.default);
    server.auth.strategy('bigstem-strategy', 'jwt', {
        keys: process.env.JWT_SECRET,
        verify: {
            aud: false,
            iss: false,
            sub: false,
            nbf: true,
            exp: true,
            maxAgeSec: 14400,
            timeSkewSec: 15,
        },
        validate: (decoded, request, h) => {
            return {
                isValid: true,
                credentials: decoded
            };
        }
    });
    server.auth.default('bigstem-strategy');
    await server.start();
    return server;
}
exports.start = start;
process.on('unhandledRejection', async (err) => {
    await server.app.prisma.$disconnect();
    console.log(err);
    process.exit(1);
});
start()
    .then((server) => {
    console.log(`🚀 Server ready at: ${server.info.uri}`);
})
    .catch((err) => {
    console.log(err);
});
//# sourceMappingURL=index.js.map