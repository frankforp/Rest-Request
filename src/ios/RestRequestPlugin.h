#import <Foundation/Foundation.h>

#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVJSON.h>

@interface RestRequestPlugin : CDVPlugin

- (void)exitApp;
- (void)get:(CDVInvokedUrlCommand *)command;
- (void)post:(CDVInvokedUrlCommand *)command;
- (void)put:(CDVInvokedUrlCommand *)command;
- (void)delete:(CDVInvokedUrlCommand *)command;

@end