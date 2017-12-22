# DialUp
Listen for internet connectivity changes with RxJava

[![Build Status](https://travis-ci.org/Commit451/DialUp.svg?branch=master)](https://travis-ci.org/Commit451/DialUp) [![](https://jitpack.io/v/Commit451/DialUp.svg)](https://jitpack.io/#Commit451/DialUp)

# Dependency 
In order to include DialUp as a dependency, you will need to also include [BroadcastReceiverObservable](https://github.com/Commit451/BroadcastReceiverObservable):

[![](https://jitpack.io/v/Commit451/BroadcastReceiverObservable.svg)](https://jitpack.io/#Commit451/BroadcastReceiverObservable)

# Usage
To start listening for connectivity change events:
```kotlin
val textView = findViewById<TextView>(R.id.status)
DialUp.observable(this)
        .subscribe { connected ->
            Snackbar.make(root, "Connectivity changed to: $connected", Snackbar.LENGTH_LONG)
                    .show()
            textView.text = "Connected: $connected"
        }
```

It is recommended that you use some method to cancel the subscription when the activity or fragment is destroyed so that the `BroadcastReceiver` gets unregistered. [RxLifecycle](https://github.com/trello/RxLifecycle) is a great tool for this.

To simply check connectivity:
```kotlin
val connected = DialUp.isConnected(context)
```

# Notes
- Connectivity is reported by the device and does not always reflect the ability to reach a server. For example, a user could have Wifi enabled and connected, but not be provisioned correctly on the network and DialUp does not know about this.
- When you first subscribe, you will get an immediate status of the network.

# Acknowledgements
DialUp uses [BroadcastReceiverObservable](https://github.com/Commit451/BroadcastReceiverObservable) as its base. See that project if you want to create other RxJava observables from Broadcasts

License
--------

    Copyright 2017 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
