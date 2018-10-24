import 'dart:async';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

class FlutterPhotokit {
  static const MethodChannel _channel = const MethodChannel('flutter_photokit');

  FlutterPhotokit._();

  /// Saves the file at [filePath] to [albumName].
  ///
  /// [filePath] must contain either an image or video file.
  ///
  /// If an album named [albumName] doesn't exist, it will be created.
  static Future<bool> saveToAlbum( {@required String filePath, @required String albumName}) async {
    return await _channel.invokeMethod('saveToAlbum', <String, dynamic>{'filePath': filePath, 'albumName': albumName});
  }

  /// Saves the file at [filePath] to the device's camera roll.
  ///
  /// [filePath] must contain either an image or video file.
  static Future<bool> saveToCameraRoll({@required String filePath}) async {
    return await _channel.invokeMethod('saveToCameraRoll', <String, dynamic>{ 'filePath': filePath});
  }
}
