package cordova.plugin.network.printer;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkPrinter extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {
        if (action.equals("print")) {
            this.print(args, callbackContext);
            return true;
        }
        return false;
    }

    private void print(JSONArray args, final CallbackContext callbackContext) {
        if (args != null) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        String ip = args.getJSONObject(0).getString("ip");
                        if (ip == null) {
                            throw new Exception("IP address not found.");
                        }

                        String portAddr = Integer.parseInt(args.getJSONObject(0).getString("port"));
                        if (portAddr == null) {
                            throw new Exception("Port not found.");
                        }
                        int port = Integer.parseInt(portAddr);

                        String content = args.getJSONObject(0).getString("content");
                        if (content == null) {
                            throw new Exception("Print content not found.");
                        }

                        Socket socket = new Socket(ip, port);
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                        dataOutputStream.write(content.getBytes());
                        dataOutputStream.close();
                        socket.close();

                        String success = pluginResponse("true", "Printing is done.");
                        callbackContext.success(success);
                    } catch (UnknownHostException ex) {
                        String error = pluginResponse("false", String.valueOf(ex));
                        callbackContext.error(error);
                    } catch (IOException ex) {
                        String error = pluginResponse("false", String.valueOf(ex));
                        callbackContext.error(error);
                    } catch (Exception ex) {
                        String error = pluginResponse("false", String.valueOf(ex));
                        callbackContext.error(error);
                    }
                }
            };
            thread.start();
        } else {
            callbackContext.error(pluginResponse("false", "Expected paramerters not found."));
        }
    }

    public String pluginResponse(String status, String message) {
        try {
            Log.v("Network Printer Message", message);
            JSONObject res = new JSONObject();
            res.put("status", status);
            res.put("message", message);
            return res.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
