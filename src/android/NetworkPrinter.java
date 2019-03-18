package cordova-plugin-network-printer;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.io.DataOutputStream;

/**
 * This class echoes a string called from JavaScript.
 */
public class NetworkPrinter extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        /*if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }*/
        if (action.equals("print")) {
            this.print(args, callbackContext);
            return true;
        }
        return false;
    }

    /*private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }*/

    private void print(JSONArray args, CallbackContext callbackContext) {
        if (args != null) {
            try {
                String ip = args.getJSONObject(0).getString("ip");
                if (ip == null) {
                    throw new Exception("Network address not found.");
                }
                String port = args.getJSONObject(0)..getString("port");
                if (port == null) {
                    throw new Exception("IP port not found.");
                }
                String content = args.getJSONObject(0)..getString("content");
                if (content == null) {
                    throw new Exception("Print content not found.");
                }

                Socket socket = new Socket(ip, port);
                DataOutputStrem dos = new DataOutputStrem(sock.getOutputStream());

                dos.write(content.getBytes());
                dos.close();
                socket.close();

                callbackContext.success(true);
            } catch (Exception ex) {
                callbackContext.error(ex);
            }
        } else {
            callbackContext.error("Expected parameters not found.");
        }
    }

}
