#import <UIKit/UIKit.h>
#import "RCTBridgeModule.h"


@interface RNImmediatePhoneCall : NSObject <RCTBridgeModule>
@end

@implementation RNImmediatePhoneCall

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(immediatePhoneCall:(NSString *)number)
{
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[NSString stringWithFormat:@"tel:%@", number]]];
};

@end
