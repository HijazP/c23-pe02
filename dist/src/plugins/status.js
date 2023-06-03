"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const plugin = {
    name: 'app/status',
    register: async function (server) {
        server.route({
            method: 'GET',
            path: '/',
            handler: (_, h) => {
                return h.response({ up: true }).code(200);
            },
        });
    },
};
exports.default = plugin;
//# sourceMappingURL=status.js.map