import {NativeModules} from 'react-native';

var RNImmediatePhoneCall = {
  immediatePhoneCall: function(number) {
        NativeModules.RNImmediatePhoneCall.immediatePhoneCall(number);
  }
};

export default RNImmediatePhoneCall;
