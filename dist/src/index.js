"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.start = void 0;
const hapi_1 = __importDefault(require("@hapi/hapi"));
const prisma_1 = __importDefault(require("./plugins/prisma"));
const desa_1 = __importDefault(require("./plugins/desa"));
const status_1 = __importDefault(require("./plugins/status"));
const server = hapi_1.default.server({
    port: process.env.PORT || 3000,
    host: process.env.HOST || 'localhost',
});
async function start() {
    await server.register([prisma_1.default, status_1.default, desa_1.default]);
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
    console.log(`
🚀 Server ready at: ${server.info.uri}
⭐️ See sample requests: http://pris.ly/e/ts/rest-hapi#3-using-the-rest-api
`);
})
    .catch((err) => {
    console.log(err);
});
//# sourceMappingURL=index.js.map