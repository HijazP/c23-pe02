var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
import Hapi from '@hapi/hapi';
import prisma from './plugins/prisma';
import Jwt from '@hapi/jwt';
import status from './plugins/status';
import desa from './plugins/desa';
import user from './plugins/user';
const server = Hapi.server({
    port: process.env.PORT || 3000,
    host: process.env.HOST || 'localhost',
});
export function start() {
    return __awaiter(this, void 0, void 0, function* () {
        yield server.register([
            prisma,
            status,
            desa,
            user
        ]);
        yield server.register(Jwt);
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
            validate: (decoded) => {
                return {
                    isValid: true,
                    credentials: decoded
                };
            }
        });
        server.auth.default('bigstem-strategy');
        yield server.start();
        return server;
    });
}
process.on('unhandledRejection', (err) => __awaiter(void 0, void 0, void 0, function* () {
    yield server.app.prisma.$disconnect();
    console.log(err);
    process.exit(1);
}));
start()
    .then((server) => {
    console.log(`🚀 Server ready at: ${server.info.uri}`);
})
    .catch((err) => {
    console.log(err);
});
//# sourceMappingURL=index.js.map