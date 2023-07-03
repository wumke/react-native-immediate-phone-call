# react-native-immediate-phone-call

Initiate immediate phone call (without further user interaction) for React Native on iOS and Android.

The difference with [react-native-phone-call](https://github.com/tiaanduplessis/react-native-phone-call) and [react-native-communications](https://github.com/anarchicknight/react-native-communications) is that with this library no additional user input is required for Android and the call starts instantly (Apple always asks confirmation since the last iOs updates...).

NOTICE:

- for React Native < 0.47 use react-native-immediate-phone-call <1.x.x
- for React Native 0.47 - 0.59 use react-native-immediate-phone-call >=1.x.x
- for React Native >= 0.60 use react-native-immediate-phone-call >=2.x.x

## Setup

(NOTICE THAT INSTALLATION STEPS FOR NEWER REACT NATIVE VERSIONS MAY BE DIFFERENT...)

For expo managed projects skip to [expo setup guide](#setup-in-expo)

Fast and easy:

```bash
npm install react-native-immediate-phone-call --save
```
Add to your project automatically

```bash
react-native link react-native-immediate-phone-call
```

Or manual: add the latest version as dependency to your package.json.

```javascript
{
  "name": "YourProject",
  ...
  },
  "dependencies": {
    ...
    "react-native-immediate-phone-call": "^1.0.0",
    ...
  }
```

#### iOS

- Add RNImmediatePhoneCall.xcoderproj into your project in the Libraries folder.
- Add the .a file on the General tab of your target under Linked Frameworks And Libraries.
- Add the .a file on the Build Phases tab of your target under Link Binary With Libraries.
- For iOS 9.0 and above it's mandatory to have `LSApplicationQueriesSchemes` in `info.plist` after `</dict>`

```xml
  <key>LSApplicationQueriesSchemes</key>
    <array>
        <string>tel</string>
        <string>telprompt</string>
    </array>
```

#### Android

- In the AndroidManifest.xml file of your android studio project add:

  ```java
  <uses-permission android:name="android.permission.CALL_PHONE" />
  ```

- In the settings.gradle

  ```java
    include ':react-native-immediate-phone-call', ':app'
    project(':react-native-immediate-phone-call').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-immediate-phone-call/android')
  ```

- In the app/build.gradle
  ```java
    implementation project(':react-native-immediate-phone-call')
  ```

- In MainApplication.java

  ```java
    import com.github.wumke.RNImmediatePhoneCall.RNImmediatePhoneCallPackage;
    ...
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
        ...
        new RNImmediatePhoneCallPackage(),
        ...
      );
    }
    ...
  ```

- MainActivity.java

  ```java
    import com.github.wumke.RNImmediatePhoneCall.RNImmediatePhoneCallPackage;  // <--- import
    ...
    public class MainActivity extends ReactActivity {
      ...
      @Override
      public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
          RNImmediatePhoneCallPackage.onRequestPermissionsResult(requestCode, permissions, grantResults); // very important event callback
          super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      }
      ...
    }
  ```

## Setup in Expo

Install package

```bash
npm install react-native-immediate-phone-call --save
```

In the app.json file of your expo project add:

```js
...
       "permissions": [
        ...
        "android.permission.CALL_PHONE"   // <--- add to your project permissions
      ],
...
```

Note: React-native-immediate-phone-call won't work in **expo go** and **expo dev-client** app, you'll need to build an actual apk to test it. 

## Usage

```javascript
import RNImmediatePhoneCall from 'react-native-immediate-phone-call';
...
RNImmediatePhoneCall.immediatePhoneCall('0123456789');
...
```

## Versioning

This project uses semantic versioning: MAJOR.MINOR.PATCH.
This means that releases within the same MAJOR version are always backwards compatible. For more info see [semver.org](http://semver.org/).
