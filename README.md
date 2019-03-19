# cordova-network-printer

Plugin for Cordova to print plain text by IP Address on network printer.

```js
cordova.plugins.NetworkPrinter.print({
  ip: 'YOUR PRINTER IP',
  port: 'PORT (INT)',
  content: 'Priting Content'
}, successCallback, errorCallback);
```
