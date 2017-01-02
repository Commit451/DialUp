# DialUp
Android internet connectivity changes with RxJava 2

[![Build Status](https://travis-ci.org/Commit451/DialUp.svg?branch=master)](https://travis-ci.org/Commit451/DialUp) [![](https://jitpack.io/v/Commit451/DialUp.svg)](https://jitpack.io/#Commit451/DialUp)

# Usage
```java
DialUp.listen(this)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
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
            Log.e("TAG", "fail", e);
        }

        @Override
        public void onComplete() {

        }
    });
```

It is recommended that you use some method to cancel the listen when the activity or fragment is destroyed. [RxLifecycle](https://github.com/trello/RxLifecycle) is a great tool for this.

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
