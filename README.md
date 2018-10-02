# react-native-immediate-phone-call
Initiate immediate phone call (without further user interaction) for React Native on iOS and Android.
The difference with [react-native-phone-call](https://github.com/tiaanduplessis/react-native-phone-call) and [react-native-communications](https://github.com/anarchicknight/react-native-communications) is that with this library no additional user input is required for Android and the call starts instantly (Apple always asks confirmation since the last iOs updates...). 

NOTICE:
- for React Native < 0.47 use react-native-immediate-phone-call <1.x.x
- for React Native > 0.47 use react-native-immediate-phone-call >=1.x.x

## Setup

Fast and easy:
```bash
npm install react-native-immediate-phone-call --save
react-native link react-native-immediate-phone-call
```

Or manual:  add the latest version as dependeny to your package.json.

```javascript
{
  "name": "YourProject",
  ...
  },
  "dependencies": {
    ...
    "react-native-immediate-phone-call": "0.1.0",
    ...
  }
```

#### iOS
* Add RNImmediatePhoneCall.xcoderproj into your project in the Libraries folder.
* Add the .a file on the General tab of your target under Linked Frameworks And Libraries
* Add the .a file on the Build Phases tab of your target under Link Binary With Libraries

#### Android
* In the AndroidManifest.xml file of your android studio project add:
    ```
    <uses-permission android:name="android.permission.CALL_PHONE" />
    ```
* In the settings.gradle
  ```
    include ':react-native-immediate-phone-call', ':app'
    project(':react-native-immediate-phone-call').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-immediate-phone-call/android')
  ```
* In the build.gradle
  ```
    compile project(':react-native-immediate-phone-call')
  ```
* In MainApplication.java
  ```
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