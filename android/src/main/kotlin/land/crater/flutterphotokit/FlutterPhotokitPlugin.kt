package land.crater.flutterphotokit

import android.util.Log
import android.os.Environment
import java.io.File

import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterPhotokitPlugin(): MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar): Unit {
      val channel = MethodChannel(registrar.messenger(), "flutter_photokit")
      channel.setMethodCallHandler(FlutterPhotokitPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result): Unit {
    if (call.method.equals("saveToCameraRoll")) {
      val filePath: String = call.argument("filePath")
      val srcFile: File = File(filePath)
      val fileName: String = srcFile.nameWithoutExtension + "." + srcFile.extension
      val pictureDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "spesh")
      val destFile: File = File(pictureDir, fileName)
      srcFile.copyTo(destFile, true)
      result.success(destFile.getAbsolutePath())
    } else {
      result.notImplemented()
    }
  }
}
