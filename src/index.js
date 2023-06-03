"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var server_1 = require("./server");
(0, server_1.createServer)()
    .then(server_1.startServer)
    .catch(function (err) {
    console.log(err);
});
//# sourceMappingURL=index.js.map