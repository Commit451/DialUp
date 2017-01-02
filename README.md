# DialUp
Android internet connectivity changes with RxJava 2

[![Build Status](https://travis-ci.org/Commit451/DialUp.svg?branch=master)](https://travis-ci.org/Commit451/DialUp) [![](https://jitpack.io/v/Commit451/DialUp.svg)](https://jitpack.io/#Commit451/DialUp)

# Usage
To start listening for connectivity change events:
```java
DialUp.listen(this)
    .subscribe(new Observer<Boolean>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Boolean aBoolean) {
            Snackbar.make(root, "Status changed to " + aBoolean, Snackbar.LENGTH_LONG)
                    .show();
            textView.setText("Connected: " + aBoolean);
        }

        @Override
        public void onError(Throwable e) {
            //oh no! This is unlikely to happen though
        }

        @Override
        public void onComplete() {
        }
    });
```

It is recommended that you use some method to cancel the subscription when the activity or fragment is destroyed so that the `BroadcastReceiver` gets unregistered. [RxLifecycle](https://github.com/trello/RxLifecycle) is a great tool for this.

To simply check connectivity:
```java
boolean connected = DialUp.status(context);
```

# Notes
- Connectivity is reported by the device and does not always reflect the ability to reach a server. For example, a user could have Wifi enabled and connected, but not be provisioned correctly on the network and DialUp does not know about this.
- When you first subscribe, you will get an immediate status of the network.

# Acknowledgements
DialUp was inspired by [rxnetwork-android](https://github.com/Laimiux/rxnetwork-android) and modified a bit to make it work with RxJava 2.

License
--------

    Copyright 2016 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
