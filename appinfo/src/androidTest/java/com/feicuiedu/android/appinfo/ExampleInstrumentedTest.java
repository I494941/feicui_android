package com.feicuiedu.android.appinfo;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    /*@Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTarInstrumentationRegistry.getTargetContext();

        assertEquals("com.feicuiedu.android.appinfo", appContext.getPackageName());
    }*/

    /**
     * 设备Wifi名称
     */
    @Test
    public void testGetPhoneWifiName() {

        WifiManager wifiManager = (WifiManager) InstrumentationRegistry.getTargetContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String wifiName = info.getSSID() + "";
        Log.d("wifiName", wifiName);
    }

    /**
     * 设备Wifi的IP
     */
    @Test
    public void testGetPhoneWifiIP() {

        WifiManager wifiManager = (WifiManager) InstrumentationRegistry.getTargetContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        /*
		 * long ip = info.getIpAddress(); return longToIP(ip);
		 */
        String ipInfo = Formatter.formatIpAddress(dhcpInfo.ipAddress);

        Log.d("ipInfo", ipInfo);
    }

    /**
     * 设备Wifi的速度
     */
    public void testGetPhoneWifiSpeed() {
        WifiManager wifiManager = (WifiManager) InstrumentationRegistry.getTargetContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        Log.d("speedInfo", info.getLinkSpeed() + "");
    }

    /**
     * 设备Wifi的MAC
     */
    public void testGetPhoneWifiMac() {

        WifiManager wifiManager = (WifiManager) InstrumentationRegistry.getTargetContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        Log.d("macAddress", info.getMacAddress() + "");
    }

    /**
     * 设备网络连接类型 (OFFLINE ? WIFI ? MOBILE) permission.ACCESS_NETWORK_STATE
     */
    public void testGetPhoneNetworkType() {
        ConnectivityManager connManager = (ConnectivityManager) InstrumentationRegistry.getTargetContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();

        if (info != null) {
            Log.d("typeName", info.getTypeName());
        } else {
            Log.d("net_state", "OFFLINE");
        }

        if (info != null && info.isConnected()) {

            Log.d("net_state", "ONLINE");
        } else {
            Log.d("net_state", "OFFLINE");
        }
    }

    /**
     * 设备电话号码 (不是所有都能拿到，运营商将手机号码已写入到sim卡中的就行) Permission: READ_PHONE_STATE
     */
    public void testGetPhoneNumber() {

        TelephonyManager telManager = (TelephonyManager) InstrumentationRegistry.getTargetContext().getSystemService(Context.TELEPHONY_SERVICE);
        Log.d("lineName", telManager.getLine1Number());
    }

    /**
     * 设备运营商名称 (中国移动？中国联通？)
     */
    public void testGetPhoneTelSimName() {

        TelephonyManager telManager = (TelephonyManager) InstrumentationRegistry.getTargetContext().getSystemService(Context.TELEPHONY_SERVICE);
        Log.d("simOperatorName", telManager.getLine1Number());
    }

    /**
     * 设备串号 permission.READ_PHONE_STATE
     */
    public void testGetPhoneIMEI() {

        TelephonyManager telManager = (TelephonyManager) InstrumentationRegistry.getTargetContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = null;
        // 检查是否有权限
        if (PackageManager.PERMISSION_GRANTED == InstrumentationRegistry.getTargetContext().getPackageManager()
                .checkPermission(Manifest.permission.READ_PHONE_STATE, InstrumentationRegistry.getTargetContext().getPackageName())) {
            deviceId = telManager.getDeviceId();
        }

        Log.d("deviceId", deviceId);
    }

    /**
     * 设备系统基带版本
     */
    public void testGetPhoneSystemBasebandVersion() {

        Log.d("radio", Build.RADIO);
    }

    /**
     * 设备系统版本号 (4.1.2?)
     */
    @Test
    public void testGetPhoneSystemVersion() {
        Log.d("release", Build.VERSION.RELEASE);
    }

    /**
     * 设备系统SDK版本号 (16?)
     */
    public void testGetPhoneSystemVersionSDK() {
        Log.d("api_version", Build.VERSION.SDK_INT + "");
    }

    /**
     * 设备设置版本号
     */
    public void testGetPhoneSystemVersionID() {
        Log.d("buid_id", Build.ID);
    }

    /**
     * 设备CPU类型名称 (品牌？)
     */
    public void testGetPhoneCPUName() {

        Log.d("cpu_abi", Build.CPU_ABI);
    }

    /**
     * 设备品牌(moto?)
     */
    public void testGetPhoneName1() {

        Log.d("brand", Build.BRAND);
    }

    /**
     * 设备制造商(moto?)
     */
    public void testGetPhoneName2() {

        Log.d("manufacturer", Build.MANUFACTURER);
    }

    /**
     * 设备型号名称(xt910)
     */
    public void getPhoneModelName() {
        // 带国家用 PRODUCT
        Log.d("model", Build.MODEL);
    }

    /**
     * 设备CPU最大频率
     */
    public void testGetPhoneCpuMaxFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }

        Log.d("cpu_max_freq", result.trim());
    }

    /**
     * 设备CPU最小频率
     */
    public void testGetPhoneCpuMinFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }

        Log.d("cpu_min_freq", result.trim());
    }

    /**
     * 设备CPU当前频率
     */
    public void testGetPhoneCpuCurrentFreq() {
        String result = "N/A";
        try {
            FileReader fr = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            result = text.trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("cpu_cur_freq", result.trim());
    }

    /**
     * 设备CPU名称
     */
    @Test
    public void testGetPhoneCpuName() {

        String cpuName = null;
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String text = "";
            while (true) {
                if (text == null) {
                    break;
                }
                sb.append(text).append(":");
                text = br.readLine();

            }

            Log.d("sb.toString()", sb.toString());
            String[] array = sb.toString().split(":\\s+", 10);

            cpuName = array[9];
            // cpuName = array[2];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("cpuName", cpuName);
    }

    /**
     * 设备CPU数量
     */
    public void testGetPhoneCpuNumber() {

        int cupCount = 0;
        class CpuFilter implements FileFilter {
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());

            cupCount = files.length;
        } catch (Exception e) {
            e.printStackTrace();

            cupCount = 1;
        }

        Log.d("cupCount", cupCount + "");
    }

    /**
     * 判断当前手机是否有ROOT权限
     *
     * @return
     */
    public void testHasRoot() {

        boolean bool = false;

        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;

                Log.d("是否有root权限", "否");
            } else {
                Log.d("是否有root权限", "是");
            }
        } catch (Exception e) {

        }

    }

    /**
     * 获取手机分辨率
     */
	/*
	 * public String getResolution() { String resolution = ""; DisplayMetrics
	 * metrics = new DisplayMetrics(); ((Activity)
	 * context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
	 * resolution = metrics.widthPixels + "*" + metrics.heightPixels; return
	 * resolution; }
	 */

    /**
     * 获取照片最大分辨率
     */
    public void testGetMaxPhotoSize() {
        String maxSize = "";
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
        Camera.Size size = null;
        for (Camera.Size s : sizes) {
            if (size == null) {
                size = s;
            } else if (size.height * s.width < s.height * s.width) {
                size = s;
            }
        }
        maxSize = size.width + "*" + size.height;
        camera.release();

        Log.d("照片最大分辨率", maxSize);
    }

    /**
     * 获取相机最大尺寸
     */
    public void testGetCameraResolution() {
        String cameraResolution = "";
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
        Camera.Size size = null;
        for (Camera.Size s : sizes) {
            if (size == null) {
                size = s;
            } else if (size.height * s.width < s.height * s.width) {
                size = s;
            }
        }
        cameraResolution = (size.width * size.height) / 10000 + "万像素";
        camera.release();

        Log.d("相机最大尺寸", cameraResolution);
    }

    /**
     * 获取闪光灯状态
     */
    public void testGetFlashMode() {
        String flashMode = "";
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        flashMode = parameters.getFlashMode();
        camera.release();

        Log.d("获取闪光灯状态", flashMode);
    }

    /**
     * 获取像素密度
     */
    public void testGetPixDensity() {
        float density = 0;
        density = InstrumentationRegistry.getTargetContext().getResources().getDisplayMetrics().density;

        Log.d("获取像素密度", density + "");
    }

    /**
     * 判断设备是否支持多点触控
     */
    public void testIsSupportMultiTouch() {
        PackageManager pm = InstrumentationRegistry.getTargetContext().getPackageManager();
        boolean isSupportMultiTouch = pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);

        if (isSupportMultiTouch) {
            Log.d("否支持多点触控", "是");
        } else {
            Log.d("否支持多点触控", "否");
        }

    }

    /**
     * 获取蓝牙连接状态
     */
    public void testGetBlueToothState() {
        BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        String bluetoothStatus = null;
        if (bAdapter == null) {
            bluetoothStatus = "设备不支持蓝牙";
        }
        int state = bAdapter.getState();
        switch (state) {
            case BluetoothAdapter.STATE_TURNING_OFF:
                bluetoothStatus = "蓝牙关闭中";
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                bluetoothStatus = "蓝牙开启中";
                break;
            case BluetoothAdapter.STATE_OFF:
                bluetoothStatus = "蓝牙关闭";
                break;
            case BluetoothAdapter.STATE_ON:
                bluetoothStatus = "蓝牙开启";
                break;
            default:
                bluetoothStatus = "未知";
                break;
        }

        Log.d("蓝牙连接状态", bluetoothStatus);
    }


}
