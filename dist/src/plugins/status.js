"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const statusPlugin = {
    name: 'app/status',
    register: async function (server) {
        server.route({
            method: 'GET',
            path: '/',
            handler: (_, h) => {
                return h.response({ up: true }).code(200);
            },
            options: {
                auth: false,
            }
        });
    },
};
exports.default = statusPlugin;
//# sourceMappingURL=status.js.map