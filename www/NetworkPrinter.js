var exec = require('cordova/exec');

/*module.exports.coolMethod = function (arg0, success, error) {
 exec(success, error, 'NetworkPrinter', 'coolMethod', [arg0]);
};*/

exports.print = function (arg0, success, error) {
 alert('adfdfsd');
 exec(success, error, 'NetworkPrinter', 'print', [arg0]);
}
