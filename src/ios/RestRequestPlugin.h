#import <Foundation/Foundation.h>

#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVJSON.h>

@interface RestRequestPlugin : CDVPlugin <UIAlertViewDelegate>

- (void)exitApp:(CDVInvokedUrlCommand *)command;
- (void)askForLocationServices:(CDVInvokedUrlCommand *)command;
- (void)get:(CDVInvokedUrlCommand *)command;
- (void)post:(CDVInvokedUrlCommand *)command;
- (void)put:(CDVInvokedUrlCommand *)command;
- (void)delete:(CDVInvokedUrlCommand *)command;

@end