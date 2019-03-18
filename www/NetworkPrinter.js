var exec = require('cordova/exec');

module.exports.coolMethod = function (arg0, success, error) {
 exec(success, error, 'NetworkPrinter', 'coolMethod', [arg0]);
};

module.exports.print = function (arg0, success, error) {
 exec(success, error, 'NetworkPrinter', 'print', [arg0]);
}
