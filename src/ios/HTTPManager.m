// Copyright (c) 2012 Mattt Thompson (http://mattt.me/)
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
// Modified by Andrew Stephan
#import "HttpManager.h"

@implementation HttpManager

static HttpManager *instance;

+ (HttpManager *)sharedManager {
    @synchronized(self) {
        static dispatch_once_t onceToken;
        dispatch_once(&onceToken, ^{
            instance = [[HttpManager alloc] init];
        });
        return instance;
    }
}

+ (id)alloc {
    @synchronized([instance class]) {
        NSAssert(instance == nil, @"Attempted to allocate a second instance of a singleton.");
        instance = [super alloc];
        return instance;
    }
    return nil;
}

- (id)init {
    if (self = [super init]) {
        _reqManager = [AFHTTPRequestOperationManager manager];
        _reqManager.responseSerializer = [AFJSONResponseSerializer serializer];
    }
    
    return self;
}

@end